import fpinscala.monads._

trait Chapter11 {

  /** EXERCISE 11.1
    * Write monad instances for Par, Parser, Option, Stream, and List.
    */
  /** EXERCISE 11.2
    * State looks like it would be a monad too, but it takes two type arguments and
    * you need a type constructor of one argument to implement Monad. Try to implement a
    * State monad, see what issues you run into, and think about possible solutions. We’ll
    * discuss the solution later in this chapter.
    */
  /** EXERCISE 11.3
    * The sequence and traverse combinators should be pretty familiar to you by now, and
    * your implementations of them from various prior chapters are probably all very similar.
    * Implement them once and for all on Monad[F].
    */
  def sequence[F[_], A](lma: List[F[A]]): F[List[A]]
  def traverse[F[_], A, B](la: List[A])(f: A => F[B]): F[List[B]]

  /** EXERCISE 11.4
    * Implement replicateM.
    */
  def replicateM[F[_], A](n: Int, ma: F[A]): F[List[A]]

  /** EXERCISE 11.5
    * Think about how replicateM will behave for various choices of F. For example, how
    * does it behave in the List monad? What about Option? Describe in your own words
    * the general meaning of replicateM.
    */
  /** EXERCISE 11.6
    * Here’s an example of a function we haven’t seen before. Implement the function
    * filterM. It’s a bit like filter, except that instead of a function from A => Boolean, we
    * have an A => F[Boolean]. (Replacing various ordinary functions like this with the
    * monadic equivalent often yields interesting results.) Implement this function, and
    * then think about what it means for various data types.
    */
  def filterM[F[_], A](ms: List[A])(f: A => F[Boolean]): F[List[A]]

  /** EXERCISE 11.7
    * Implement the Kleisli composition function compose
    */
  /** EXERCISE 11.8
    * Implement flatMap in terms of compose. It seems that we’ve found another
    * minimal set of monad combinators: compose and unit.
    */
  /** EXERCISE 11.9
    * Show that the two formulations of the associative law, the one in terms of flatMap and
    * the one in terms of compose, are equivalent.
    */
  /** EXERCISE 11.10
    * Prove that these two statements of the identity laws are equivalent.
    */
  /** EXERCISE 11.11
    * Prove that the identity laws hold for a monad of your choice.
    */
  /** EXERCISE 11.12
    * There’s a third minimal set of monadic combinators: map, unit, and join. Implement
    * join in terms of flatMap.
    */
  def join[F[_], A](mma: F[F[A]]): F[A]

  /** EXERCISE 11.13
    * Implement either flatMap or compose in terms of join and map.
    */
  /** EXERCISE 11.14
    * Restate the monad laws to mention only join, map, and unit.
    */
  /** EXERCISE 11.15
    * Write down an explanation, in your own words, of what the associative law means for
    * Par and Parser.
    */
  /** EXERCISE 11.16
    * Explain in your own words what the identity laws are stating in concrete terms for Gen
    * and List.
    */
  /** EXERCISE 11.17
    * Implement map and flatMap as methods on this class, and give an implementation for
    * Monad[Id].
    */
  /** EXERCISE 11.18
    * Now that we have a State monad, you should try it out to see how it behaves. What is
    * the meaning of replicateM in the State monad? How does map2 behave? What about
    * sequence?
    */
  /** EXERCISE 11.19
    * What laws do you expect to mutually hold for getState, setState, unit, and flatMap?
    */
  /** EXERCISE 11.20
    * To cement your understanding of monads, give a monad instance for the following
    * type, and explain what it means. What are its primitive operations? What is the
    * action of flatMap? What meaning does it give to monadic functions like sequence,
    * join, and replicateM? What meaning does it give to the monad laws?
    */
  case class Reader[R, A](run: R => A)
  object Reader {
    def readerMonad[R] = new Monad[({ type f[x] = Reader[R, x] })#f] {
      def unit[A](a: => A): Reader[R, A] = ???
      def flatMap[A, B](st: Reader[R, A])(f: A => Reader[R, B]): Reader[R, B] =
        ???
    }
  }

}
