package com.github.plippe.fpinscala.chapter07

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter07.gen._

class ParSpec extends FunSuite with PropertyChecks {

  test("map2") {
    def f(a: Int, b: Int) = a + b

    forAll { (a: Par.Par[Int], b: Par.Par[Int]) =>
      val result = Par.map2(a, b)(f)
      val expected = Answers.Par.map2(a, b)(f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("asyncF") {
    def f(a: Int) = a + 1

    forAll { a: Int =>
      val result = Par.asyncF(f)(a)
      val expected = Answers.Par.asyncF(f)(a)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("sequence") {
    forAll { ps: List[Par.Par[Int]] =>
      val result = Par.sequence(ps)
      val expected = Answers.Par.sequence(ps)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("parFilter") {
    def f(a: Int) = a > 0

    forAll { as: List[Int] =>
      val result = Par.parFilter(as)(f)
      val expected = Answers.Par.parFilter(as)(f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("choiceN") {
    forAll { (n: Par.Par[Int], choices: List[Par.Par[Int]]) =>
      val result = Par.choiceN(n)(choices)
      val expected = Answers.Par.choiceN(n)(choices)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("choiceWithChoiceN") {
    forAll { (cond: Par.Par[Boolean], t: Par.Par[Int], f: Par.Par[Int]) =>
      val result = Par.choiceWithChoiceN(cond)(t, f)
      val expected = Answers.Par.choice(cond)(t, f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("choiceMap") {
    forAll { (key: Par.Par[Int], choices: Map[Int, Par.Par[Int]]) =>
      val result = Par.choiceMap(key)(choices)
      val expected = Answers.Par.choiceMap(key)(choices)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("chooser") {
    def f(a: Int) = Par.unit(a + 1)

    forAll { (pa: Par.Par[Int]) =>
      val result = Par.chooser(pa)(f)
      val expected = Answers.Par.chooser(pa)(f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("choiceNWithChooser") {
    forAll { (n: Par.Par[Int], choices: List[Par.Par[Int]]) =>
      val result = Par.choiceNWithChooser(n)(choices)
      val expected = Answers.Par.choiceN(n)(choices)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("choiceWithChooser") {
    forAll { (cond: Par.Par[Boolean], t: Par.Par[Int], f: Par.Par[Int]) =>
      val result = Par.choiceWithChooser(cond)(t, f)
      val expected = Answers.Par.choice(cond)(t, f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("join") {
    forAll { a: Par.Par[Par.Par[Int]] =>
      val result = Par.join(a)
      val expected = Answers.Par.join(a)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("flatMapWithJoin") {
    def f(a: Int) = Par.unit(a + 1)

    forAll { (pa: Par.Par[Int]) =>
      val result = Par.flatMapWithJoin(pa)(f)
      val expected = Answers.Par.chooser(pa)(f)

      assert(Answers.Par.eqv(expected, result))
    }
  }

  test("joinWithFlatMap") {
    forAll { a: Par.Par[Par.Par[Int]] =>
      val result = Par.joinWithFlatMap(a)
      val expected = Answers.Par.join(a)

      assert(Answers.Par.eqv(expected, result))
    }
  }

}
