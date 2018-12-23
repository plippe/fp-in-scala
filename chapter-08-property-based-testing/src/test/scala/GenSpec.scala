package com.github.plippe.fpinscala.chapter08

import com.github.plippe.fpinscala.chapter08.gen._
import fpinscala.state.RNG
import org.scalatest._
import org.scalatest.prop.PropertyChecks

class GenSpec extends FunSuite with PropertyChecks {

  test("choose") {
    forAll { (rng: RNG, start: Int, stop: Int) =>
      val result = Gen.choose(start, stop).sample.run(rng)
      val expected = Answers.Gen.choose(start, stop).sample.run(rng)

      assert(expected == result)
    }
  }

  test("unit") {
    forAll { (rng: RNG, value: Int) =>
      val result = Gen.unit(value).sample.run(rng)
      val expected = Answers.Gen.unit(value).sample.run(rng)

      assert(expected == result)
    }
  }

  test("boolean") {
    forAll { (rng: RNG) =>
      val result = Gen.boolean.sample.run(rng)
      val expected = Answers.Gen.boolean.sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOfN") {
    forAll { (rng: RNG, n: Byte, g: Gen[Int]) =>
      val result = Gen.listOfN(n.toInt, g).sample.run(rng)
      val expected = Answers.Gen.listOfN(n.toInt, g).sample.run(rng)

      assert(expected == result)
    }
  }

  test("flatMap") {
    val f = (a: Int) => Gen.unit(a)
    forAll { (rng: RNG, g: Gen[Int]) =>
      val result = g.flatMap(f).sample.run(rng)
      val expected = Answers.Gen.flatMap(g)(f).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOfNWithFlatMap") {
    forAll { (rng: RNG, n: Byte, g: Gen[Int]) =>
      val result = g.listOfN(Gen.unit(n.toInt)).sample.run(rng)
      val expected = Answers.Gen.listOfN(n.toInt, g).sample.run(rng)

      assert(expected == result)
    }
  }

  test("union") {
    forAll { (rng: RNG, ga: Gen[Int], gb: Gen[Int]) =>
      val result = Gen.union(ga, gb).sample.run(rng)
      val expected = Answers.Gen.union(ga, gb).sample.run(rng)

      assert(expected == result)
    }
  }

  test("weighted") {
    forAll { (rng: RNG, ga: Gen[Int], wa: Byte, gb: Gen[Int], wb: Byte) =>
      val a = (ga, wa.toDouble.abs)
      val b = (gb, wb.toDouble.abs)
      val result = Gen.weighted(a, b).sample.run(rng)
      val expected = Answers.Gen.weighted(a, b).sample.run(rng)

      assert(expected == result)
    }
  }

  test("unsized") {
    forAll { (rng: RNG, n: Byte, g: Gen[Int]) =>
      val result = Gen.unsized(g).forSize(n.toInt).sample.run(rng)
      val expected = Answers.Gen.unsized(g).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOf") {
    forAll { (rng: RNG, n: Byte, g: Gen[Int]) =>
      val result = Gen.listOf(g).forSize(n.toInt).sample.run(rng)
      val expected = Answers.Gen.listOf(g).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOf1") {
    forAll { (rng: RNG, n: Byte, g: Gen[Int]) =>
      val result = Gen.listOf1(g).forSize(n.toInt).sample.run(rng)
      val expected = Answers.Gen.listOf1(g).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  ignore("sortedProp") {}

}
