package com.github.plippe.fpinscala.chapter06

case class State[S, +A](run: S => (A, S)) {

  /** EXERCISE 6.10a
    *
    * Generalize the functions map, map2, and flatMap. Add them as methods
    * on the State case class where possible. Otherwise you should put them in a State
    * companion object.
    */
  def map[B](f: A => B): State[S, B] = ???
  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = ???
  def flatMap[B](f: A => State[S, B]): State[S, B] = ???

}

object State {

  def modify[S](f: S => S): State[S, Unit] =
    for {
      s <- get // Gets the current state and assigns it to `s`.
      _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
    } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  /** EXERCISE 6.10b
    *
    * Generalize the functions unit, and sequence. Add them as methods
    * on the State case class where possible. Otherwise you should put them in a State
    * companion object.
    */
  def unit[S, A](a: A): State[S, A] = ???
  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = ???

  /** EXERCISE 6.11
    *
    * To gain experience with the use of State, implement a finite state automaton
    * that models a simple candy dispenser. The machine has two types of input: you can
    * insert a coin, or you can turn the knob to dispense candy. It can be in one of two
    * states: locked or unlocked. It also tracks how many candies are left and how many
    * coins it contains.
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
  sealed trait Input
  case object Coin extends Input
  case object Turn extends Input
  case class Machine(locked: Boolean, candies: Int, coins: Int)

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ???

}
