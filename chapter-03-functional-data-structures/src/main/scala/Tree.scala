package com.github.plippe.fpinscala.chapter03

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  /** EXERCISE 3.25
    *
    * Write a function size that counts the number of nodes (leaves and branches) in a tree.
    */
  def size[A](t: Tree[A]): Int = ???

  /** EXERCISE 3.26
    *
    * Write a function maximum that returns the maximum element in a Tree[Int]. (Note:
    * In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x
    * and y.)
    */
  def maximum(t: Tree[Int]): Int = ???

  /** EXERCISE 3.27
    *
    * Write a function depth that returns the maximum path length from the root of a tree
    * to any leaf.
    */
  def depth[A](t: Tree[A]): Int = ???

  /** EXERCISE 3.28
    *
    * Write a function map, analogous to the method of the same name on List, that modifies
    * each element in a tree with a given function.
    */
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = ???

  /** EXERCISE 3.29
    *
    * Generalize size, maximum, depth, and map, writing a new function fold that abstracts
    * over their similarities. Reimplement them in terms of this more general function. Can
    * you draw an analogy between this fold function and the left and right folds for List?
    */
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = ???
  def sizeWithFold[A](t: Tree[A]): Int = ???
  def maximumWithFold(t: Tree[Int]): Int = ???
  def depthWithFold[A](t: Tree[A]): Int = ???
  def mapWithFold[A, B](t: Tree[A])(f: A => B): Tree[B] = ???
}
