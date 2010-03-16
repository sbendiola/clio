package org.github.clio

import org.scalatest.FlatSpec 
import org.scalatest.matchers.ShouldMatchers

class ClioSpec extends FlatSpec with ShouldMatchers {
  
  it should "support having the key and value in the same arg" in {
    pending
    val args = Array("--block-size=10")
    val clio = new Clio(args) {
      val blockSize = o("--block-size").as[Int]
    }
    clio.blockSize should be (10)    
  }
  
  it should "should set a property when the value follows the designator" in {
    pending
    val args = Array( "-buildfile", "mybuild.xml", "-Dproperty=value", "-Dproperty1=value1", "-projecthelp")
    val clio = new Clio(args) {
      val property = o("-D")("property")
    }      
    clio.property should be ("value")
    //clio.projectHelp should be (true)
  }

  it should "should support key with value" in {
    pending
    val args = Array("-ddebug")
    val clio = new Clio(args) {
      val debugFlag = o("-d")
    }      
    clio.debugFlag should be ("debug")
  }

  it should "should set flags" in {
    pending
    val args = Array("-projecthelp")
    val clio = new Clio(args) {
      val projectHelp = f("-projecthelp")
    }      
    clio.projectHelp should be ("true")
  }
  
  it should "allow clustering flags" in {
    pending
    val args = Array("-abc")
    val clio = new Clio(args) {
      val a = f("-a")
      val b = f("-b")
      val c = f("-c")
    }
    clio.a should be ("true")
    clio.b should be ("true")
    clio.c should be ("true")
  }

  it should "allow clustering flags in any order" in {
    pending
    val args = Array("-cab")
    val clio = new Clio(args) {
      val a = f("-a")
      val b = f("-b")
      val c = f("-c")
    }
    clio.a should be ("true")
    clio.b should be ("true")
    clio.c should be ("true")
  }
  
  it should "display help" in {
    pending
    val args = Array("-h")
    
    class HelpException extends Exception
    intercept[HelpException] {
      val clio = new Clio(args) {
        val a = f("-a")
        val b = f("-b")
        val c = f("-c")
      }  
    }
  }

  it should "display help on long flag" in {
    pending
    val args = Array("--help")
    
    class HelpException extends Exception
    intercept[HelpException] {
      val clio = new Clio(args) {
        val a = f("-a")
        val b = f("-b")
        val c = f("-c")
      }  
    }
  }

  it should "display help on ?" in {
    pending
    val args = Array("?")
    
    class HelpException extends Exception
    intercept[HelpException] {
      val clio = new Clio(args) {
        val a = f("-a")
        val b = f("-b")
        val c = f("-c")
      }  
    }
  }


  it should "display help on help" in {
    pending
    val args = Array("help")
    
    class HelpException extends Exception
    intercept[HelpException] {
      val clio = new Clio(args) {
        val a = f("-a")
        val b = f("-b")
        val c = f("-c")
      }  
    }
  }

  
  it should "should support long names" in {
    pending
    val args = Array( "--buildfile", "mybuild.xml")
    val clio = new Clio(args) {
      val buildFile = o("-b", "--buildfile")
    }      
    clio.buildFile should be ("mybuild.xml")
  }
  
  it should "should support multi args names" in {
    pending
    val args = Array("-f", "file1.xml", "file2.xml", "file3.xml")
    val clio = new Clio(args) {
      val files = o("-f").asList
    }      
    clio.files should be (List("file1.xml", "file2.xml", "file3.xml"))
  }

}