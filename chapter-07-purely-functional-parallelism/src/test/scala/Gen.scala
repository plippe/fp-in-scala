package com.github.plippe.fpinscala.chapter07

import org.scalacheck.{Arbitrary, Gen}

package object gen {

  implicit def arbitraryPar[T: Arbitrary] = Arbitrary(genPar[T])
  def genPar[T: Arbitrary]: Gen[Par.Par[T]] =
    Arbitrary.arbitrary[T].map(Par.unit)

}
