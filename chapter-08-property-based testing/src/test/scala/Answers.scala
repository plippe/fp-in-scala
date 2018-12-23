// https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/testing/Gen.scala
package com.github.plippe.fpinscala.chapter08

import fpinscala.state.{RNG, State}
import com.github.plippe.fpinscala.chapter08.{Gen => G, Prop => P}

object Answers {

  object Gen {

    def choose(start: Int, stopExclusive: Int): G[Int] =
      G(State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive - start)))

    def unit[A](a: => A): G[A] =
      G(State.unit(a))

    def boolean: G[Boolean] =
      G(State(RNG.boolean))

    def listOfN[A](n: Int, g: G[A]): G[List[A]] =
      G(State.sequence(List.fill(n)(g.sample)))

    def flatMap[A, B](a: G[A])(f: A => G[B]): G[B] =
      G(a.sample.flatMap(a => f(a).sample))

    def union[A](g1: G[A], g2: G[A]): G[A] =
      flatMap(boolean)(b => if (b) g1 else g2)

    def weighted[A](g1: (G[A], Double), g2: (G[A], Double)): G[A] = {
      /* The probability we should pull from `g1`. */
      val g1Threshold = g1._2.abs / (g1._2.abs + g2._2.abs)

      G(State(RNG.double).flatMap(d =>
        if (d < g1Threshold) g1._1.sample else g2._1.sample))
    }

    def unsized[A](g: G[A]): SGen[A] = SGen(_ => g)

    def listOf[A](g: G[A]): SGen[List[A]] =
      SGen(n => listOfN(n, g))

    def listOf1[A](g: G[A]): SGen[List[A]] =
      SGen(n => listOfN(n.max(1), g))

  }

  object Prop {
    def and(a: Prop1, b: Prop1) = Prop1 { () =>
      a.check() && b.check()
    }

    def and(a: Prop2, b: Prop2) = Prop2 { (n, rng) =>
      a.run(n, rng) match {
        case P.Passed => b.run(n, rng)
        case x        => x
      }
    }

    def or(a: Prop2, b: Prop2) = Prop2 { (n, rng) =>
      a.run(n, rng) match {
        // In case of failure, run the other prop.
        case P.Falsified(msg, _) => tag(b, msg).run(n, rng)
        case x                   => x
      }
    }

    def tag(a: Prop2, msg: String) = Prop2 { (n, rng) =>
      a.run(n, rng) match {
        case P.Falsified(e, c) => P.Falsified(msg + "\n" + e, c)
        case x                 => x
      }
    }

  }

}
