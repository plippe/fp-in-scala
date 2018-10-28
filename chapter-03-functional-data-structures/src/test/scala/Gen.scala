package com.github.plippe.fpinscala.chapter03

import org.scalacheck.{Arbitrary, Gen => OfficialGen}

object Gen {

  implicit def arbitraryListGen[T: Arbitrary] = Arbitrary(listGen[T])
  def listGen[T: Arbitrary]: OfficialGen[List[T]] =
    OfficialGen.oneOf(OfficialGen.const(Nil), consGen[T])

  implicit def arbitraryConsGen[T: Arbitrary] = Arbitrary(consGen[T])
  def consGen[T: Arbitrary]: OfficialGen[Cons[T]] =
    for {
      head <- Arbitrary.arbitrary[T]
      tail <- listGen[T]
    } yield Cons(head, tail)

  implicit def arbitraryTreeGen[T: Arbitrary] = Arbitrary(treeGen[T](5))
  def treeGen[T: Arbitrary](maxDepth: Int): OfficialGen[Tree[T]] =
    if (maxDepth <= 0) leafGen[T]
    else OfficialGen.oneOf(leafGen[T], branchGen[T](maxDepth))

  implicit def arbitraryLeafGen[T: Arbitrary] = Arbitrary(leafGen[T])
  def leafGen[T: Arbitrary]: OfficialGen[Leaf[T]] =
    Arbitrary.arbitrary[T].map(Leaf.apply)

  implicit def arbitraryBranchGen[T: Arbitrary] = Arbitrary(branchGen[T](5))
  def branchGen[T: Arbitrary](maxDepth: Int): OfficialGen[Branch[T]] =
    for {
      left <- treeGen[T](maxDepth - 1)
      right <- treeGen[T](maxDepth - 1)
    } yield Branch(left, right)

}
