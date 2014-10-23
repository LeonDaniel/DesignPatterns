package structural

import javax.crypto.{Cipher, KeyGenerator}

/**
 * The decorator pattern is used to extend functionality of some object, without affecting other instances of the same class.
 * Decorators provide a flexible alternative to sub-classing.
 *
 * To achieve this goal, Scala provides a direct way of overriding interface methods,
 * without binding to their concrete implementation in place of declaration, using abstract override modifier
 *
 * @author Daniel Leon
 */
object Decorator {

  //Component
  trait IEmail {
    def getContents : String
  }

  //Concrete component
  class Email(content: String) extends IEmail {

    override def getContents = content
  }

  //Concrete decorator
  trait ExternalEmailDecorator extends IEmail {
      //wrapped component
      abstract override def getContents = super.getContents + "\n Company Disclaimer"
  }

  //Concrete decorator
  trait SecureDecorator extends IEmail {

    abstract override def getContents = {
      val originalMessage = super.getContents
      val keyGenerator = KeyGenerator.getInstance("DES")
      val myDesKey = keyGenerator.generateKey()
      val desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding")
      desCipher.init(Cipher.ENCRYPT_MODE, myDesKey)
      val text = desCipher.doFinal(originalMessage.getBytes)
      new String(text)
    }
  }

  def main (args: Array[String]) {

    println("External implementation")
    val externalEmail = new Email("Scala rules") with ExternalEmailDecorator
    println(externalEmail.getContents)

    println("\nSecure implementation")
    val secureEmail = new Email("Scala rules big time") with SecureDecorator
    println(secureEmail.getContents)
  }
}
