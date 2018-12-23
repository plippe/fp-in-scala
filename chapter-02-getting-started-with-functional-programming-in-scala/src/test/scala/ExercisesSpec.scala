package com.github.plippe.fpinscala.chapter02

import org.scalatest._
import org.scalatest.prop.PropertyChecks
import org.scalacheck.Gen

class ExercisesSpec extends FunSuite with PropertyChecks {

  test("fib") {
    forAll(Gen.chooseNum[Int](0, 45)) { n =>
      val result = Exercises.fib(n)
      val expected = Answers.fib(n)

      assert(expected == result)
    }
  }

  test("isSorted") {
    def ordered(a: Int, b: Int) = a <= b

    forAll { as: Array[Int] =>
      val result = Exercises.isSorted(as, ordered)
      val expected = Answers.isSorted(as, ordered)

      assert(expected == result)
    }
  }

  test("curry") {
    def sum(a: Int, b: Int): Int = a + b

    forAll { (a: Int, b: Int) =>
      val result = Exercises.curry(sum)(a)(b)
      val expected = Answers.curry(sum)(a)(b)

      assert(expected == result)
    }
  }

  test("uncurry") {
    def sum(a: Int)(b: Int): Int = a + b

    forAll { (a: Int, b: Int) =>
      val result = Exercises.uncurry(sum)(a, b)
      val expected = Answers.uncurry(sum)(a, b)

      assert(expected == result)
    }
  }

  test("compose") {
    def add2(a: Int) = a + 2
    def multiply2(a: Int) = a * 2

    forAll { a: Int =>
      val result = Exercises.compose(multiply2, add2)(a)
      val expected = Answers.compose(multiply2, add2)(a)

      assert(expected == result)
    }
  }

}
