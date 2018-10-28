import fpinscala.localeffects._

trait Chapter14 {

  /** EXERCISE 14.1
    * Add a combinator on STArray to fill the array from a Map where each key in the map
    * represents an index into the array, and the value under that key is written to the array
    * at that index. For example, xs.fill(Map(0->"a", 2->"b")) should write the value
    * "a" at index 0 in the array xs and "b" at index 2. Use the existing combinators to write
    * your implementation.
    */
  def fill[S, A](xs: Map[Int, A]): ST[S, Unit]

  /** EXERCISE 14.2
    * Write the purely functional versions of partition and qs.
    */
  def partition[S](arr: STArray[S, Int], n: Int, r: Int, pivot: Int): ST[S, Int]
  def qs[S](a: STArray[S, Int], n: Int, r: Int): ST[S, Unit]

  /** EXERCISE 14.3
  * Give the same treatment to scala.collection.mutable.HashMap as we’ve given here
  * to references and arrays. Come up with a minimal set of primitive combinators for creating
  * and manipulating hash maps.
  */

}
