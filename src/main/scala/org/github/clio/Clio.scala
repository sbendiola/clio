package org.github.clio

case class Opt(name: String, flag: Boolean = false, parent: Option[Opt] = None) {
  def as[T](implicit converter: conversions.Converter[T]) = OptValue[T](this)(converter)
  def apply(name: String) = {
    Opt(name, parent = Some(this))
  }
}

case class OptValue[T](o: Opt)(implicit converter: conversions.Converter[T])  

class Clio(args: Array[String]) {
  def o(name: String) = Opt(name)
  def f(name: String) = Opt(name, flag = true)
}

object conversions {  
  abstract class Converter[T] {
    def apply(text: String) : T
  }
  
  implicit object IntConverter extends Converter[Int] {
    def apply(text: String) = text.toInt
  }
}