package net.gumbix.hl7dsl

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */

object NestedTest {
  def main(args: Array[String]) {
    val a = new A() {
      b = new B(2)
      // doB(3).b2 = 4
      // doB(3){b2 = 4}
    }
    println(a)
  }
}

class A {
  def doB(a: Int)(cmd: => Unit) = {
    b = new B(a) {
      cmd
    }
    b
  }
  var b = new B(1)

  override def toString = b.toString
}

class B(var b1: Int) {
  var b2 = 2
  override def toString = b1.toString + ", " + b2.toString
}