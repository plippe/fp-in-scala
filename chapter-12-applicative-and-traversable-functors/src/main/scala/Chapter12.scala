import fpinscala.monads.Functor
import fpinscala.applicative._

trait Chapter12 {

  /** EXERCISE 12.1
    * Transplant the implementations of as many combinators as you can from Monad to
    * Applicative, using only map2 and unit, or methods implemented in terms of them.
    */
  def sequence[F[_], A](fas: List[F[A]]): F[List[A]]
  def replicateM[F[_], A](n: Int, fa: F[A]): F[List[A]]
  def product[F[_], A, B](fa: F[A], fb: F[A]): F[(A, B)]

  /** EXERCISE 12.2
    * The name applicative comes from the fact that we can formulate the Applicative
    * interface using an alternate set of primitives, unit and the function apply, rather than
    * unit and map2. Show that this formulation is equivalent in expressiveness by defining
    * map2 and map in terms of unit and apply. Also establish that apply can be implemented
    * in terms of map2 and unit.
    */
  trait Applicative[F[_]] extends Functor[F] {
    def apply[A, B](fab: F[A => B])(fa: F[A]): F[B]
    def unit[A](a: => A): F[A]
    def map[A, B](fa: F[A])(f: A => B): F[B]
    def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[A]
  }

  /** EXERCISE 12.3
    * The apply method is useful for implementing map3, map4, and so on, and the pattern
    * is straightforward. Implement map3 and map4 using only unit, apply, and the curried
    * method available on functions.
    */
  def map3[F[_], A, B, C, D](fa: F[A], fb: F[B], fc: F[C])(
      f: (A, B, C) => D): F[D]
  def map4[F[_], A, B, C, D, E](fa: F[A], fb: F[B], fc: F[C], fd: F[D])(
      f: (A, B, C, D) => E): F[E]

  /** EXERCISE 12.4
    * What is the meaning of streamApplicative.sequence? Specializing the signature
    * of sequence to Stream, we have this:
    */
  def sequence[A](a: List[Stream[A]]): Stream[List[A]]

  /** EXERCISE 12.5
    * Write a monad instance for Either.
    */
  def eitherMonad[E]: Monad[({ type f[x] = Either[E, x] })#f]

  /** EXERCISE 12.6
    * Write an Applicative instance for Validation that accumulates errors in Failure.
    * Note that in the case of Failure there’s always at least one error, stored in head. The
    * rest of the errors accumulate in the tail.
    */
  /** EXERCISE 12.7
    * Prove that all monads are applicative functors by showing that if the monad laws
    * hold, the Monad implementations of map2 and map satisfy the applicative laws.
    */
  /** EXERCISE 12.8
    * Just like we can take the product of two monoids A and B to give the monoid (A, B),
    * we can take the product of two applicative functors. Implement this function:
    */
  def product[F[_], G[_]](
      G: Applicative[G]): Applicative[({ type f[x] = (F[x], G[x]) })#f]

  /** EXERCISE 12.9
    * Applicative functors also compose another way! If F[_] and G[_] are applicative
    * functors, then so is F[G[_]]. Implement this function:
    */
  def compose[F[_], G[_]](
      G: Applicative[G]): Applicative[({ type f[x] = F[G[x]] })#f]

  /** EXERCISE 12.10
    * Prove that this composite applicative functor meets the applicative laws. This is
    * an extremely challenging exercise.
    */
  /** EXERCISE 12.11
    * Try to write compose on Monad. It’s not possible, but it is instructive to attempt it and
    * understand why this is the case.
    */
  def compose[F[_], G[_]](G: Monad[G]): Monad[({ type f[x] = F[G[x]] })#f]

  /** EXERCISE 12.12
    * On the Applicative trait, implement sequence over a Map rather than a List:
    */
  def sequenceMap[F[_], K, V](ofa: Map[K, F[V]]): F[Map[K, V]]

  /** EXERCISE 12.13
    * Write Traverse instances for List, Option, and Tree.
    */
  case class Tree[+A](head: A, tail: List[Tree[A]])

  /** EXERCISE 12.14
    * Implement map in terms of traverse as a method on Traverse[F]. This establishes
    * that Traverse is an extension of Functor and that the traverse function is a
    * generalization of map (for this reason we sometimes call these traversable functors). Note
    * that in implementing map, you can call traverse with your choice of Applicative[G].
    */
  trait Traverse[F[_]] extends Functor[F] {
    def traverse[G[_], A, B](fa: F[A])(f: A => G[B])(
        implicit G: Applicative[G]): G[F[B]] =
      sequence(map(fa)(f))
    def sequence[G[_], A](fga: F[G[A]])(implicit G: Applicative[G]): G[F[A]] =
      traverse(fga)(ga => ga)
    def map[A, B](fa: F[A])(f: A => B): F[B] = ???
  }

  /** EXERCISE 12.15
    * Answer, to your own satisfaction, the question of why it’s not possible for Foldable to
    * extend Functor. Can you think of a Foldable that isn’t a functor?
    */
  /** EXERCISE 12.16
    * There’s an interesting consequence of being able to turn any traversable functor into
    * a reversed list—we can write, once and for all, a function to reverse any traversable
    * functor! Write this function, and think about what it means for List, Tree, and other
    * traversable functors.
    *
    * It should obey the following law, for all x and y of the appropriate types:
    * toList(reverse(x)) ++ toList(reverse(y)) ==
    * reverse(toList(y) ++ toList(x))
    */
  def reverse[F[_], A](fa: F[A]): F[A]

  /** EXERCISE 12.17
    * Use mapAccum to give a default implementation of foldLeft for the Traverse trait.
    */
  /** EXERCISE 12.18
    * Use applicative functor products to write the fusion of two traversals. This function
    * will, given two functions f and g, traverse fa a single time, collecting the results of
    * both functions at once.
    */
  def fuse[F[_], G[_], H[_], A, B](fa: F[A])(f: A => G[B], g: A => H[B])(
      G: Applicative[G],
      H: Applicative[H]): (G[F[B]], H[F[B]])

  /** EXERCISE 12.19
    * Implement the composition of two Traverse instances.
    */
  def compose[F[_], G[_]](
      implicit G: Traverse[G]): Traverse[({ type f[x] = F[G[x]] })#f]

  /** EXERCISE 12.20
    * Implement the composition of two monads where one of them is traversable.
    */
  def composeM[F[_], G[_]](F: Monad[F],
                           G: Monad[G],
                           T: Traverse[G]): Monad[({ type f[x] = F[G[x]] })#f]

}
