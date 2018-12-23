package com.github.plippe.fpinscala.chapter04

import org.scalatest._
import org.scalatest.prop.{PropertyChecks, Whenever}

import com.github.plippe.fpinscala.chapter04.gen._

class EitherSpec extends FunSuite with PropertyChecks with Whenever {

  test("map") {
    def f(a: Int) = a + 1

    forAll { e: Either[Int, Int] =>
      val expected = Answers.Either.map(e)(f)
      val result = e.map(f)

      assert(expected == result)
    }
  }

  test("flatMap") {
    forAll { (ea: Either[Int, Int], eb: Either[Int, Int]) =>
      val expected = Answers.Either.flatMap(ea)(_ => eb)
      val result = ea.flatMap(_ => eb)

      assert(expected == result)
    }
  }

  test("orElse") {
    forAll { (ea: Either[Int, Int], eb: Either[Int, Int]) =>
      val expected = Answers.Either.orElse(ea)(eb)
      val result = ea.orElse(eb)

      assert(expected == result)
    }
  }

  test("map2") {
    def f(a: Int, b: Int) = Right(a + b)

    forAll { (ea: Either[Int, Int], eb: Either[Int, Int]) =>
      val expected = Answers.Either.map2(ea, eb)(f)
      val result = ea.map2(eb)(f)

      assert(expected == result)
    }
  }

  test("sequence") {
    forAll { es: List[Either[Int, Int]] =>
      val expected = Answers.Either.sequence(es)
      val result = Either.sequence(es)

      assert(expected == result)
    }
  }

  test("traverse") {
    forAll { (es: List[Either[Int, Int]], e: Either[Int, Int]) =>
      val expected = Answers.Either.traverse(es)(_ => e)
      val result = Either.traverse(es)(_ => e)

      assert(expected == result)
    }
  }

}
