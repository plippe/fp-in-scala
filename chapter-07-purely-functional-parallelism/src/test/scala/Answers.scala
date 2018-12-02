// https://github.com/fpinscala/fpinscala/tree/master/answers/src/main/scala/fpinscala/parallelism
package com.github.plippe.fpinscala.chapter07

import com.github.plippe.fpinscala.chapter07.{Par => P}
import java.util.concurrent.{Executors, ExecutorService}

object Answers {

  object Par {

    def eqv[A](a: P.Par[A], b: P.Par[A]): Boolean = {
      val executor = Executors.newFixedThreadPool(5)
      P.equal(executor)(a, b)
    }

    def map2[A, B, C](a: P.Par[A], b: P.Par[B])(f: (A, B) => C): P.Par[C] =
      (es: ExecutorService) => {
        val af = a(es)
        val bf = b(es)

        P.UnitFuture(f(af.get, bf.get))
      }

    def asyncF[A, B](f: A => B): A => P.Par[B] =
      a => P.lazyUnit(f(a))

    def sequence[A](l: List[P.Par[A]]): P.Par[List[A]] =
      l.foldRight[P.Par[List[A]]](P.unit(List()))((h, t) => map2(h, t)(_ :: _))

    def parFilter[A](l: List[A])(f: A => Boolean): P.Par[List[A]] = {
      val pars: List[P.Par[List[A]]] =
        l.map(asyncF((a: A) => if (f(a)) List(a) else List()))
      P.map(sequence(pars))(_.flatten)
    }

    def choice[A](cond: P.Par[Boolean])(t: P.Par[A], f: P.Par[A]): P.Par[A] =
      es =>
        if (P.run(es)(cond).get)
          t(es) // Notice we are blocking on the result of `cond`.
        else f(es)

    def choiceN[A](n: P.Par[Int])(choices: List[P.Par[A]]): P.Par[A] =
      es => {
        val ind = P.run(es)(n).get // Full source files
        P.run(es)(choices(ind))
      }

    def choiceMap[K, V](key: P.Par[K])(choices: Map[K, P.Par[V]]): P.Par[V] =
      es => {
        val k = P.run(es)(key).get
        P.run(es)(choices(k))
      }

    def chooser[A, B](p: P.Par[A])(choices: A => P.Par[B]): P.Par[B] =
      es => {
        val k = P.run(es)(p).get
        P.run(es)(choices(k))
      }

    def join[A](a: P.Par[P.Par[A]]): P.Par[A] =
      es => P.run(es)(P.run(es)(a).get())
  }
}
