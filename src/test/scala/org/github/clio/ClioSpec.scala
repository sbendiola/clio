package org.github.clio

import org.scalatest.FlatSpec 
import org.scalatest.matchers.ShouldMatchers

class ClioSpec extends FlatSpec with ShouldMatchers {
  "A Stack" should "pop values in last-in-first-out order" in {
    1 should be (2)
  }
  
  it should "throw NoSuchElementException if an empty stack is popped" in {
    Foo().bar should be (3)
  }
  case class Foo(bar: String = "123")
}