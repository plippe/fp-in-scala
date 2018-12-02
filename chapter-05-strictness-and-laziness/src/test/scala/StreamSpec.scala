package com.github.plippe.fpinscala.chapter05

import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.fpinscala.chapter05.Gen._

class StreamSpec extends FunSuite with PropertyChecks {

  test("toList") {
    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.toList(s)
      val result = s.toList

      assert(expected == result)
    }
  }

  test("take") {
    forAll { (s: Stream[Int], n: Int) =>
      val expected = Answers.Stream.take(s)(n)
      val result = s.take(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("drop") {
    forAll { (s: Stream[Int], n: Int) =>
      val expected = Answers.Stream.drop(s)(n)
      val result = s.drop(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhile") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.takeWhile(s)(f)
      val result = s.takeWhile(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhileWithFoldRight") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.takeWhile(s)(f)
      val result = s.takeWhileWithFoldRight(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("headOptionWithFoldRight") {
    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.headOption(s)
      val result = s.headOptionWithFoldRight

      assert(expected == result)
    }
  }

  test("map") {
    def f(a: Int) = a + 1

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.map(s)(f)
      val result = s.map(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("filter") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.filter(s)(f)
      val result = s.filter(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("append") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val expected = Answers.Stream.append(sa, sb)
      val result = sa.append(sb)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("flatMap") {
    def f(a: Int) =
      Cons(() => a, () => Cons(() => a, () => Cons(() => a, () => Empty)))

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.flatMap(s)(f)
      val result = s.flatMap(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("constant") {
    forAll { n: Int =>
      val expected = Answers.Stream.constant(n)
      val result = Stream.constant(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("from") {
    forAll { n: Int =>
      val expected = Answers.Stream.from(n)
      val result = Stream.from(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("fibs") {
    val expected = Answers.Stream.fibs()
    val result = Stream.fibs()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("unfold") {
    def f(a: Int): Option[(Int, Int)] =
      if (a > 0) Some((a, a - 1))
      else None

    forAll { n: Int =>
      val expected = Answers.Stream.unfold(n)(f)
      val result = Stream.unfold(n)(f)

      assert(expected == result)
    }
  }

  test("fibsWithUnfold") {
    val expected = Answers.Stream.fibs()
    val result = Stream.fibsWithUnfold()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("fromWithUnfold") {
    forAll { n: Int =>
      val expected = Answers.Stream.from(n)
      val result = Stream.fromWithUnfold(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("constantWithUnfold") {
    forAll { n: Int =>
      val expected = Answers.Stream.constant(n)
      val result = Stream.constantWithUnfold(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("onesWithUnfold") {
    val expected = Answers.Stream.onesWithUnfold()
    val result = Stream.onesWithUnfold()

    assert(Answers.Stream.eqv(expected, result))
  }

  test("mapWithUnfold") {
    def f(a: Int) = a + 1

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.map(s)(f)
      val result = s.mapWithUnfold(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWithUnfold") {
    forAll { (s: Stream[Int], n: Int) =>
      val expected = Answers.Stream.take(s)(n)
      val result = s.takeWithUnfold(n)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("takeWhileWithUnfold") {
    def f(a: Int) = a > 0

    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.takeWhile(s)(f)
      val result = s.takeWhileWithUnfold(f)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("zipWithWithUnfold") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val expected = Answers.Stream.zipWithWithUnfold(sa)(sb)(_ + _)
      val result = sa.zipWithWithUnfold(sb)(_ + _)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("zipAllWithUnfold") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val expected = Answers.Stream.zipAllWithUnfold(sa)(sb)
      val result = sa.zipAllWithUnfold(sb)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("startsWith") {
    forAll { (sa: Stream[Int], sb: Stream[Int]) =>
      val expected = Answers.Stream.startsWith(sa)(sb)
      val result = sa.startsWith(sb)

      assert(expected == result)
    }
  }

  test("tails") {
    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.tails(s)
      val result = s.tails

      assert(Answers.Stream.eqv(expected, result))
    }
  }

  test("scanRight") {
    forAll { s: Stream[Int] =>
      val expected = Answers.Stream.scanRight(s)(0)(_ + _)
      val result = s.scanRight(0)(_ + _)

      assert(Answers.Stream.eqv(expected, result))
    }
  }

}
