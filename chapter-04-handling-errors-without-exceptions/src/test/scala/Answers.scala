// https://github.com/fpinscala/fpinscala/tree/master/answers/src/main/scala/fpinscala/errorhandling
package com.github.plippe.fpinscala.chapter04

object Answers {

  object Option {
    def map[A, B](o: Option[A])(f: A => B): Option[B] = o match {
      case None    => None
      case Some(a) => Some(f(a))
    }

    def flatMap[A, B](o: Option[A])(f: A => Option[B]): Option[B] = o match {
      case None    => None
      case Some(a) => f(a)
    }

    def getOrElse[A, B >: A](o: Option[A])(default: => B): B = o match {
      case None    => default
      case Some(a) => a
    }

    def orElse[A, B >: A](oa: Option[A])(ob: => Option[B]): Option[B] =
      oa match {
        case None => ob
        case _    => oa
      }

    def filter[A](o: Option[A])(f: A => Boolean): Option[A] = o match {
      case Some(a) if f(a) => o
      case _               => None
    }

    def variance(xs: Seq[Double]): Option[Double] = {
      def mean(xs: Seq[Double]): Option[Double] =
        if (xs.isEmpty) None
        else Some(xs.sum / xs.length)

      flatMap(mean(xs)) { m =>
        mean(xs.map(x => math.pow(x - m, 2)))
      }
    }

    def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
      flatMap(a) { a1 =>
        map(b) { b1 =>
          f(a1, b1)
        }
      }

    def sequence[A](a: List[Option[A]]): Option[List[A]] =
      a match {
        case Nil => Some(Nil)
        case h :: t =>
          flatMap(h) { h1 =>
            map(sequence(t)) { h1 :: _ }
          }
      }

    def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
      a match {
        case Nil    => Some(Nil)
        case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
      }
  }

  object Either {
    def map[E, A, B](e: Either[E, A])(f: A => B): Either[E, B] =
      e match {
        case Right(a) => Right(f(a))
        case Left(e)  => Left(e)
      }

    def flatMap[E, A, EE >: E, B](e: Either[E, A])(
        f: A => Either[EE, B]): Either[EE, B] =
      e match {
        case Left(e)  => Left(e)
        case Right(a) => f(a)
      }

    def orElse[E, A, EE >: E, AA >: A](a: Either[E, A])(
        b: => Either[EE, AA]): Either[EE, AA] =
      a match {
        case Left(_)  => b
        case Right(a) => Right(a)
      }

    def map2[E, A, EE >: E, B, C](a: Either[E, A], b: Either[EE, B])(
        f: (A, B) => C): Either[EE, C] = {
      flatMap[E, A, EE, C](a) { a1 =>
        map[EE, B, C](b) { b1 =>
          f(a1, b1)
        }
      }
    }

    def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] =
      traverse(es)(x => x)

    def traverse[E, A, B](es: List[A])(
        f: A => Either[E, B]): Either[E, List[B]] =
      es match {
        case Nil    => Right(Nil)
        case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
      }
  }

}
