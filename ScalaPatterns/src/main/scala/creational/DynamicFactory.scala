package creational

import scala.collection.mutable

/**
 * My idea of factory from Java 8 imported in Scala
 * (work in progress...)
 *
 * @author Daniel Leon
 */
object DynamicFactory {

  trait Animal {
    def speak()
    def name()
  }

  private class defaultAnimal extends Animal {
    override def speak(): Unit = println("What")

    override def name(): Unit = "Animal"
  }

  private class Dog extends Animal {
    override def speak() {
      println("Hello, I'm a dog!")
    }
    override def name() {
      "Dog"
    }
  }

  private class Cat extends Animal {
    override def speak() {
      println("Miau")
    }

    override def name() {
      "Cat"
    }
  }

  object Animal {
    var list = new mutable.LinkedHashSet[(String => Boolean, () => Animal)]()

    def addAnimal(predicate : String => Boolean, supplier : () => Animal) {
          list.add(predicate, supplier)
    }

    def apply(kind: String) = list.filter(tuple => tuple._1.apply(kind)).map( set => set._2.apply()).collectFirst {
      case animal:Animal => animal
      case _ => new defaultAnimal
    }
  }

  def main(args : Array[String]) {
    Animal.addAnimal( name => "dog".equals(name), () => new Dog())

    Animal("dog").getOrElse(new defaultAnimal).speak()
    Animal("cat").getOrElse(new defaultAnimal).speak()

    Animal.addAnimal( name => "cat".equals(name), () => new Cat())
    Animal("cat").get.speak()
  }
}
