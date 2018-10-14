import fpinscala.state._
import fpinscala.state.RNG.Rand

trait Chapter06 {

  /** EXERCISE 6.1
    *
    * Write a function that uses RNG.nextInt to generate a random integer between 0 and
    * Int.maxValue (inclusive). Make sure to handle the corner case when nextInt returns
    * Int.MinValue, which doesn’t have a non-negative counterpart.
    */
  def nonNegativeInt(rng: RNG): (Int, RNG)

  /** EXERCISE 6.2
    *
    * Write a function to generate a Double between 0 and 1, not including 1. Note: You can
    * use Int.MaxValue to obtain the maximum positive integer value, and you can use
    * x.toDouble to convert an x: Int to a Double.
    */
  def double(rng: RNG): (Double, RNG)

  /** EXERCISE 6.3
    *
    * Write functions to generate an (Int, Double) pair, a (Double, Int) pair, and a
    * (Double, Double, Double) 3-tuple. You should be able to reuse the functions you’ve
    * already written.
    */
  def intDouble(rng: RNG): ((Int, Double), RNG)
  def doubleInt(rng: RNG): ((Double, Int), RNG)
  def double3(rng: RNG): ((Double, Double, Double), RNG)

  /** EXERCISE 6.4
    *
    * Write a function to generate a list of random integers.
    */
  def ints(count: Int)(rng: RNG): (List[Int], RNG)

  /** EXERCISE 6.5
    *
    * Use map to reimplement double in a more elegant way. See exercise 6.2.
    */
  /** EXERCISE 6.6
    *
    * Write the implementation of map2 based on the following signature. This function
    * takes two actions, ra and rb, and a function f for combining their results, and returns
    * a new action that combines them:
    */
  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C]

  /** EXERCISE 6.7
    *
    * If you can combine two RNG transitions, you should be able to combine a whole
    * list of them. Implement sequence for combining a List of transitions into a single
    * transition. Use it to reimplement the ints function you wrote before. For the latter,
    * you can use the standard library function List.fill(n)(x) to make a list with x
    * repeated n times.
    */
  def sequence[A](fs: List[Rand[A]]): Rand[List[A]]

  /** EXERCISE 6.8
    *
    * Implement flatMap, and then use it to implement nonNegativeLessThan.
    */
  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B]

  /** EXERCISE 6.9
    *
    * Reimplement map and map2 in terms of flatMap. The fact that this is possible is what
    * we’re referring to when we say that flatMap is more powerful than map and map2.
    */
  /** EXERCISE 6.10
    *
    * Generalize the functions unit, map, map2, flatMap, and sequence. Add them as methods
    * on the State case class where possible. Otherwise you should put them in a State
    * companion object.
    */
  /** EXERCISE 6.11
    *
    * To gain experience with the use of State, implement a finite state automaton
    * that models a simple candy dispenser. The machine has two types of input: you can
    * insert a coin, or you can turn the knob to dispense candy. It can be in one of two
    * states: locked or unlocked. It also tracks how many candies are left and how many
    * coins it contains.
    *
    * sealed trait Input
    * case object Coin extends Input
    * case object Turn extends Input
    * case class Machine(locked: Boolean, candies: Int, coins: Int)
    *
    * The rules of the machine are as follows:
    *   - Inserting a coin into a locked machine will cause it to unlock if there’s any
    *     candy left.
    *   - Turning the knob on an unlocked machine will cause it to dispense candy and
    *     become locked.
    *   - Turning the knob on a locked machine or inserting a coin into an unlocked
    *     machine does nothing.
    *   - A machine that’s out of candy ignores all inputs.
    *
    * The method simulateMachine should operate the machine based on the list of inputs
    * and return the number of coins and candies left in the machine at the end. For example,
    * if the input Machine has 10 coins and 5 candies, and a total of 4 candies are successfully
    * bought, the output should be (14, 1).
    */
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)]

}
