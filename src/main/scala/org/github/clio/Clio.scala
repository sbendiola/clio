package org.github.clio

case class KeyValue(seperator: String = "")

class Clio(
  val args: Array[String], 
  val kv: KeyValue = KeyValue(seperator = "=")) {
        
  private val clio = this
  var values = List[Value]()
  
  def o = Opt
  
  def ov(name: String, 
         longName: String = "", 
         description: String = "", 
         required: Boolean = false, 
         kv: KeyValue = kv, 
         flag: Boolean = false, 
         parent: Option[Opt] = None) = 
    Opt(name = name, longName = longName, description = description, optional = !required, kv = kv, flag = flag, parent = parent)
  
  def f(name: String) = Opt(name, flag = true)
  
  def parse : this.type = {
    args.foreach(println)
    this
  }
  sealed trait Value
  
  case class OptValue[T]
    (o: Opt, multiValue: Boolean = false)
    (implicit converter: conversions.Converter[T]) extends Value {
      
    values = values.filterNot(_ == o)
    values ::= this
    
    def value : T = {
      println(o.kv)
      o.context.values.foreach(println)
      null.asInstanceOf[T]
    }
  }
  
  case class Opt(
    name: String, 
    longName: String = "",
    description: String = "", 
    optional: Boolean = true, 
    kv: KeyValue = kv, 
    flag: Boolean = false, 
    parent: Option[Opt] = None, 
    context: Clio = clio) extends Value {
    
    values ::= this      
    
    def as[T](implicit converter: conversions.Converter[T]) = 
      OptValue[T](this)(converter)

    def asList = OptValue[String](this, multiValue = true)(conversions.StringConverter)

    def asList[T](implicit converter: conversions.Converter[T]) = 
        OptValue[T](this, multiValue = true)(converter)

    def apply(name: String, 
              longName: String = "", 
              required: Boolean = false, 
              description: String = "", 
              flag: Boolean = false) = 
      new Opt(name = name, longName = longName, description = description, optional = !required, flag = flag, parent = Some(this))  
      
    def required = {
      copy(optional = false)
    }
    
    def value : String = {
      ""
    }
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