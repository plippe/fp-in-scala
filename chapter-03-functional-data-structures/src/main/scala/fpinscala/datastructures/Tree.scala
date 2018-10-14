// https://github.com/fpinscala/fpinscala/blob/master/exercises/src/main/scala/fpinscala/datastructures/Tree.scala
package fpinscala.datastructures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {}
