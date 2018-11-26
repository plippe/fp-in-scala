// https://github.com/fpinscala/fpinscala/tree/master/answers/src/main/scala/fpinscala/state
package com.github.plippe.fpinscala.chapter06

import com.github.plippe.fpinscala.chapter06.RNG.{Rand, unit}
import com.github.plippe.fpinscala.chapter06.State._

object Answers {

  object RNG {
    def nonNegativeInt(rng: RNG): (Int, RNG) = {
      val (i, r) = rng.nextInt
      (if (i < 0) -(i + 1) else i, r)
    }

    def double(rng: RNG): (Double, RNG) = {
      val (i, r) = nonNegativeInt(rng)
      (i / (Int.MaxValue.toDouble + 1), r)
    }

    def intDouble(rng: RNG): ((Int, Double), RNG) = {
      val (i, r1) = rng.nextInt
      val (d, r2) = double(r1)
      ((i, d), r2)
    }

    def doubleInt(rng: RNG): ((Double, Int), RNG) = {
      val ((i, d), r) = intDouble(rng)
      ((d, i), r)
    }

    def double3(rng: RNG): ((Double, Double, Double), RNG) = {
      val (d1, r1) = double(rng)
      val (d2, r2) = double(r1)
      val (d3, r3) = double(r2)
      ((d1, d2, d3), r3)
    }

    def ints(count: Int)(rng: RNG): (List[Int], RNG) =
      if (count == 0)
        (List(), rng)
      else {
        val (x, r1) = rng.nextInt
        val (xs, r2) = ints(count - 1)(r1)
        (x :: xs, r2)
      }

    def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
      rng => {
        val (a, r1) = ra(rng)
        val (b, r2) = rb(r1)
        (f(a, b), r2)
      }

    def sequence[A](fs: List[Rand[A]]): Rand[List[A]] =
      fs.foldRight(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))

    def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
      rng => {
        val (a, rng2) = s(rng)
        (f(a), rng2)
      }

    def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] =
      rng => {
        val (a, r1) = f(rng)
        g(a)(r1) // We pass the new state along
      }

    def nonNegativeLessThan(n: Int): Rand[Int] = {
      flatMap(nonNegativeInt) { i =>
        val mod = i % n
        if (i + (n - 1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
      }
    }
  }

  object State {

    def unit[S, A](a: A): State[S, A] = new State(s => (a, s))

    def map[S, A, B](s: State[S, A])(f: A => B): State[S, B] =
      flatMap(s)(a => unit(f(a)))

    def map2[S, A, B, C](sa: State[S, A])(sb: State[S, B])(
        f: (A, B) => C): State[S, C] =
      flatMap(sa)(a => map(sb)(b => f(a, b)))

    def flatMap[S, A, B](sa: State[S, A])(f: A => State[S, B]): State[S, B] =
      new State(sb => {
        val (a, sc) = sa.run(sb)
        f(a).run(sc)
      })

    def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
      def go(s: S, actions: List[State[S, A]], acc: List[A]): (List[A], S) =
        actions match {
          case Nil    => (acc.reverse, s)
          case h :: t => h.run(s) match { case (a, s2) => go(s2, t, a :: acc) }
        }

      new State((s: S) => go(s, sas, List()))
    }

    def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = {
      def update =
        (i: Input) =>
          (s: Machine) =>
            (i, s) match {
              case (_, Machine(_, 0, _))        => s
              case (Coin, Machine(false, _, _)) => s
              case (Turn, Machine(true, _, _))  => s
              case (Coin, Machine(true, candy, coin)) =>
                Machine(false, candy, coin + 1)
              case (Turn, Machine(false, candy, coin)) =>
                Machine(true, candy - 1, coin)
        }

      for {
        _ <- sequence(inputs map (modify[Machine] _ compose update))
        s <- get
      } yield (s.coins, s.candies)
    }
  }
}
