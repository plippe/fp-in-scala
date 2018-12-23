package com.github.plippe.fpinscala.chapter06

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter06.gen._
import com.github.plippe.fpinscala.chapter06.State.{Input, Machine}

class StateSpec extends FunSuite with PropertyChecks {

  test("unit") {
    forAll { a: Int =>
      val result = State.unit(a).run(())
      val expected = Answers.State.unit(a).run(())

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { s: State[Unit, Int] =>
      val result = s.map(f).run(())
      val expected = Answers.State.map(s)(f).run(())

      assert(expected == result)
    }
  }

  test("map2") {
    forAll { (sa: State[Unit, Int], sb: State[Unit, Int]) =>
      val result = sa.map2(sb)(_ + _).run(())
      val expected = Answers.State.map2(sa)(sb)(_ + _).run(())

      assert(expected == result)
    }
  }

  test("flatMap") {
    def f(a: Int) = State[Unit, Int](s => (a + 1, s))

    forAll { s: State[Unit, Int] =>
      val result = s.flatMap(f).run(())
      val expected = Answers.State.flatMap(s)(f).run(())

      assert(expected == result)
    }
  }

  test("sequence") {
    forAll { s: List[State[Unit, Int]] =>
      val result = State.sequence(s).run(())
      val expected = Answers.State.sequence(s).run(())

      assert(expected == result)
    }
  }

  test("simulateMachine") {
    forAll { (machine: Machine, is: List[Input]) =>
      val result = State.simulateMachine(is).run(machine)
      val expected = Answers.State.simulateMachine(is).run(machine)

      assert(expected == result)
    }
  }

}
