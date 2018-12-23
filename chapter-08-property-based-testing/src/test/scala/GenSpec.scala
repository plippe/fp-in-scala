package com.github.plippe.fpinscala.chapter08

import com.github.plippe.fpinscala.chapter08.gen._
import fpinscala.state.RNG
import org.scalatest._
import org.scalatest.prop.PropertyChecks

class GenSpec extends FunSuite with PropertyChecks {

  test("choose") {
    forAll { (rng: RNG, start: Int, stop: Int) =>
      val expected = Answers.Gen.choose(start, stop).sample.run(rng)
      val result = Gen.choose(start, stop).sample.run(rng)

      assert(expected == result)
    }
  }

  test("unit") {
    forAll { (rng: RNG, value: Int) =>
      val expected = Answers.Gen.unit(value).sample.run(rng)
      val result = Gen.unit(value).sample.run(rng)

      assert(expected == result)
    }
  }

  test("boolean") {
    forAll { (rng: RNG) =>
      val expected = Answers.Gen.boolean.sample.run(rng)
      val result = Gen.boolean.sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOfN") {
    forAll { (rng: RNG, n: Byte, value: Int) =>
      val g = Gen.unit(value)
      val expected = Answers.Gen.listOfN(n.toInt, g).sample.run(rng)
      val result = Gen.listOfN(n.toInt, g).sample.run(rng)

      assert(expected == result)
    }
  }

  test("flatMap") {
    val f = (value: Int) => Gen.unit(value)
    forAll { (rng: RNG, value: Int) =>
      val g = Gen.unit(value)
      val expected = Answers.Gen.flatMap(g)(f).sample.run(rng)
      val result = g.flatMap(f).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOfNWithFlatMap") {
    forAll { (rng: RNG, n: Byte, value: Int) =>
      val g = Gen.unit(value)
      val expected = Answers.Gen.listOfN(n.toInt, g).sample.run(rng)
      val result = g.listOfN(Gen.unit(n.toInt)).sample.run(rng)

      assert(expected == result)
    }
  }

  test("union") {
    forAll { (rng: RNG, av: Int, bv: Int) =>
      val a = Gen.unit(av)
      val b = Gen.unit(bv)
      val expected = Answers.Gen.union(a, b).sample.run(rng)
      val result = Gen.union(a, b).sample.run(rng)

      assert(expected == result)
    }
  }

  test("weighted") {
    forAll { (rng: RNG, av: Int, aw: Byte, bv: Int, bw: Byte) =>
      val a = (Gen.unit(av), aw.toDouble.abs)
      val b = (Gen.unit(bv), bw.toDouble.abs)
      val expected = Answers.Gen.weighted(a, b).sample.run(rng)
      val result = Gen.weighted(a, b).sample.run(rng)

      assert(expected == result)
    }
  }

  test("unsized") {
    forAll { (rng: RNG, n: Byte, av: Int) =>
      val a = Gen.unit(av)
      val expected = Answers.Gen.unsized(a).forSize(n.toInt).sample.run(rng)
      val result = Gen.unsized(a).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOf") {
    forAll { (rng: RNG, n: Byte, av: Int) =>
      val a = Gen.unit(av)
      val expected = Answers.Gen.listOf(a).forSize(n.toInt).sample.run(rng)
      val result = Gen.listOf(a).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  test("listOf1") {
    forAll { (rng: RNG, n: Byte, av: Int) =>
      val a = Gen.unit(av)
      val expected = Answers.Gen.listOf1(a).forSize(n.toInt).sample.run(rng)
      val result = Gen.listOf1(a).forSize(n.toInt).sample.run(rng)

      assert(expected == result)
    }
  }

  ignore("sortedProp") {}

}
