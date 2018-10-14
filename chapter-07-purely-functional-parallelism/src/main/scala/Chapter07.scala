import fpinscala.parallelism.Par.Par

trait Chapter07 {

  /** EXERCISE 7.1
    *
    * Par.map2 is a new higher-order function for combining the result of two parallel computations.
    * What is its signature? Give the most general signature possible (don’t
    * assume it works only for Int).
    */
  /** EXERCISE 7.2
    *
    * Before continuing, try to come up with representations for Par that make it possible
    * to implement the functions of our API.
    */
  /** EXERCISE 7.3
    *
    * Fix the implementation of map2 so that it respects the contract of timeouts on
    * Future.
    */
  /** EXERCISE 7.4
    *
    * This API already enables a rich set of operations. Here’s a simple example: using
    * lazyUnit, write a function to convert any function A => B to one that evaluates its
    * result asynchronously.
    */
  def asyncF[A, B](f: A => B): A => Par[B]

  /** EXERCISE 7.5
    *
    * Write this function, called sequence. No additional primitives are required. Do
    * not call run.
    */
  def sequence[A](ps: List[Par[A]]): Par[List[A]]

  /** EXERCISE 7.6
    *
    * Implement parFilter, which filters elements of a list in parallel.
    */
  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]]

  /** EXERCISE 7.7
    *
    * Given map(y)(id) == y, it’s a free theorem that map(map(y)(g))(f) ==
    * map(y)(f compose g). (This is sometimes called map fusion, and it can be used as an
    * optimization—rather than spawning a separate parallel computation to compute the
    * second mapping, we can fold it into the first mapping.)13 Can you prove it? You may
    * want to read the paper “Theorems for Free!” (http://mng.bz/Z9f1) to better understand
    * the “trick” of free theorems.
    */
  /** EXERCISE 7.8
    *
    * Take a look through the various static methods in Executors to get a feel for the
    * different implementations of ExecutorService that exist. Then, before continuing,
    * go back and revisit your implementation of fork and try to find a counterexample or
    * convince yourself that the law holds for your implementation.
    */
  /** EXERCISE 7.9
    *
    * Show that any fixed-size thread pool can be made to deadlock given this implementation
    * of fork.
    */
  /** EXERCISE 7.10
    *
    * Our non-blocking representation doesn’t currently handle errors at all. If at any
    * point our computation throws an exception, the run implementation’s latch never
    * counts down and the exception is simply swallowed. Can you fix that?
    */
  /** EXERCISE 7.11
    *
    * Implement choiceN and then choice in terms of choiceN.
    */
  /** EXERCISE 7.12
    *
    * There’s still something rather arbitrary about choiceN. The choice of List seems
    * overly specific. Why does it matter what sort of container we have? For instance, what
    * if, instead of a list of computations, we have a Map of them:
    *
    * If you want, stop reading here and see if you can come up with a new and more general
    * combinator in terms of which you can implement choice, choiceN, and choiceMap.
    */
  def choiceMap[K, V](key: Par[K])(choices: Map[K, Par[V]]): Par[V]

  /** EXERCISE 7.13
    *
    * Implement this new primitive chooser, and then use it to implement choice and
    * choiceN.
    */

  /** EXERCISE 7.14
  *
  * Implement join. Can you see how to implement flatMap using join? And can you
  * implement join using flatMap?
  */

}
