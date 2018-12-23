package com.github.plippe.fpinscala.chapter06

import org.scalacheck.{Arbitrary, Gen}
import com.github.plippe.fpinscala.chapter06.State._

package object gen {

  implicit def arbitraryRng = Arbitrary(genRng)
  def genRng: Gen[RNG] = Arbitrary.arbitrary[Long].map(SimpleRNG.apply)

  implicit def arbitraryRand[T: Arbitrary] = Arbitrary(genRand[T])
  def genRand[T: Arbitrary]: Gen[RNG.Rand[T]] =
    Arbitrary.arbitrary[T].map { value: T => (rng: RNG) =>
      (value, rng)
    }

  implicit def arbitraryState[S, T: Arbitrary] = Arbitrary(genState[S, T])
  def genState[S, T: Arbitrary]: Gen[State[S, T]] =
    for {
      value <- Arbitrary.arbitrary[T]
    } yield State(s => (value, s))

  implicit def arbitraryInput = Arbitrary(genInput)
  def genInput: Gen[Input] = Gen.oneOf(Coin, Turn)

  implicit def arbitraryMachine = Arbitrary(genMachine)
  def genMachine: Gen[Machine] =
    for {
      locked <- Arbitrary.arbitrary[Boolean]
      candies <- Arbitrary.arbitrary[Int]
      coins <- Arbitrary.arbitrary[Int]
    } yield Machine(locked, candies, coins)

}
