package com.github.plippe.fpinscala.chapter03

import org.scalacheck.{Arbitrary, Gen}

package object gen {

  implicit def arbitraryList[T: Arbitrary] = Arbitrary(genList[T])
  def genList[T: Arbitrary]: Gen[List[T]] =
    Gen.oneOf(Gen.const(Nil), genCons[T])

  implicit def arbitraryCons[T: Arbitrary] = Arbitrary(genCons[T])
  def genCons[T: Arbitrary]: Gen[Cons[T]] =
    for {
      head <- Arbitrary.arbitrary[T]
      tail <- genList[T]
    } yield Cons(head, tail)

  implicit def arbitraryTree[T: Arbitrary] = Arbitrary(genTree[T](5))
  def genTree[T: Arbitrary](maxDepth: Int): Gen[Tree[T]] =
    if (maxDepth <= 0) genLeaf[T]
    else Gen.oneOf(genLeaf[T], genBranch[T](maxDepth))

  implicit def arbitraryLeaf[T: Arbitrary] = Arbitrary(genLeaf[T])
  def genLeaf[T: Arbitrary]: Gen[Leaf[T]] =
    Arbitrary.arbitrary[T].map(Leaf.apply)

  implicit def arbitraryBranch[T: Arbitrary] = Arbitrary(genBranch[T](5))
  def genBranch[T: Arbitrary](maxDepth: Int): Gen[Branch[T]] =
    for {
      left <- genTree[T](maxDepth - 1)
      right <- genTree[T](maxDepth - 1)
    } yield Branch(left, right)

}
