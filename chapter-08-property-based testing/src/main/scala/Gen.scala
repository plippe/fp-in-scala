package com.github.plippe.fpinscala.chapter08

import fpinscala.state.{RNG, State}

case class Gen[A](sample: State[RNG, A]) {
  def map[B](f: A => B): Gen[B] =
    Gen(sample.map(f))

  /** EXERCISE 8.6
    *
    * Implement flatMap, and then use it to implement this more dynamic version of
    * listOfN. Put flatMap and listOfN in the Gen class.
    */
  def flatMap[B](f: A => Gen[B]): Gen[B] = ???
  def listOfN(size: Gen[Int]): Gen[List[A]] = ???
}

object Gen {

  /** EXERCISE 8.4
    *
    * Implement Gen.choose using this representation of Gen. It should generate integers in
    * the range start to stopExclusive. Feel free to use functions you’ve already written.
    */
  def choose(start: Int, stopExclusive: Int): Gen[Int] = ???

  /** EXERCISE 8.5
    *
    * Let’s see what else we can implement using this representation of Gen. Try implementing
    * unit, boolean, and listOfN.
    */
  def unit[A](a: => A): Gen[A] = ???
  def boolean: Gen[Boolean] = ???
  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] = ???

  /** EXERCISE 8.6
    *
    * Implement flatMap, and then use it to implement this more dynamic version of
    * listOfN. Put flatMap and listOfN in the Gen class.
    */
  def flatMap[A, B](g: Gen[A])(f: A => Gen[B]): Gen[B] = ???
  def listOfNWithFlatMap[A](g: Gen[A])(size: Gen[Int]): Gen[List[A]] = ???

  /** EXERCISE 8.7
    *
    * Implement union, for combining two generators of the same type into one, by pulling
    * values from each generator with equal likelihood.
    */
  def union[A](g1: Gen[A], g2: Gen[A]): Gen[A] = ???

  /** EXERCISE 8.8
    *
    * Implement weighted, a version of union that accepts a weight for each Gen and generates
    * values from each Gen with probability proportional to its weight.
    */
  def weighted[A](g1: (Gen[A], Double), g2: (Gen[A], Double)): Gen[A] = ???

  /** EXERCISE 8.10
    *
    * Implement helper functions for converting Gen to SGen. You can add this as a method
    * on Gen.
    */
  def unsized[A](g: Gen[A]): SGen[A] = ???

  /** EXERCISE 8.12
    *
    * Implement a listOf combinator that doesn’t accept an explicit size. It should return an
    * SGen instead of a Gen. The implementation should generate lists of the requested size.
    */
  def listOf[A](g: Gen[A]): SGen[List[A]] = ???

  /** EXERCISE 8.13
    *
    * Define listOf1 for generating nonempty lists, and then update your specification of
    * max to use this generator.
    */
  def listOf1[A](g: Gen[A]): SGen[List[A]] = ???

  /** EXERCISE 8.14
    *
    * Write a property to verify the behavior of List.sorted (API docs link: http://mng.bz/
    * Pz86), which you can use to sort (among other things) a List[Int]. For instance,
    * List(2,1,3).sorted is equal to List(1,2,3).
    */
  def sortedProp: Int = ???

}

case class SGen[A](forSize: Int => Gen[A]) {

  /** EXERCISE 8.11
  *
  * Not surprisingly, SGen at a minimum supports many of the same operations as Gen,
  * and the implementations are rather mechanical. Define some convenience functions
  * on SGen that simply delegate to the corresponding functions on Gen.
  */
}
