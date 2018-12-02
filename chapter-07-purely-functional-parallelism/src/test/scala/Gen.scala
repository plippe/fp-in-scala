package com.github.plippe.fpinscala.chapter07

import org.scalacheck.{Arbitrary, Gen => OfficialGen}

object Gen {

  implicit def arbitraryParGen[T: Arbitrary] = Arbitrary(parGen[T])
  def parGen[T: Arbitrary]: OfficialGen[Par.Par[T]] =
    Arbitrary.arbitrary[T].map(Par.unit)

}
