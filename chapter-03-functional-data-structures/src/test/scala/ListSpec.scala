package com.github.plippe.fpinscala.chapter03

import org.scalatest._
import org.scalatest.prop.{PropertyChecks, Whenever}

import com.github.plippe.fpinscala.chapter03.gen._

class ListSpec extends FunSuite with PropertyChecks with Whenever {

  test("tail") {
    forAll { l: List[Int] =>
      whenever(l != Nil) {
        val result = List.tail(l)
        val expected = Answers.List.tail(l)

        assert(expected == result)
      }
    }
  }

  test("setHead") {
    forAll { (l: List[Int], h: Int) =>
      whenever(l != Nil) {
        val result = List.setHead(l, h)
        val expected = Answers.List.setHead(l, h)

        assert(expected == result)
      }
    }
  }

  test("drop") {
    forAll { (l: List[Int], n: Int) =>
      val result = List.drop(l, n)
      val expected = Answers.List.drop(l, n)

      assert(expected == result)
    }
  }

  test("dropWhile") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val result = List.dropWhile(l, f)
      val expected = Answers.List.dropWhile(l, f)

      assert(expected == result)
    }
  }

  test("init") {
    forAll { l: List[Int] =>
      whenever(l != Nil) {
        val result = List.init(l)
        val expected = Answers.List.init(l)

        assert(expected == result)
      }
    }
  }

  test("lengthWithFoldRight") {
    forAll { l: List[Int] =>
      val result = List.lengthWithFoldRight(l)
      val expected = Answers.List.length(l)

      assert(expected == result)
    }
  }

  test("foldLeft") {
    def f(b: Int, a: Int) = b + a

    forAll { (l: List[Int], z: Int) =>
      val result = List.foldLeft(l, z)(f)
      val expected = Answers.List.foldLeft(l, z)(f)

      assert(expected == result)
    }
  }

  test("sumWithFoldLeft") {
    forAll { l: List[Int] =>
      val result = List.sumWithFoldLeft(l)
      val expected = Answers.List.sum(l)

      assert(expected == result)
    }
  }

  test("productWithFoldLeft") {
    forAll { l: List[Double] =>
      val result = List.productWithFoldLeft(l)
      val expected = Answers.List.product(l)

      assert(expected == result)
    }
  }

  test("lengthWithFoldLeft") {
    forAll { l: List[Int] =>
      val result = List.lengthWithFoldLeft(l)
      val expected = Answers.List.length(l)

      assert(expected == result)
    }
  }

  test("reverse") {
    forAll { l: List[Int] =>
      val result = List.reverse(l)
      val expected = Answers.List.reverse(l)

      assert(expected == result)
    }
  }

  test("foldRightWithFoldLeft") {
    def f(a: Int, b: Int) = a + b

    forAll { (l: List[Int], z: Int) =>
      val result = List.foldRightWithFoldLeft(l, z)(f)
      val expected = List.foldRight(l, z)(f)

      assert(expected == result)
    }
  }

  test("foldLeftWithFoldRight") {
    def f(b: Int, a: Int) = b + a

    forAll { (l: List[Int], z: Int) =>
      val result = List.foldLeftWithFoldRight(l, z)(f)
      val expected = Answers.List.foldLeft(l, z)(f)

      assert(expected == result)
    }
  }

  test("appendWithFoldLeft") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val result = List.appendWithFoldLeft(la, lb)
      val expected = Answers.List.append(la, lb)

      assert(expected == result)
    }
  }

  test("appendWithFoldRight") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val result = List.appendWithFoldRight(la, lb)
      val expected = Answers.List.append(la, lb)

      assert(expected == result)
    }
  }

  test("concat") {
    forAll { l: List[List[Int]] =>
      val result = List.concat(l)
      val expected = Answers.List.concat(l)

      assert(expected == result)
    }
  }

  test("mapAdd1") {
    forAll { l: List[Int] =>
      val result = List.mapAdd1(l)
      val expected = Answers.List.map(l)(_ + 1)

      assert(expected == result)
    }
  }

  test("mapDoubleToString") {
    forAll { l: List[Double] =>
      val result = List.mapDoubleToString(l)
      val expected = Answers.List.map(l)(_.toString)

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { l: List[Int] =>
      val result = List.map(l)(f)
      val expected = Answers.List.map(l)(f)

      assert(expected == result)
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val result = List.filter(l)(f)
      val expected = Answers.List.filter(l)(f)

      assert(expected == result)
    }
  }

  test("flatMap") {
    def f(a: Int) = List(a, a, a)

    forAll { l: List[Int] =>
      val result = List.flatMap(l)(f)
      val expected = Answers.List.flatMap(l)(f)

      assert(expected == result)
    }
  }

  test("filterWithFlatMap") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val result = List.filterWithFlatMap(l)(f)
      val expected = Answers.List.filter(l)(f)

      assert(expected == result)
    }
  }

  test("zipWithAdd") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val result = List.zipWithAdd(la, lb)
      val expected = Answers.List.zipWith(la, lb)(_ + _)

      assert(expected == result)
    }
  }

  test("zipWith") {
    def f(a: Int, b: Int) = a + b

    forAll { (la: List[Int], lb: List[Int]) =>
      val result = List.zipWith(la, lb)(f)
      val expected = Answers.List.zipWith(la, lb)(f)

      assert(expected == result)
    }
  }

  test("hasSubsequence") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val result = List.hasSubsequence(la, lb)
      val expected = Answers.List.hasSubsequence(la, lb)

      assert(expected == result)
    }
  }

}
