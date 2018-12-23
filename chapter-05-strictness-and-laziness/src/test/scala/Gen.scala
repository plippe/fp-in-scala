package com.github.plippe.fpinscala.chapter05

import org.scalacheck.{Arbitrary, Gen}

package object gen {

  implicit def arbitraryStream[T: Arbitrary] = Arbitrary(genStream[T])
  def genStream[T: Arbitrary]: Gen[Stream[T]] =
    Gen.oneOf(Gen.const(Stream.empty[T]), genCons[T])

  implicit def arbitraryCons[T: Arbitrary] = Arbitrary(genCons[T])
  def genCons[T: Arbitrary]: Gen[Cons[T]] =
    for {
      head <- Arbitrary.arbitrary[T]
      tail <- genStream[T]
    } yield Stream.cons(head, tail).asInstanceOf[Cons[T]]

}
