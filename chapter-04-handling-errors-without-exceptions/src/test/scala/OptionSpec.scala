package com.github.plippe.fpinscala.chapter04

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter04.gen._

class OptionSpec extends FunSuite with PropertyChecks {

  test("map") {
    def f(a: Int) = a + 1

    forAll { o: Option[Int] =>
      val result = o.map(f)
      val expected = Answers.Option.map(o)(f)

      assert(expected == result)
    }
  }

  test("flatMap") {
    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val result = oa.flatMap(_ => ob)
      val expected = Answers.Option.flatMap(oa)(_ => ob)

      assert(expected == result)
    }
  }

  test("getOrElse") {
    forAll { (o: Option[Int], n: Int) =>
      val result = o.getOrElse(n)
      val expected = Answers.Option.getOrElse(o)(n)

      assert(expected == result)
    }
  }

  test("orElse") {
    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val result = oa.orElse(ob)
      val expected = Answers.Option.orElse(oa)(ob)

      assert(expected == result)
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { o: Option[Int] =>
      val result = o.filter(f)
      val expected = Answers.Option.filter(o)(f)

      assert(expected == result)
    }
  }

  test("variance") {
    forAll { xs: List[Double] =>
      val result = Option.variance(xs)
      val expected = Answers.Option.variance(xs)

      assert(expected == result)
    }
  }

  test("map2") {
    def f(a: Int, b: Int) = Right(a + b)

    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val result = Option.map2(oa, ob)(f)
      val expected = Answers.Option.map2(oa, ob)(f)

      assert(expected == result)
    }
  }

  test("sequence") {
    forAll { os: List[Option[Int]] =>
      val result = Option.sequence(os)
      val expected = Answers.Option.sequence(os)

      assert(expected == result)
    }
  }

  test("traverse") {
    forAll { (os: List[Option[Int]], o: Option[Int]) =>
      val result = Option.traverse(os)(_ => o)
      val expected = Answers.Option.traverse(os)(_ => o)

      assert(expected == result)
    }
  }

  test("sequenceWithTraverse") {
    forAll { os: List[Option[Int]] =>
      val result = Option.sequenceWithTraverse(os)
      val expected = Answers.Option.sequence(os)

      assert(expected == result)
    }
  }

}
