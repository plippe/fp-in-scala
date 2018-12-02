import com.github.plippe.fpinscala.chapter07.Par.Par
import fpinscala.iomonad._
import IO3.{Console, ~>}

trait Chapter13 {

  /** EXERCISE 13.1
    * Free is a monad for any choice of F. Implement map and flatMap methods on the
    * Free trait, and give the Monad instance for Free[F,_].
    */
  def freeMonad[F[_]]: Monad[({ type f[a] = Free[F, a] })#f]

  /** EXERCISE 13.2
    * Implement a specialized tail-recursive interpreter, runTrampoline, for running a
    * Free[Function0,A].
    */
  // @annotation.tailrec
  def runTrampoline[A](a: Free[Function0, A]): A

  /** EXERCISE 13.3
    * Implement a generic interpreter for Free[F,A], given a Monad[F]. You can pattern
    * your implementation after the Async interpreter given previously, including use
    * of a tail-recursive step function.
    */
  def run[F[_], A](a: Free[F, A])(implicit F: Monad[F]): F[A]

  /** EXERCISE 13.4
    * It turns out that runConsoleFunction0 isn’t stack-safe, since flatMap isn’t stacksafe
    * for Function0 (it has the same problem as our original, naive IO type in which run
    * called itself in the implementation of flatMap). Implement translate using runFree,
    * and then use it to implement runConsole in a stack-safe way.
    */
  def translate[F[_], G[_], A](f: Free[F, A])(fg: F ~> G): Free[G, A]
  def runConsole[A](a: Free[Console, A]): A

  /** EXERCISE 13.5
    * We’re not going to work through a full-fledged implementation of a non-blocking
    * I/O library here, but you may be interested to explore this on your own by building off the
    * java.nio library (API at http://mng.bz/uojM). As a start, try implementing an asynchronous
    * read from an AsynchronousFileChannel (API at http://mng.bz/X30L).
    */
  def read(file: java.nio.channels.AsynchronousFileChannel,
           fromPosition: Long,
           numBytes: Int): Par[Either[Throwable, Array[Byte]]]

}
