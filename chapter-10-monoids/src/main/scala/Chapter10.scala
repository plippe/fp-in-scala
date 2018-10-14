import fpinscala.monoids._
import fpinscala.monoids.Monoid._

trait Chapter10 {

  /** EXERCISE 10.1
    *
    * Give Monoid instances for integer addition and multiplication as well as the Boolean
    * operators.
    */
  val intAddition: Monoid[Int]
  val intMultiplication: Monoid[Int]
  val booleanOr: Monoid[Boolean]
  val booleanAnd: Monoid[Boolean]

  /** EXERCISE 10.2
    *
    * Give a Monoid instance for combining Option values.
    */
  def optionMonoid[A]: Monoid[Option[A]]

  /** EXERCISE 10.3
    *
    * A function having the same argument and return type is sometimes called an endofunction.
    * Write a monoid for endofunctions.
    */
  def endoMonoid[A]: Monoid[A => A]

  /** EXERCISE 10.4
    *
    * Use the property-based testing framework we developed in part 2 to implement a
    * property for the monoid laws. Use your property to test the monoids we’ve written.
    */
  def monoidLaws[A](m: Monoid[A], gen: Gen[A]): Prop

  /** EXERCISE 10.5
    *
    * Implement foldMap.
    */
  /** EXERCISE 10.6
    *
    * The foldMap function can be implemented using either foldLeft or foldRight.
    * But you can also write foldLeft and foldRight using foldMap! Try it.
    */
  /** EXERCISE 10.7
    *
    * Implement a foldMap for IndexedSeq. Your implementation should use the strategy
    * of splitting the sequence in two, recursively processing each half, and then adding the
    * answers together with the monoid.
    */
  def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B

  /** EXERCISE 10.8
    *
    * Also implement a parallel version of foldMap using the library we developed in
    * chapter 7. Hint: Implement par, a combinator to promote Monoid[A] to a Monoid
    * [Par[A]], and then use this to implement parFoldMap.
    */
  import fpinscala.parallelism.Nonblocking._

  def par[A](m: Monoid[A]): Monoid[Par[A]]
  def parFoldMap[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): Par[B]

  /** EXERCISE 10.9
    *
    * Use foldMap to detect whether a given IndexedSeq[Int] is ordered. You’ll need
    * to come up with a creative Monoid.
    */
  /** EXERCISE 10.10
    *
    * Write a monoid instance for WC and make sure that it meets the monoid laws.
    */
  val wcMonoid: Monoid[WC]

  /** EXERCISE 10.11
    *
    * Use the WC monoid to implement a function that counts words in a String by recursively
    * splitting it into substrings and counting the words in those substrings.
    */
  /** EXERCISE 10.12
    *
    * Implement Foldable[List], Foldable[IndexedSeq], and Foldable[Stream].
    * Remember that foldRight, foldLeft, and foldMap can all be implemented in terms
    * of each other, but that might not be the most efficient implementation.
    */
  /** EXERCISE 10.13
    *
    * Recall the binary Tree data type from chapter 3. Implement a Foldable instance for it.
    */
  sealed trait Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  /** EXERCISE 10.14
    *
    * Write a Foldable[Option] instance.
    */
  /** EXERCISE 10.15
    *
    * Any Foldable structure can be turned into a List. Write this conversion in a generic
    * way:
    */
  def toList[F[_], A](fa: F[A]): List[A]

  /** EXERCISE 10.16
    *
    * Prove it. Notice that your implementation of op is obviously associative so long as A.op
    * and B.op are both associative.
    */
  def productMonoid[A, B](A: Monoid[A], B: Monoid[B]): Monoid[(A, B)]

  /** EXERCISE 10.17
    *
    * Write a monoid instance for functions whose results are monoids.
    */
  def functionMonoid[A, B](B: Monoid[B]): Monoid[A => B]

  /** EXERCISE 10.18
    *
    * A bag is like a set, except that it’s represented by a map that contains one entry per
    * element with that element as the key, and the value under that key is the number of
    * times the element appears in the bag. For example:
    *
    * scala> bag(Vector("a", "rose", "is", "a", "rose"))
    * res0: Map[String,Int] = Map(a -> 2, rose -> 2, is -> 1)
    *
    * Use monoids to compute a “bag” from an IndexedSeq.
    */
  def bag[A](as: IndexedSeq[A]): Map[A, Int]

}
