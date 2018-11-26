package com.github.plippe.fpinscala.chapter05

sealed trait Stream[+A] {

  /** EXERCISE 5.1
    *
    * Write a function to convert a Stream to a List, which will force its evaluation and let
    * you look at it in the REPL. You can convert to the regular List type in the standard
    * library. You can place this and other functions that operate on a Stream inside the
    * Stream trait.
    */
  def toList: List[A] = ???

  /** EXERCISE 5.2
    *
    * Write the function take(n) for returning the first n elements of a Stream, and
    * drop(n) for skipping the first n elements of a Stream.
    */
  def take(n: Int): Stream[A] = ???
  def drop(n: Int): Stream[A] = ???

  /** EXERCISE 5.3
    *
    * Write the function takeWhile for returning all starting elements of a Stream that
    * match the given predicate.
    */
  def takeWhile(p: A => Boolean): Stream[A] = ???

  /** EXERCISE 5.4
    *
    * Implement forAll, which checks that all elements in the Stream match a given predicate.
    * Your implementation should terminate the traversal as soon as it encounters a
    * nonmatching value.
    */
  def forAll(f: A => Boolean): Boolean = ???

  /** EXERCISE 5.5
    *
    * Use foldRight to implement takeWhile.
    */
  def takeWhileWithFoldRight(p: A => Boolean): Stream[A] = ???

  /** EXERCISE 5.6
    *
    * Implement headOption using foldRight.
    */
  def headOption: Option[A] = ???

  /** EXERCISE 5.7
    *
    * Implement map, filter, append, and flatMap using foldRight. The append method
    * should be non-strict in its argument.
    */
  def map[B](f: A => B): Stream[B] = ???
  def filter(f: A => Boolean): Stream[A] = ???
  def append[B >: A](s: => Stream[B]): Stream[B] = ???
  def flatMap[B](f: A => Stream[B]): Stream[B] = ???

  /** EXERCISE 5.13
    *
    * Use unfold to implement map, take, takeWhile, zipWith (as in chapter 3), and
    * zipAll. The zipAll function should continue the traversal as long as either stream
    * has more elements—it uses Option to indicate whether each stream has been
    * exhausted.
    */
  def mapWithUnfold[B](f: A => B): Stream[B] = ???
  def takeWithUnfold(n: Int): Stream[A] = ???
  def takeWhileWithUnfold(p: A => Boolean): Stream[A] = ???
  def zipWithWithUnfold[B, C](sb: Stream[B])(f: (A, B) => C): Stream[C] = ???
  def zipAllWithUnfold[B](sb: Stream[B]): Stream[(Option[A], Option[B])] = ???

  /** EXERCISE 5.14
    *
    * Implement startsWith using functions you’ve written. It should check if one
    * Stream is a prefix of another. For instance, Stream(1,2,3) startsWith Stream(1,2)
    * would be true.
    */
  def startsWith[B](sb: Stream[B]): Boolean = ???

  /** EXERCISE 5.15
    * Implement tails using unfold. For a given Stream, tails returns the Stream of suffixes
    * of the input sequence, starting with the original Stream. For example, given
    * Stream(1,2,3), it would return Stream(Stream(1,2,3), Stream(2,3), Stream(3),
    * Stream()).
    */
  def tails: Stream[Stream[A]] = ???

  /** EXERCISE 5.16
    *
    * Generalize tails to the function scanRight, which is like a foldRight that
    * returns a stream of the intermediate results. For example:
    *
    * scala> Stream(1,2,3).scanRight(0)(_ + _).toList
    * res0: List[Int] = List(6,5,3,0)
    *
    * This example should be equivalent to the expression List(1+2+3+0, 2+3+0, 3+0,
    * 0). Your function should reuse intermediate results so that traversing a Stream with n
    * elements always takes time linear in n. Can it be implemented using unfold? How, or
    * why not? Could it be implemented using another function we’ve written?
    */
  def scanRight[B](z: B)(f: (A, => B) => B): Stream[B] = ???
}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {

  /** EXERCISE 5.8
    *
    * Generalize ones slightly to the function constant, which returns an infinite Stream of
    * a given value.
    */
  def constant[A](a: A): Stream[A] = ???

  /** EXERCISE 5.9
    *
    * Write a function that generates an infinite stream of integers, starting from n, then n
    * + 1, n + 2, and so on.
    */
  def from(n: Int): Stream[Int] = ???

  /** EXERCISE 5.10
    *
    * Write a function fibs that generates the infinite stream of Fibonacci numbers: 0, 1, 1,
    * 2, 3, 5, 8, and so on.
    */
  def fibs(): Stream[Int] = ???

  /** EXERCISE 5.11
    *
    * Write a more general stream-building function called unfold. It takes an initial state,
    * and a function for producing both the next state and the next value in the generated
    * stream.
    */
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = ???

  /** EXERCISE 5.12
    *
    * Write fibs, from, constant, and ones in terms of unfold.
    */
  def fibsWithUnfold(): Stream[Int] = ???
  def fromWithUnfold(n: Int): Stream[Int] = ???
  def constantWithUnfold[A](a: A): Stream[A] = ???
  def onesWithUnfold(): Stream[Int] = ???

}
