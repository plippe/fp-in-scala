package com.github.plippe.fpinscala.chapter05

import org.scalacheck.{Arbitrary, Gen => OfficialGen}

object Gen {

  implicit def arbitraryStreamGen[T: Arbitrary] = Arbitrary(streamGen[T])
  def streamGen[T: Arbitrary]: OfficialGen[Stream[T]] =
    OfficialGen.oneOf(OfficialGen.const(Empty), consGen[T])

  implicit def arbitraryConsGen[T: Arbitrary] = Arbitrary(consGen[T])
  def consGen[T: Arbitrary]: OfficialGen[Cons[T]] =
    for {
      head <- Arbitrary.arbitrary[T]
      tail <- streamGen[T]
    } yield Cons(() => head, () => tail)

}
