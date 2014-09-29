package creational

/**
 * Scala provides a special syntactic construct, that looks similar to constructor invocation, but it’s actually a convenient factory method
 */

object Factory {

  trait Animal {
    def speak
    def name
  }

  private class Dog extends Animal {
    override def speak {
      println("Hello, I'm a dog!")
    }
    override def name {
      "Dog"
    }
  }

  private class Cat extends Animal {
    override def speak {
      println("Miau")
    }

    override def name {
      "Cat"
    }
  }

  object Animal {
    def apply(kind: String) = kind match {
      case "dog" => new Dog()
      case "cat" => new Cat()
    }
  }

  def main(args: Array[String]) {
    val dog = Animal("dog")
    val cat = Animal("cat")
    dog.speak
    cat.speak
  }
}
