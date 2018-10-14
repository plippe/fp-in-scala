import fpinscala.datastructures._

trait Chapter03 {

  /** EXERCISE 3.1
    *
    * What will be the result of the following match expression?
    */
  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _)))          => x
    case Nil                                   => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t)                            => h + List.sum(t)
    case _                                     => 101
  }

  /** EXERCISE 3.2
    *
    * Implement the function tail for removing the first element of a List. Note that the
    * function takes constant time. What are different choices you could make in your
    * implementation if the List is Nil? We’ll return to this question in the next chapter
    */
  /** EXERCISE 3.3
    *
    * Using the same idea, implement the function setHead for replacing the first element
    * of a List with a different value.
    */
  /** EXERCISE 3.4
    *
    * Generalize tail to the function drop, which removes the first n elements from a list.
    * Note that this function takes time proportional only to the number of elements being
    * dropped—we don’t need to make a copy of the entire List.
    */
  def drop[A](l: List[A], n: Int): List[A]

  /** EXERCISE 3.5
    *
    * Implement dropWhile, which removes elements from the List prefix as long as they
    * match a predicate.
    */
  def dropWhile[A](l: List[A], f: A => Boolean): List[A]

  /** EXERCISE 3.6
    *
    * Not everything works out so nicely. Implement a function, init, that returns a List
    * consisting of all but the last element of a List. So, given List(1,2,3,4), init will
    * return List(1,2,3). Why can’t this function be implemented in constant time like
    * tail?
    */
  def init[A](l: List[A]): List[A]

  /** EXERCISE 3.7
    *
    * Can product, implemented using foldRight, immediately halt the recursion and
    * return 0.0 if it encounters a 0.0? Why or why not? Consider how any short-circuiting
    * might work if you call foldRight with a large list. This is a deeper question that we’ll
    * return to in chapter 5.
    */
  /** EXERCISE 3.8
    *
    * See what happens when you pass Nil and Cons themselves to foldRight, like this:
    * foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)). What do you think this
    * says about the relationship between foldRight and the data constructors of List?
    */
  /** EXERCISE 3.9
    *
    * Compute the length of a list using foldRight.
    */
  def length[A](as: List[A]): Int

  /** EXERCISE 3.10
    *
    * Our implementation of foldRight is not tail-recursive and will result in a StackOverflowError
    * for large lists (we say it’s not stack-safe). Convince yourself that this is the
    * case, and then write another general list-recursion function, foldLeft, that is
    * tail-recursive, using the techniques we discussed in the previous chapter. Here is its
    * signature.
    */
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B

  /** EXERCISE 3.11
    *
    * Write sum, product, and a function to compute the length of a list using foldLeft.
    */
  /** EXERCISE 3.12
    *
    * Write a function that returns the reverse of a list (given List(1,2,3) it returns
    * List(3,2,1)). See if you can write it using a fold.
    */
  /** EXERCISE 3.13
    *
    * Can you write foldLeft in terms of foldRight? How about the other way
    * around? Implementing foldRight via foldLeft is useful because it lets us implement
    * foldRight tail-recursively, which means it works even for large lists without overflowing
    * the stack.
    */
  /** EXERCISE 3.14
    *
    * Implement append in terms of either foldLeft or foldRight.
    */
  /** EXERCISE 3.15
    *
    * Write a function that concatenates a list of lists into a single list. Its runtime
    * should be linear in the total length of all lists. Try to use functions we have already
    * defined.
    */
  /** EXERCISE 3.16
    *
    * Write a function that transforms a list of integers by adding 1 to each element.
    * (Reminder: this should be a pure function that returns a new List!)
    */
  /** EXERCISE 3.17
    *
    * Write a function that turns each value in a List[Double] into a String. You can use
    * the expression d.toString to convert some d: Double to a String.
    */
  /** EXERCISE 3.18
    *
    * Write a function map that generalizes modifying each element in a list while maintaining
    * the structure of the list. Here is its signature:
    */
  def map[A, B](as: List[A])(f: A => B): List[B]

  /** EXERCISE 3.19
    *
    * Write a function filter that removes elements from a list unless they satisfy a given
    * predicate. Use it to remove all odd numbers from a List[Int].
    */
  def filter[A](as: List[A])(f: A => Boolean): List[A]

  /** EXERCISE 3.20
    *
    * Write a function flatMap that works like map except that the function given will return
    * a list instead of a single result, and that list should be inserted into the final resulting
    * list. For instance, flatMap(List(1,2,3))(i => List(i,i)) should result in List(1,1,2,2,3,3).
    */
  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B]

  /** EXERCISE 3.21
    *
    * Use flatMap to implement filter.
    */
  /** EXERCISE 3.22
    *
    * Write a function that accepts two lists and constructs a new list by adding corresponding
    * elements. For example, List(1,2,3) and List(4,5,6) become List(5,7,9).
    */
  /** EXERCISE 3.23
    *
    * Generalize the function you just wrote so that it’s not specific to integers or addition.
    * Name your generalized function zipWith.
    */
  /** EXERCISE 3.24
    *
    * As an example, implement hasSubsequence for checking whether a List contains
    * another List as a subsequence. For instance, List(1,2,3,4) would have
    * List(1,2), List(2,3), and List(4) as subsequences, among others. You may have
    * some difficulty finding a concise purely functional implementation that is also efficient.
    * That’s okay. Implement the function however comes most naturally. We’ll
    * return to this implementation in chapter 5 and hopefully improve on it. Note: Any
    * two values x and y can be compared for equality in Scala using the expression x == y.
    */
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean

  /** EXERCISE 3.25
    *
    * Write a function size that counts the number of nodes (leaves and branches) in a tree.
    */

  /** EXERCISE 3.26
    *
    * Write a function maximum that returns the maximum element in a Tree[Int]. (Note:
    * In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x
    * and y.)
    */

  /** EXERCISE 3.27
    *
    * Write a function depth that returns the maximum path length from the root of a tree
    * to any leaf.
    */

  /** EXERCISE 3.28
    *
    * Write a function map, analogous to the method of the same name on List, that modifies
    * each element in a tree with a given function.
    */

  /** EXERCISE 3.29
  *
  * Generalize size, maximum, depth, and map, writing a new function fold that abstracts
  * over their similarities. Reimplement them in terms of this more general function. Can
  * you draw an analogy between this fold function and the left and right folds for List?
  */

}
