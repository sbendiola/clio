package org.github.clio

class Clio(val args: Array[String]) {
  var values = List[Value]()
  def o = Opt
    
  def f(name: String) = Opt(name, flag = true)
  
  sealed trait Value {
    println(values)
  }
  
  case class OptValue[T](o: Opt, multiValue: Boolean = false)(implicit converter: conversions.Converter[T]) extends Value {
    values = values.filterNot(_ == o)
    values ::= this
  }
  
  case class Opt(name: String, longName: String = "", description: String = "", required: Boolean = false, flag: Boolean = false, parent: Option[Opt] = None) extends Value {
    values ::= this
    def as[T](implicit converter: conversions.Converter[T]) = 
      OptValue[T](this)(converter)

    def asList = OptValue[String](this, multiValue = true)(conversions.StringConverter)

    def asList[T](implicit converter: conversions.Converter[T]) = 
        OptValue[T](this, multiValue = true)(converter)

    def apply(name: String, longName: String = "", required: Boolean = false, description: String = "", flag: Boolean = false) = 
      new Opt(name = name, longName = longName, description = description, required = required, flag = flag, parent = Some(this))  
  }  
}

object conversions {  
  abstract class Converter[T] {
    def apply(text: String) : T
  }
  
  implicit object IntConverter extends Converter[Int] {
    def apply(text: String) = text.toInt
  }
  
  implicit object FloatConverter extends Converter[Float] {
    def apply(text: String) = text.toFloat
  }

  implicit object ShortConverter extends Converter[Short] {
    def apply(text: String) = text.toShort
  }  
  
  implicit object StringConverter extends Converter[String] {
    def apply(text: String) = text
  }
}