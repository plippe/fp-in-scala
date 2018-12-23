package com.github.plippe.fpinscala.chapter04

import org.scalacheck.{Arbitrary, Gen}

package object gen {

  implicit def arbitraryOption[A: Arbitrary] = Arbitrary(genOption[A])
  def genOption[A: Arbitrary]: Gen[Option[A]] =
    Gen.oneOf(
      Gen.const(None),
      Arbitrary.arbitrary[A].map(Some.apply)
    )

  implicit def arbitraryEither[A: Arbitrary, B: Arbitrary] =
    Arbitrary(genEither[A, B])
  def genEither[A: Arbitrary, B: Arbitrary]: Gen[Either[A, B]] =
    Gen.oneOf(
      Arbitrary.arbitrary[A].map(Left.apply),
      Arbitrary.arbitrary[B].map(Right.apply)
    )

}
