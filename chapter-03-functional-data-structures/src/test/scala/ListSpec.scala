package com.github.plippe.fpinscala.chapter03

import org.scalatest._
import org.scalatest.prop.{PropertyChecks, Whenever}

import com.github.plippe.fpinscala.chapter03.Gen._

class ListSpec extends FunSuite with PropertyChecks with Whenever {

  test("tail") {
    forAll { l: List[Int] =>
      whenever(l != Nil) {
        val expected = Answers.List.tail(l)
        val result = List.tail(l)

        assert(expected == result)
      }
    }
  }

  test("setHead") {
    forAll { (l: List[Int], h: Int) =>
      whenever(l != Nil) {
        val expected = Answers.List.setHead(l, h)
        val result = List.setHead(l, h)

        assert(expected == result)
      }
    }
  }

  test("drop") {
    forAll { (l: List[Int], n: Int) =>
      val expected = Answers.List.drop(l, n)
      val result = List.drop(l, n)

      assert(expected == result)
    }
  }

  test("dropWhile") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val expected = Answers.List.dropWhile(l, f)
      val result = List.dropWhile(l, f)

      assert(expected == result)
    }
  }

  test("init") {
    forAll { l: List[Int] =>
      whenever(l != Nil) {
        val expected = Answers.List.init(l)
        val result = List.init(l)

        assert(expected == result)
      }
    }
  }

  test("length") {
    forAll { l: List[Int] =>
      val expected = Answers.List.length(l)
      val result = List.length(l)

      assert(expected == result)
    }
  }

  test("foldLeft") {
    def f(b: Int, a: Int) = b + a

    forAll { (l: List[Int], z: Int) =>
      val expected = Answers.List.foldLeft(l, z)(f)
      val result = List.foldLeft(l, z)(f)

      assert(expected == result)
    }
  }

  test("sumWithFoldLeft") {
    forAll { l: List[Int] =>
      val expected = Answers.List.sum(l)
      val result = List.sumWithFoldLeft(l)

      assert(expected == result)
    }
  }

  test("productWithFoldLeft") {
    forAll { l: List[Double] =>
      val expected = Answers.List.product(l)
      val result = List.productWithFoldLeft(l)

      assert(expected == result)
    }
  }

  test("lengthWithFoldLeft") {
    forAll { l: List[Int] =>
      val expected = Answers.List.length(l)
      val result = List.lengthWithFoldLeft(l)

      assert(expected == result)
    }
  }

  test("reverse") {
    forAll { l: List[Int] =>
      val expected = Answers.List.reverse(l)
      val result = List.reverse(l)

      assert(expected == result)
    }
  }

  test("foldRightWithFoldLeft") {
    def f(a: Int, b: Int) = a + b

    forAll { (l: List[Int], z: Int) =>
      val expected = List.foldRight(l, z)(f)
      val result = List.foldRightWithFoldLeft(l, z)(f)

      assert(expected == result)
    }
  }

  test("foldLeftWithFoldRight") {
    def f(b: Int, a: Int) = b + a

    forAll { (l: List[Int], z: Int) =>
      val expected = Answers.List.foldLeft(l, z)(f)
      val result = List.foldLeftWithFoldRight(l, z)(f)

      assert(expected == result)
    }
  }

  test("append") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val expected = Answers.List.append(la, lb)
      val result = List.append(la, lb)

      assert(expected == result)
    }
  }

  test("concat") {
    forAll { l: List[List[Int]] =>
      val expected = Answers.List.concat(l)
      val result = List.concat(l)

      assert(expected == result)
    }
  }

  test("mapAdd1") {
    forAll { l: List[Int] =>
      val expected = Answers.List.map(l)(_ + 1)
      val result = List.mapAdd1(l)

      assert(expected == result)
    }
  }

  test("mapDoubleToString") {
    forAll { l: List[Double] =>
      val expected = Answers.List.map(l)(_.toString)
      val result = List.mapDoubleToString(l)

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { l: List[Int] =>
      val expected = Answers.List.map(l)(f)
      val result = List.map(l)(f)

      assert(expected == result)
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val expected = Answers.List.filter(l)(f)
      val result = List.filter(l)(f)

      assert(expected == result)
    }
  }

  test("flatMap") {
    def f(a: Int) = List(a, a, a)

    forAll { l: List[Int] =>
      val expected = Answers.List.flatMap(l)(f)
      val result = List.flatMap(l)(f)

      assert(expected == result)
    }
  }

  test("filterWithFlatMap") {
    def f(a: Int) = a > 0

    forAll { l: List[Int] =>
      val expected = Answers.List.filter(l)(f)
      val result = List.filterWithFlatMap(l)(f)

      assert(expected == result)
    }
  }

  test("zipWithAdd") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val expected = Answers.List.zipWith(la, lb)(_ + _)
      val result = List.zipWithAdd(la, lb)

      assert(expected == result)
    }
  }

  test("zipWith") {
    def f(a: Int, b: Int) = a + b

    forAll { (la: List[Int], lb: List[Int]) =>
      val expected = Answers.List.zipWith(la, lb)(f)
      val result = List.zipWith(la, lb)(f)

      assert(expected == result)
    }
  }

  test("hasSubsequence") {
    forAll { (la: List[Int], lb: List[Int]) =>
      val expected = Answers.List.hasSubsequence(la, lb)
      val result = List.hasSubsequence(la, lb)

      assert(expected == result)
    }
  }

}
