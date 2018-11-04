// https://github.com/fpinscala/fpinscala/tree/master/answers/src/main/scala/fpinscala/laziness
package com.github.plippe.fpinscala.chapter05

object Answers {

  object Stream {

    def eqv[A](a: Stream[A], b: Stream[A]): Boolean = {
      val start = take(a)(25)
      startsWith(b)(a)
    }

    def toList[A](s: Stream[A]): List[A] = {
      @annotation.tailrec
      def go(s: Stream[A], acc: List[A]): List[A] = s match {
        case Cons(h, t) => go(t(), h() :: acc)
        case _          => acc
      }

      go(s, List()).reverse
    }

    def take[A](s: Stream[A])(n: Int): Stream[A] = s match {
      case Cons(h, t) if n > 1  => Cons(() => h(), () => take(t())(n - 1))
      case Cons(h, _) if n == 1 => Cons(() => h(), () => Empty)
      case _                    => Empty
    }

    @annotation.tailrec
    def drop[A](s: Stream[A])(n: Int): Stream[A] = s match {
      case Cons(_, t) if n > 0 => drop(t())(n - 1)
      case _                   => s
    }

    def takeWhile[A](s: Stream[A])(f: A => Boolean): Stream[A] = s match {
      case Cons(h, t) if f(h()) => Cons(() => h(), () => takeWhile(t())(f))
      case _                    => Empty
    }

    def headOption[A](s: Stream[A]): Option[A] = take(s)(1) match {
      case Cons(h, _) => Some(h())
      case _          => None
    }

    def foldRight[A, B](s: Stream[A])(z: => B)(f: (A, => B) => B): B =
      s match {
        case Cons(h, t) => f(h(), foldRight(t())(z)(f))
        case _          => z
      }

    def map[A, B](s: Stream[A])(f: A => B): Stream[B] = {
      val empty: Stream[B] = Empty
      foldRight(s)(empty)((h, t) => Cons(() => f(h), () => t))
    }

    def filter[A](s: Stream[A])(f: A => Boolean): Stream[A] = {
      val empty: Stream[A] = Empty
      foldRight(s)(empty)(
        (h, t) =>
          if (f(h)) Cons(() => h, () => t)
          else t)
    }

    def append[A, B >: A](sa: Stream[A], sb: => Stream[B]): Stream[B] =
      foldRight(sa)(sb)((h, t) => Cons(() => h, () => t))

    def flatMap[A, B](s: Stream[A])(f: A => Stream[B]): Stream[B] = {
      val empty: Stream[B] = Empty
      foldRight(s)(empty)((h, t) => append(f(h), t))
    }

    def constant[A](a: A): Stream[A] = {
      lazy val tail: Stream[A] = Cons[A](() => a, () => tail)
      tail
    }

    def from(n: Int): Stream[Int] =
      Cons(() => n, () => from(n + 1))

    def fibs() = {
      def go(f0: Int, f1: Int): Stream[Int] =
        Cons(() => f0, () => go(f1, f0 + f1))

      go(0, 1)
    }

    def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
      f(z) match {
        case Some((h, s)) => Cons(() => h, () => unfold(s)(f))
        case None         => Empty
      }

    def onesWithUnfold() = unfold(1)(_ => Some((1, 1)))

    def zipWithWithUnfold[A, B, C](s1: Stream[A])(s2: Stream[B])(
        f: (A, B) => C): Stream[C] =
      unfold((s1, s2)) {
        case (Cons(h1, t1), Cons(h2, t2)) =>
          Some((f(h1(), h2()), (t1(), t2())))
        case _ => None
      }

    def zipWithAll[A, B, C](s1: Stream[A])(s2: Stream[B])(
        f: (Option[A], Option[B]) => C): Stream[C] = {
      val emptyA: Stream[A] = Empty
      val emptyB: Stream[B] = Empty

      Stream.unfold((s1, s2)) {
        case (Empty, Empty) => None
        case (Cons(h, t), Empty) =>
          Some((f(Some(h()), Option.empty[B]), (t(), emptyB)))
        case (Empty, Cons(h, t)) =>
          Some((f(Option.empty[A], Some(h())), (emptyA -> t())))
        case (Cons(h1, t1), Cons(h2, t2)) =>
          Some((f(Some(h1()), Some(h2())), (t1() -> t2())))
      }
    }

    def zipAllWithUnfold[A, B](s1: Stream[A])(
        s2: Stream[B]): Stream[(Option[A], Option[B])] =
      zipWithAll(s1)(s2)((_, _))

    def startsWith[A, B](s1: Stream[A])(s2: Stream[B]): Boolean = {
      def forAll[C](s: Stream[C])(f: C => Boolean): Boolean =
        foldRight[C, Boolean](s)(true)((a, b) => f(a) && b)

      forAll(takeWhile(zipAllWithUnfold(s1)(s2))(!_._2.isEmpty)) {
        case (h, h2) => h == h2
      }
    }

    def tails[A](s: Stream[A]): Stream[Stream[A]] =
      append(unfold(s) {
        case Empty => None
        case s     => Some((s, drop(s)(1)))
      }, Cons(() => Empty, () => Empty))

    def scanRight[A, B](s: Stream[A])(z: B)(f: (A, => B) => B): Stream[B] =
      foldRight(s)((z, Cons(() => z, () => Empty)))((a, p0) => {
        lazy val p1 = p0
        val b2 = f(a, p1._1)
        (b2, Cons(() => b2, () => p1._2))
      })._2
  }
}
