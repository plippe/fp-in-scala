package com.github.plippe.fpinscala.chapter08

import com.github.plippe.fpinscala.chapter08.gen._
import fpinscala.state.RNG
import org.scalatest._
import org.scalatest.prop.PropertyChecks

class Prop1Spec extends FunSuite with PropertyChecks {
  test("&&") {
    forAll { (a: Prop1, b: Prop1) =>
      val expected = Answers.Prop.and(a, b).check
      val result = (a && b).check

      assert(expected == result)
    }
  }
}

class Prop2Spec extends FunSuite with PropertyChecks {
  test("&&") {
    forAll { (n: Int, rng: RNG, a: Prop2, b: Prop2) =>
      val expected = Answers.Prop.and(a, b).run(n, rng)
      val result = (a && b).run(n, rng)

      assert(expected == result)
    }
  }

  test("||") {
    forAll { (n: Int, rng: RNG, a: Prop2, b: Prop2) =>
      val expected = Answers.Prop.or(a, b).run(n, rng)
      val result = (a || b).run(n, rng)

      assert(expected == result)
    }
  }

  test("tag") {
    forAll { (n: Int, rng: RNG, a: Prop2, msg: String) =>
      val expected = Answers.Prop.tag(a, msg).run(n, rng)
      val result = a.tag(msg).run(n, rng)

      assert(expected == result)
    }
  }
}

class PropSpec extends FunSuite with PropertyChecks {

  ignore("intProp") {}
  ignore("forkProp") {}

}
