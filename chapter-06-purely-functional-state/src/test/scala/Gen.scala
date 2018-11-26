package com.github.plippe.fpinscala.chapter06

import org.scalacheck.{Arbitrary, Gen => OfficialGen}
import com.github.plippe.fpinscala.chapter06.State._

object Gen {

  implicit def arbitraryRngGen = Arbitrary(rngGen)
  def rngGen: OfficialGen[RNG] = Arbitrary.arbitrary[Long].map(RNG.apply)

  implicit def arbitraryRandGen[T: Arbitrary] = Arbitrary(randGen[T])
  def randGen[T: Arbitrary]: OfficialGen[RNG.Rand[T]] =
    Arbitrary.arbitrary[T].map { value: T => (rng: RNG) =>
      (value, rng)
    }

  implicit def arbitraryStateGen[S, T: Arbitrary] = Arbitrary(stateGen[S, T])
  def stateGen[S, T: Arbitrary]: OfficialGen[State[S, T]] =
    for {
      value <- Arbitrary.arbitrary[T]
    } yield State(s => (value, s))

  implicit def arbitraryInputGen = Arbitrary(inputGen)
  def inputGen: OfficialGen[Input] = OfficialGen.oneOf(Coin, Turn)

  implicit def arbitraryMachineGen = Arbitrary(machineGen)
  def machineGen: OfficialGen[Machine] =
    for {
      locked <- Arbitrary.arbitrary[Boolean]
      candies <- Arbitrary.arbitrary[Int]
      coins <- Arbitrary.arbitrary[Int]
    } yield Machine(locked, candies, coins)

}
