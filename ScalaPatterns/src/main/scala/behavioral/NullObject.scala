package behavioral

/**
 * we use so-called “for comprehension” to process the optional value
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

    (1 to 10).map( i => SoundSource.getSound).flatMap(sound => sound).foreach(sound => sound.play())
  }
}
