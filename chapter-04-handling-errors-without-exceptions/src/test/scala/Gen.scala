package com.github.plippe.fpinscala.chapter04

import org.scalacheck.{Arbitrary, Gen => OfficialGen}

object Gen {

  implicit def arbitraryOptionGen[A: Arbitrary] = Arbitrary(optionGen[A])
  def optionGen[A: Arbitrary]: OfficialGen[Option[A]] =
    OfficialGen.oneOf(
      OfficialGen.const(None),
      Arbitrary.arbitrary[A].map(Some.apply)
    )

  implicit def arbitraryEitherGen[A: Arbitrary, B: Arbitrary] =
    Arbitrary(eitherGen[A, B])
  def eitherGen[A: Arbitrary, B: Arbitrary]: OfficialGen[Either[A, B]] =
    OfficialGen.oneOf(
      Arbitrary.arbitrary[A].map(Left.apply),
      Arbitrary.arbitrary[B].map(Right.apply)
    )

}
