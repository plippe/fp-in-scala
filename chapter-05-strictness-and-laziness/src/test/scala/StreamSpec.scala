package com.github.plippe.fpinscala.chapter05

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter05.gen._

class StreamSpec extends FunSuite with PropertyChecks {

  test("toList") {
    forAll { s: Stream[Int] =>
      val result = s.toList
      val expected = Answers.Stream.toList(s)

      assert(expected == result)
    }
  }

  test("take") {
    forAll { (s: Stream[Int], n: Int) =>
      val result = s.take(n)
      val expected = Answers.Stream.take(s)(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("drop") {
    forAll { (s: Stream[Int], n: Int) =>
      val result = s.drop(n)
      val expected = Answers.Stream.drop(s)(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhile") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val result = s.takeWhile(f)
      val expected = Answers.Stream.takeWhile(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhileWithFoldRight") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val result = s.takeWhileWithFoldRight(f)
      val expected = Answers.Stream.takeWhile(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("headOptionWithFoldRight") {
    forAll { s: Stream[Int] =>
      val result = s.headOptionWithFoldRight
      val expected = Answers.Stream.headOption(s)

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { s: Stream[Int] =>
      val result = s.map(f)
      val expected = Answers.Stream.map(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val result = s.filter(f)
      val expected = Answers.Stream.filter(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("append") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val result = sa.append(sb)
      val expected = Answers.Stream.append(sa, sb)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("flatMap") {
    def f(a: Int) =
      Cons(() => a, () => Cons(() => a, () => Cons(() => a, () => Empty)))

    forAll { s: Stream[Int] =>
      val result = s.flatMap(f)
      val expected = Answers.Stream.flatMap(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("constant") {
    forAll { n: Int =>
      val result = Stream.constant(n)
      val expected = Answers.Stream.constant(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("from") {
    forAll { n: Int =>
      val result = Stream.from(n)
      val expected = Answers.Stream.from(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("fibs") {
    val result = Stream.fibs()
    val expected = Answers.Stream.fibs()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("unfold") {
    def f(a: Int): Option[(Int, Int)] =
      if (a > 0) Some((a, a - 1))
      else None

    forAll { n: Int =>
      val result = Stream.unfold(n)(f)
      val expected = Answers.Stream.unfold(n)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("fibsWithUnfold") {
    val result = Stream.fibsWithUnfold()
    val expected = Answers.Stream.fibs()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("fromWithUnfold") {
    forAll { n: Int =>
      val result = Stream.fromWithUnfold(n)
      val expected = Answers.Stream.from(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("constantWithUnfold") {
    forAll { n: Int =>
      val result = Stream.constantWithUnfold(n)
      val expected = Answers.Stream.constant(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("onesWithUnfold") {
    val result = Stream.onesWithUnfold()
    val expected = Answers.Stream.onesWithUnfold()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("mapWithUnfold") {
    def f(a: Int) = a + 1

    forAll { s: Stream[Int] =>
      val result = s.mapWithUnfold(f)
      val expected = Answers.Stream.map(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWithUnfold") {
    forAll { (s: Stream[Int], n: Int) =>
      val result = s.takeWithUnfold(n)
      val expected = Answers.Stream.take(s)(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhileWithUnfold") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val result = s.takeWhileWithUnfold(f)
      val expected = Answers.Stream.takeWhile(s)(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("zipWithWithUnfold") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val result = sa.zipWithWithUnfold(sb)(_ + _)
      val expected = Answers.Stream.zipWithWithUnfold(sa)(sb)(_ + _)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("zipAllWithUnfold") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val result = sa.zipAllWithUnfold(sb)
      val expected = Answers.Stream.zipAllWithUnfold(sa)(sb)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("startsWith") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val result = sa.startsWith(sb)
      val expected = Answers.Stream.startsWith(sa)(sb)

      assert(expected == result)
    }
  }

  test("tails") {
    forAll { s: Stream[Int] =>
      val result = s.tails
      val expected = Answers.Stream.tails(s)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("scanRight") {
    forAll { s: Stream[Int] =>
      val result = s.scanRight(0)(_ + _)
      val expected = Answers.Stream.scanRight(s)(0)(_ + _)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

}
