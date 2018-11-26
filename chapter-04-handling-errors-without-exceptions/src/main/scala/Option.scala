package com.github.plippe.fpinscala.chapter04

sealed trait Option[+A] {

  /** EXERCISE 4.1
    *
    * Implement all of the preceding functions on Option. As you implement each function,
    * try to think about what it means and in what situations you’d use it. We’ll explore when
    * to use each of these functions next. Here are a few hints for solving this exercise:
    *   - It’s fine to use pattern matching, though you should be able to implement all
    *     the functions besides map and getOrElse without resorting to pattern matching.
    *   - For map and flatMap, the type signature should be enough to determine the
    *     implementation.
    *   - getOrElse returns the result inside the Some case of the Option, or if the Option
    *     is None, returns the given default value.
    *   - orElse returns the first Option if it’s defined; otherwise, it returns the second
    *     Option
    */
  def map[B](f: A => B): Option[B] = ???
  def flatMap[B](f: A => Option[B]): Option[B] = ???
  def getOrElse[B >: A](default: => B): B = ???
  def orElse[B >: A](ob: => Option[B]): Option[B] = ???
  def filter(f: A => Boolean): Option[A] = ???

}

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Option {

  /** EXERCISE 4.2
    *
    * Implement the variance function in terms of flatMap. If the mean of a sequence is m,
    * the variance is the mean of math.pow(x - m, 2) for each element x in the sequence.
    * See the definition of variance on Wikipedia (http://mng.bz/0Qsr).
    */
  def variance(xs: Seq[Double]): Option[Double] = ???

  /** EXERCISE 4.3
    *
    * Write a generic function map2 that combines two Option values using a binary function.
    * If either Option value is None, then the return value is too. Here is its signature:
    */
  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = ???

  /** EXERCISE 4.4
    *
    * Write a function sequence that combines a list of Options into one Option containing
    * a list of all the Some values in the original list. If the original list contains None even
    * once, the result of the function should be None; otherwise the result should be Some
    * with a list of all the values. Here is its signature:
    */
  def sequence[A](a: List[Option[A]]): Option[List[A]] = ???

  /** EXERCISE 4.5
    *
    * Implement this function. It’s straightforward to do using map and sequence, but try
    * for a more efficient implementation that only looks at the list once. In fact, implement
    * sequence in terms of traverse.
    */
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = ???
  def sequenceWithTraverse[A](a: List[Option[A]]): Option[List[A]] = ???

}
