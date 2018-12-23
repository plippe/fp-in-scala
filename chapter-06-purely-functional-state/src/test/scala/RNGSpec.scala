package com.github.plippe.fpinscala.chapter06

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter06.gen._

class RNGSpec extends FunSuite with PropertyChecks {

  test("nonNegativeInt") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.nonNegativeInt(rng)
      val result = RNG.nonNegativeInt(rng)

      assert(expected == result)
    }
  }

  test("double") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.double(rng)
      val result = RNG.double(rng)

      assert(expected == result)
    }
  }

  test("intDouble") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.intDouble(rng)
      val result = RNG.intDouble(rng)

      assert(expected == result)
    }
  }

  test("doubleInt") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.doubleInt(rng)
      val result = RNG.doubleInt(rng)

      assert(expected == result)
    }
  }

  test("double3") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.double3(rng)
      val result = RNG.double3(rng)

      assert(expected == result)
    }
  }

  test("ints") {
    forAll { (n: Int, rng: RNG) =>
      val expected = Answers.RNG.ints(n)(rng)
      val result = RNG.ints(n)(rng)

      assert(expected == result)
    }
  }

  test("doubleWithMap") {
    forAll { rng: RNG =>
      val expected = Answers.RNG.double(rng)
      val result = RNG.doubleWithMap(rng)

      assert(expected == result)
    }
  }

  test("map2") {
    forAll { (rng: RNG, randa: RNG.Rand[Int], randb: RNG.Rand[Int]) =>
      val expected = Answers.RNG.map2(randa, randb)(_ + _)(rng)
      val result = RNG.map2(randa, randb)(_ + _)(rng)

      assert(expected == result)
    }
  }

  test("sequence") {
    forAll { (rng: RNG, rands: List[RNG.Rand[Int]]) =>
      val expected = Answers.RNG.sequence(rands)(rng)
      val result = RNG.sequence(rands)(rng)

      assert(expected == result)
    }
  }

  test("flatMap") {
    forAll { (rng: RNG, randa: RNG.Rand[Int], randb: RNG.Rand[Int]) =>
      val expected = Answers.RNG.flatMap(randa)(_ => randb)(rng)
      val result = RNG.flatMap(randa)(_ => randb)(rng)

      assert(expected == result)
    }
  }

  test("nonNegativeLessThan") {
    forAll { (n: Int, rng: RNG) =>
      val expected = Answers.RNG.nonNegativeLessThan(n)(rng)
      val result = RNG.nonNegativeLessThan(n)(rng)

      assert(expected == result)
    }
  }

  test("mapWithFlatMap") {
    forAll { (rng: RNG, rand: RNG.Rand[Int]) =>
      val expected = Answers.RNG.map(rand)(_ + 1)(rng)
      val result = RNG.mapWithFlatMap(rand)(_ + 1)(rng)

      assert(expected == result)
    }
  }

  test("map2WithFlatMap") {
    forAll { (rng: RNG, randa: RNG.Rand[Int], randb: RNG.Rand[Int]) =>
      val expected = Answers.RNG.map2(randa, randb)(_ + _)(rng)
      val result = RNG.map2WithFlatMap(randa, randb)(_ + _)(rng)

      assert(expected == result)
    }
  }

}
