package structural

import javax.crypto.{Cipher, KeyGenerator}

/**
 * To achieve the same goal, Scala provides a direct way of overriding interface methods,
 * without binding to their concrete implementation in place of declaration, using abstract override modifier
 */
object Decorator {

  trait IEmail {
    def getContents() : String
  }

  class Email(content: String) extends IEmail {

    override def getContents = content
  }

  trait ExternalEmailDecorator extends IEmail {
      //wrapped component
      abstract override def getContents = super.getContents + "\n Company Disclaimer"
  }

  trait SecureDecorator extends IEmail {

    abstract override def getContents = {
      val originalMessage = super.getContents
      val keygenerator = KeyGenerator.getInstance("DES")
      val myDesKey = keygenerator.generateKey();
      val desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding")
      desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
      val text = desCipher.doFinal(originalMessage.getBytes())
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
