import scala.util.matching.Regex

trait Chapter09 {

  /** EXERCISE 9.1
    *
    * Using product, implement the now-familiar combinator map2 and then use this to
    * implement many1 in terms of many. Note that we could have chosen to make map2
    * primitive and defined product in terms of map2 as we’ve done in previous chapters.
    * The choice is up to you.
    */
  def map2[Parser[_], A, B, C](p: Parser[A], p2: Parser[B])(
      f: (A, B) => C): Parser[C]

  /** EXERCISE 9.2
    *
    * Try coming up with laws to specify the behavior of product.
    */
  /** EXERCISE 9.3
    *
    * Before continuing, see if you can define many in terms of or, map2, and succeed.
    */
  /** EXERCISE 9.4
    *
    * Using map2 and succeed, implement the listOfN combinator from earlier.
    */
  def listOfN[Parser[_], A](n: Int, p: Parser[A]): Parser[List[A]]

  /** EXERCISE 9.5
    *
    * We could also deal with non-strictness with a separate combinator like we did in chapter
    * 7. Try this here and make the necessary changes to your existing combinators.
    * What do you think of that approach in this instance?
    */
  /** EXERCISE 9.6
    *
    * Using flatMap and any other combinators, write the context-sensitive parser we
    * couldn’t express earlier. To parse the digits, you can make use of a new primitive,
    * regex, which promotes a regular expression to a Parser. In Scala, a string s can
    * be promoted to a Regex object (which has methods for matching) using s.r, for
    * instance, "[a-zA-Z_][a-zA-Z0-9_]*".r.
    */
  implicit def regex[Parser[_]](r: Regex): Parser[String]

  /** EXERCISE 9.7
    *
    * Implement product and map2 in terms of flatMap.
    */

  /** EXERCISE 9.8
    *
    * map is no longer primitive. Express it in terms of flatMap and/or other combinators.
    */

  /** EXERCISE 9.9
    *
    * At this point, you are going to take over the process. You’ll be creating a
    * Parser[JSON] from scratch using the primitives we’ve defined. You don’t need to
    * worry (yet) about the representation of Parser. As you go, you’ll undoubtedly discover
    * additional combinators and idioms, notice and factor out common patterns,
    * and so on. Use the skills you’ve been developing throughout this book, and have fun!
    * If you get stuck, you can always consult the answers.
    *
    * Here’s some minimal guidance:
    *   - Any general-purpose combinators you discover can be added to the Parsers
    *     trait directly.
    *   - You’ll probably want to introduce combinators that make it easier to parse the
    *     tokens of the JSON format (like string literals and numbers). For this you could
    *     use the regex primitive we introduced earlier. You could also add a few primitives
    *     like letter, digit, whitespace, and so on, for building up your token
    *     parsers.
    */

  /** EXERCISE 9.10
    *
    * If you haven’t already done so, spend some time discovering a nice set of combinators
    * for expressing what errors get reported by a Parser. For each combinator, try
    * to come up with laws specifying what its behavior should be. This is a very open-ended
    * design task. Here are some guiding questions:
    *   - Given the parser "abra".**(" ".many).**("cadabra"), what sort of error would
    *     you like to report given the input "abra cAdabra" (note the capital 'A')? Only
    *     something like Expected 'a'? Or Expected "cadabra"? What if you wanted to
    *     choose a different error message, like "Magic word incorrect, try again!"?
    *   - Given a or b, if a fails on the input, do we always want to run b, or are there
    *     cases where we might not want to? If there are such cases, can you think of additional
    *     combinators that would allow the programmer to specify when or should
    *     consider the second parser?
    *   - How do you want to handle reporting the location of errors?
    *   - Given a or b, if a and b both fail on the input, might we want to support reporting
    *     both errors? And do we always want to report both errors, or do we want to
    *     give the programmer a way to specify which of the two errors is reported?
    */

  /** EXERCISE 9.11
    *
    * Can you think of any other primitives that might be useful for letting the programmer
    * specify what error(s) in an or chain get reported?
    */

  /** EXERCISE 9.12
    *
    * In the next section, we’ll work through a representation for Parser and implement
    * the Parsers interface using this representation. But before we do that, try to
    * come up with some ideas on your own. This is a very open-ended design task, but the
    * algebra we’ve designed places strong constraints on possible representations. You
    * should be able to come up with a simple, purely functional representation of Parser
    * that can be used to implement the Parsers interface.
    *
    * Your code will likely look something like this:
    *
    * class MyParser[+A](...) { ... }
    *
    * object MyParsers extends Parsers[MyParser] {
    *   implementations of primitives go here
    * }
    */

  /** EXERCISE 9.13
    *
    * Implement string, regex, succeed, and slice for this initial representation of
    * Parser. Note that slice is less efficient than it could be, since it must still construct a
    * value only to discard it. We’ll return to this later.
    */

  /** EXERCISE 9.14
    *
    * Revise your implementation of string to use scope and/or label to provide a meaningful
    * error message in the event of an error.
    */

  /** EXERCISE 9.15
    *
    * Implement the rest of the primitives, including run, using this representation of
    * Parser, and try running your JSON parser on various inputs.
    */

  /** EXERCISE 9.16
    *
    * Come up with a nice way of formatting a ParseError for human consumption. There
    * are a lot of choices to make, but a key insight is that we typically want to combine or
    * group labels attached to the same location when presenting the error as a String for
    * display.
    */

  /** EXERCISE 9.17
    *
    * The slice combinator is still less efficient than it could be. For instance,
    * many(char('a')).slice will still build up a List[Char], only to discard it. Can you
    * think of a way of modifying the Parser representation to make slicing more efficient?
    */

  /** EXERCISE 9.18
  *
  * Some information is lost when we combine parsers with the or combinator. If both
  * parsers fail, we’re only keeping the errors from the second parser. But we might want
  * to show both error messages, or choose the error from whichever branch got furthest
  * without failing. Change the representation of ParseError to keep track of errors that
  * occurred in other branches of the parser.
  */

}
