import fpinscala.streamingio._
import fpinscala.iomonad._

trait Chapter15 {

  /** EXERCISE 15.1
    * Implement take, which halts the Process after it encounters the given number of elements,
    * and drop, which ignores the given number of arguments and then emits the
    * rest. Also implement takeWhile and dropWhile, that take and drop elements as long
    * as the given predicate remains true.
    */
  def take[I](n: Int): SimpleStreamTransducers.Process[I, I]
  def drop[I](n: Int): SimpleStreamTransducers.Process[I, I]
  def takeWhile[I](f: I => Boolean): SimpleStreamTransducers.Process[I, I]
  def dropWhile[I](f: I => Boolean): SimpleStreamTransducers.Process[I, I]

  /** EXERCISE 15.2
    * Implement count. It should emit the number of elements seen so far. For instance,
    * count(Stream("a", "b", "c")) should yield Stream(1, 2, 3) (or Stream(0, 1, 2, 3),
    * your choice).
    */
  def count[I]: SimpleStreamTransducers.Process[I, Int]

  /** EXERCISE 15.3
    * Implement mean. It should emit a running average of the values seen so far.
    */
  def mean: SimpleStreamTransducers.Process[Double, Double]

  /** EXERCISE 15.4
    * Write sum and count in terms of loop.
    */
  /** EXERCISE 15.5
    * Implement |> as a method on Process. Let the types guide your implementation.
    */
  def |>[O, I, O2](p2: SimpleStreamTransducers.Process[O, O2])
    : SimpleStreamTransducers.Process[I, O2]

  /** EXERCISE 15.6
    * Implement zipWithIndex. It emits a running count of values emitted along with each
    * value; for example, Process("a","b").zipWithIndex yields Process(("a",0),
    * ("b",1)).
    */
  /** EXERCISE 15.7
    * Come up with a generic combinator that lets you express mean in terms of sum
    * and count. Define this combinator and implement mean in terms of it.
    */
  /** EXERCISE 15.8
    * Implement exists. There are multiple ways to implement it, given that exists(_ % 2
    * == 0)(Stream(1,3,5,6,7)) could produce Stream(true) (halting, and only yielding
    * the final result), Stream(false,false,false,true) (halting, and yielding all intermediate
    * results), or Stream(false,false,false,true,true) (not halting, and yielding
    * all the intermediate results). Note that because |> fuses, there’s no penalty to
    * implementing the “trimming” of this last form with a separate combinator.
    */
  def exists[I](f: I => Boolean): SimpleStreamTransducers.Process[I, Boolean]

  /** EXERCISE 15.9
    * Write a program that reads degrees Fahrenheit as Double values from a file, one value
    * per line, sends each value through a process to convert it to degrees Fahrenheit, and
    * writes the result to another file. Your program should ignore blank lines in the input
    * file, as well as lines that start with the # character. You can use the function toCelsius.
    */
  def toCelsius(fahrenheit: Double): Double =
    (5.0 / 9.0) * (fahrenheit - 32.0)

  /** EXERCISE 15.10
    * The runLog function can be defined more generally for any Monad in which it’s possible
    * to catch and raise exceptions (for instance, the Task type mentioned in chapter 13,
    * which adds this capability to the IO type). Define this more general version of runLog.
    * Note that this interpreter can’t be tail-recursive, and relies on the underlying monad
    * for stack safety.
    */
  trait Process[F[_], O] {
    def runLog(implicit F: MonadCatch[F]): F[IndexedSeq[O]]
  }

  trait MonadCatch[F[_]] extends Monad[F] {
    def attempt[A](a: F[A]): F[Either[Throwable, A]]
    def fail[A](t: Throwable): F[A]
  }

  /** EXERCISE 15.11
    * This idiom of using await to “evaluate” the result of some IO action isn’t specific to IO.
    * Implement the generic combinator eval to promote some F[A] to a Process that
    * emits only the result of that F[A]. Also implement eval_, which promotes an F[A] to a
    * Process, emitting no values. Note that implementing these functions doesn’t require
    * knowing anything about F.
    */
  def eval[F[_], A](a: F[A]): Process[F, A]
  def eval_[F[_], A, B](a: F[A]): Process[F, B]

  /** EXERCISE 15.12
    * The definition of to uses a new combinator, join, defined for any Process, which
    * concatenates a nested Process. Implement join using existing primitives. This combinator
    * should be quite familiar to you from previous chapters.
    */
  def join[F[_], O](p: Process[F, Process[F, O]]): Process[F, O]

}
