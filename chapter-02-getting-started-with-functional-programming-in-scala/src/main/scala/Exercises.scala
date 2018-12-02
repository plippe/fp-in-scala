package com.github.plippe.fpinscala.chapter02

object Exercises {

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)
    go(n, 1)
  }

  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    @annotation.tailrec
    def loop(n: Int): Int =
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)
    loop(0)
  }

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
    (b: B) => f(a, b)

  /** EXERCISE 2.1
    *
    * Write a recursive function to get the nth Fibonacci number (http://mng.bz/C29s).
    * The first two Fibonacci numbers are 0 and 1. The nth number is always the sum of the
    * previous two—the sequence begins 0, 1, 1, 2, 3, 5. Your definition should use a
    * local tail-recursive function.
    */
  def fib(n: Int): Int = ???

  /** EXERCISE 2.2
    *
    * Implement isSorted, which checks whether an Array[A] is sorted according to a
    * given comparison function:
    */
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = ???

  /** EXERCISE 2.3
    *
    * Let’s look at another example, currying, which converts a function f of two arguments
    * into a function of one argument that partially applies f. Here again there’s only one
    * implementation that compiles. Write this implementation.
    */
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = ???

  /** EXERCISE 2.4
    *
    * Implement uncurry, which reverses the transformation of curry. Note that since =>
    * associates to the right, A => (B => C) can be written as A => B => C.
    */
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = ???

  /** EXERCISE 2.5
    *
    * Implement the higher-order function that composes two functions.
    */
  def compose[A, B, C](f: B => C, g: A => B): A => C = ???

}
