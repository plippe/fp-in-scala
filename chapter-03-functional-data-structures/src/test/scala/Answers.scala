// https://github.com/fpinscala/fpinscala/tree/master/answers/src/main/scala/fpinscala/datastructures
package com.github.plippe.fpinscala.chapter03

import com.github.plippe.fpinscala.chapter03.{List => L}

object Answers {

  object List {
    def tail[A](l: List[A]): List[A] =
      l match {
        case Nil        => sys.error("tail of empty list")
        case Cons(_, t) => t
      }

    def setHead[A](l: List[A], h: A): List[A] = l match {
      case Nil        => sys.error("setHead on empty list")
      case Cons(_, t) => Cons(h, t)
    }

    def drop[A](l: List[A], n: Int): List[A] =
      if (n <= 0) l
      else
        l match {
          case Nil        => Nil
          case Cons(_, t) => drop(t, n - 1)
        }

    def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
      l match {
        case Cons(h, t) if f(h) => dropWhile(t, f)
        case _                  => l
      }

    def init[A](l: List[A]): List[A] =
      l match {
        case Nil          => sys.error("init of empty list")
        case Cons(_, Nil) => Nil
        case Cons(h, t)   => Cons(h, init(t))
      }

    def length[A](l: List[A]): Int =
      L.foldRight(l, 0)((_, acc) => acc + 1)

    @annotation.tailrec
    def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
      case Nil        => z
      case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }

    def sum(ints: List[Int]): Int = ints match {
      case Nil         => 0
      case Cons(x, xs) => x + sum(xs)
    }

    def product(ds: List[Double]): Double = ds match {
      case Nil          => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x, xs)  => x * product(xs)
    }

    def append[A](a1: List[A], a2: List[A]): List[A] =
      a1 match {
        case Nil        => a2
        case Cons(h, t) => Cons(h, append(t, a2))
      }

    def reverse[A](l: List[A]): List[A] = {
      val empty: List[A] = Nil
      foldLeft(l, empty)((acc, h) => Cons(h, acc))
    }

    def concat[A](l: List[List[A]]): List[A] =
      L.foldRight(l, Nil: List[A])(append)

    def map[A, B](l: List[A])(f: A => B): List[B] =
      L.foldRight(l, Nil: List[B])((h, t) => Cons(f(h), t))

    def filter[A](l: List[A])(f: A => Boolean): List[A] =
      L.foldRight(l, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)

    def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] =
      concat(map(l)(f))

    def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] =
      (a, b) match {
        case (Nil, _)                     => Nil
        case (_, Nil)                     => Nil
        case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
      }

    @annotation.tailrec
    def startsWith[A](l: List[A], prefix: List[A]): Boolean =
      (l, prefix) match {
        case (_, Nil)                              => true
        case (Cons(h, t), Cons(h2, t2)) if h == h2 => startsWith(t, t2)
        case _                                     => false
      }
    @annotation.tailrec
    def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = sup match {
      case Nil                       => sub == Nil
      case _ if startsWith(sup, sub) => true
      case Cons(_, t)                => hasSubsequence(t, sub)
    }
  }

  object Tree {
    def size[A](t: Tree[A]): Int = t match {
      case Leaf(_)      => 1
      case Branch(l, r) => 1 + size(l) + size(r)
    }

    def maximum(t: Tree[Int]): Int = t match {
      case Leaf(n)      => n
      case Branch(l, r) => maximum(l) max maximum(r)
    }

    def depth[A](t: Tree[A]): Int = t match {
      case Leaf(_)      => 0
      case Branch(l, r) => 1 + (depth(l) max depth(r))
    }

    def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
      case Leaf(a)      => Leaf(f(a))
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }
  }
}
