package org.github.clio

import org.scalatest.FlatSpec 
import org.scalatest.matchers.ShouldMatchers

class ClioSpec extends FlatSpec with ShouldMatchers {
  it should "pop values in last-in-first-out order" in {
    pending
    val args = Array("--block-size=10")
    val clio = new Clio(args) {
      val blockSize = o("--block-size").as[Int]
    }
    clio.blockSize should be (10)    
  }
  
  it should "throw NoSuchElementException if an empty stack is popped" in {
    pending
    val args = Array( "-buildfile", "mybuild.xml", "-Dproperty=value", "-Dproperty1=value1", "-projecthelp")
    val clio = new Clio(args) {
      val buildFile = o("--buildfile")
      val property = o("-D")("property")
      val property1 = o("-D")("property1")
      val projectHelp = f("-projecthelp")
    }
    clio.buildFile should be ("mybuild.xml")
    clio.property should be ("value")
    clio.property1 should be ("value1")
    //clio.projectHelp should be (true)
  }
}