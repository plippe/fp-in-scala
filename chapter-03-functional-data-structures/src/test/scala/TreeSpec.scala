package com.github.plippe.fpinscala.chapter03

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter03.gen._

class TreeSpec extends FunSuite with PropertyChecks {

  test("size") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.size(t)
      val result = Tree.size(t)

      assert(expected == result)
    }
  }

  test("maximum") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.maximum(t)
      val result = Tree.maximum(t)

      assert(expected == result)
    }
  }

  test("depth") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.depth(t)
      val result = Tree.depth(t)

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.map(t)(f)
      val result = Tree.map(t)(f)

      assert(expected == result)
    }
  }

  test("sizeWithFold") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.size(t)
      val result = Tree.sizeWithFold(t)

      assert(expected == result)
    }
  }

  test("maximumWithFold") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.maximum(t)
      val result = Tree.maximumWithFold(t)

      assert(expected == result)
    }
  }

  test("depthWithFold") {
    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.depth(t)
      val result = Tree.depthWithFold(t)

      assert(expected == result)
    }
  }

  test("mapWithFold") {
    def f(a: Int) = a + 1

    forAll { t: Tree[Int] =>
      val expected = Answers.Tree.map(t)(f)
      val result = Tree.mapWithFold(t)(f)

      assert(expected == result)
    }
  }

}
