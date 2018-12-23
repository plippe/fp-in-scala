package com.github.plippe.fpinscala.chapter08

import fpinscala.state.RNG
import org.scalacheck.{Arbitrary, Gen => OfficialGen}

package object gen {

  implicit def arbitraryRng = Arbitrary(genRng)
  def genRng: OfficialGen[RNG] = Arbitrary.arbitrary[Long].map(RNG.Simple)

  implicit def arbitraryGen[T: Arbitrary] = Arbitrary(genGen[T])
  def genGen[T: Arbitrary]: OfficialGen[Gen[T]] =
    Arbitrary.arbitrary[T].map(t => Gen.unit(t))

  implicit def arbitraryResult = Arbitrary(genResult)
  def genResult: OfficialGen[Prop.Result] =
    OfficialGen.oneOf(OfficialGen.const(Prop.Passed), genFalsified)

  def genFalsified: OfficialGen[Prop.Falsified] =
    for {
      failure <- Arbitrary.arbitrary[String]
      successes <- Arbitrary.arbitrary[Int]
    } yield Prop.Falsified(failure, successes)

  implicit def arbitraryProp1 = Arbitrary(genProp1)
  def genProp1: OfficialGen[Prop1] =
    Arbitrary.arbitrary[Boolean].map { b =>
      Prop1(() => b)
    }

  implicit def arbitraryProp2 = Arbitrary(genProp2)
  def genProp2: OfficialGen[Prop2] =
    Arbitrary.arbitrary[Prop.Result].map { b =>
      Prop2((_, _) => b)
    }

}
