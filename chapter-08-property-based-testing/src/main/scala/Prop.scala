package com.github.plippe.fpinscala.chapter08

import fpinscala.parallelism.Par
import fpinscala.state.RNG

/** EXERCISE 8.1
  *
  * To get used to thinking about testing in this way, come up with properties that specify
  * the implementation of a sum: List[Int] => Int function. You don’t have to write your
  * properties down as executable ScalaCheck code—an informal description is fine.
  * Here are some ideas to get you started:
  *   - Reversing a list and summing it should give the same result as summing the
  *     original, nonreversed list.
  *   - What should the sum be if all elements of the list are the same value?
  *   - Can you think of other properties?
  */
/** EXERCISE 8.2
  *
  * What properties specify a function that finds the maximum of a List[Int]?
  */
/** EXERCISE 8.3
  *
  * Assuming the following representation of Prop, implement && as a method of Prop.
  * trait Prop { def check: Boolean }
  */
case class Prop1(check: () => Boolean) {
  def &&(p: Prop1): Prop1 = ???
}

case class Prop2(run: (Prop.TestCases, RNG) => Prop.Result) {

  /** EXERCISE 8.9
    *
    * Now that we have a representation of Prop, implement && and || for composing Prop
    * values. Notice that in the case of failure we don’t know which property was responsible,
    * the left or the right. Can you devise a way of handling this, perhaps by allowing
    * Prop values to be assigned a tag or label which gets displayed in the event of a failure?
    */
  def &&(p: Prop2): Prop2 = ???
  def ||(p: Prop2): Prop2 = ???
  def tag(msg: String): Prop2 = ???
}

object Prop {
  type SuccessCount = Int
  type TestCases = Int
  type FailedCase = String

  sealed trait Result {
    def isFalsified: Boolean
  }
  case object Passed extends Result {
    def isFalsified = false
  }
  case class Falsified(failure: FailedCase, successes: SuccessCount)
      extends Result { def isFalsified = true }

  /** EXERCISE 8.15
    *
    * A check property is easy to prove conclusively because the test just involves evaluating
    * the Boolean argument. But some forAll properties can be proved as well. For
    * instance, if the domain of the property is Boolean, then there are really only two cases
    * to test. If a property forAll(p) passes for both p(true) and p(false), then it is
    * proved. Some domains (like Boolean and Byte) are so small that they can be exhaustively
    * checked. And with sized generators, even infinite domains can be exhaustively
    * checked up to the maximum size. Automated testing is very useful, but it’s even better
    * if we can automatically prove our code correct. Modify our library to incorporate this kind
    * of exhaustive checking of finite domains and sized generators. This is less of an exercise
    * and more of an extensive, open-ended design project.
    */
  /** EXERCISE 8.16
    *
    * Write a richer generator for Par[Int], which builds more deeply nested parallel
    * computations than the simple ones we gave previously.
    */
  def intProp: Gen[Par.Par[Int]] = Gen.choose(0, 10).map(Par.unit(_))

  /** EXERCISE 8.17
    *
    * Express the property about fork from chapter 7, that fork(x) == x.
    */
  def forkProp = ???

  /** EXERCISE 8.18
    *
    * Come up with some other properties that takeWhile should satisfy. Can you think of a
    * good property expressing the relationship between takeWhile and dropWhile?
    */

  /** EXERCISE 8.19
    *
    * We want to generate a function that uses its argument in some way to select which
    * Int to return. Can you think of a good way of expressing this? This is a very openended
    * and challenging design exercise. See what you can discover about this problem
    * and if there’s a nice general solution that you can incorporate into the library we’ve
    * developed so far.
    */

  /** EXERCISE 8.20
  *
  * You’re strongly encouraged to venture out and try using the library we’ve developed!
  * See what else you can test with it, and see if you discover any new idioms for its use or
  * perhaps ways it could be extended further or made more convenient. Here are a few
  * ideas to get you started:
  *   - Write properties to specify the behavior of some of the other functions we wrote
  *     for List and Stream, for instance, take, drop, filter, and unfold.
  *   - Write a sized generator for producing the Tree data type defined in chapter 3,
  *     and then use this to specify the behavior of the fold function we defined for
  *     Tree. Can you think of ways to improve the API to make this easier?
  *   - Write properties to specify the behavior of the sequence function we defined
  *     for Option and Either.
  */

}
