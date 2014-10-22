package behavioral

/**
 * Null Object represents the absence of an object by defining a neutral, “do nothing” behavior.
 * This approach has an advantage over using null references, because there’s no need to explicitly check the validity of references before usage.
 *
 * We use so-called “for comprehension” to process the optional value and skip None objects
 *
 * @author Daniel Leon
 */

object NullObject {

  trait Sound {
    def play()
  }

  class Music extends Sound {
    def play() { println("Playing music") }
  }

  object SoundSource {
    def getSound: Option[Sound] = {
      val available = Math.random() < 0.5
      if (available) Some(new Music) else None
    }
  }

  def main(args:Array[String]) {

    (1 to 10)
      .map( i => SoundSource.getSound)
      .flatMap(sound => sound)
      .foreach(_.play())
  }
}
