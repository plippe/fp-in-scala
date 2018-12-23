package com.github.plippe.fpinscala.chapter04

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter04.gen._

class OptionSpec extends FunSuite with PropertyChecks {

  test("map") {
    def f(a: Int) = a + 1

    forAll { o: Option[Int] =>
      val expected = Answers.Option.map(o)(f)
      val result = o.map(f)

      assert(expected == result)
    }
  }

  test("flatMap") {
    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val expected = Answers.Option.flatMap(oa)(_ => ob)
      val result = oa.flatMap(_ => ob)

      assert(expected == result)
    }
  }

  test("getOrElse") {
    forAll { (o: Option[Int], n: Int) =>
      val expected = Answers.Option.getOrElse(o)(n)
      val result = o.getOrElse(n)

      assert(expected == result)
    }
  }

  test("orElse") {
    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val expected = Answers.Option.orElse(oa)(ob)
      val result = oa.orElse(ob)

      assert(expected == result)
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { o: Option[Int] =>
      val expected = Answers.Option.filter(o)(f)
      val result = o.filter(f)

      assert(expected == result)
    }
  }

  test("variance") {
    forAll { xs: List[Double] =>
      val expected = Answers.Option.variance(xs)
      val result = Option.variance(xs)

      assert(expected == result)
    }
  }

  test("map2") {
    def f(a: Int, b: Int) = Right(a + b)

    forAll { (oa: Option[Int], ob: Option[Int]) =>
      val expected = Answers.Option.map2(oa, ob)(f)
      val result = Option.map2(oa, ob)(f)

      assert(expected == result)
    }
  }

  test("sequence") {
    forAll { os: List[Option[Int]] =>
      val expected = Answers.Option.sequence(os)
      val result = Option.sequence(os)

      assert(expected == result)
    }
  }

  test("traverse") {
    forAll { (os: List[Option[Int]], o: Option[Int]) =>
      val expected = Answers.Option.traverse(os)(_ => o)
      val result = Option.traverse(os)(_ => o)

      assert(expected == result)
    }
  }

  test("sequenceWithTraverse") {
    forAll { os: List[Option[Int]] =>
      val expected = Answers.Option.sequence(os)
      val result = Option.sequenceWithTraverse(os)

      assert(expected == result)
    }
  }

}
