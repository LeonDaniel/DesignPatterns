package structural;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * The decorator pattern is used to extend functionality of some object, without affecting other instances of the same class.
 * Decorators provide a flexible alternative to sub-classing.
 */
interface IEmail
{
    String getContents();
}

//concrete component
class Email implements IEmail
{
    private String content;

    public Email(String content)
    {
        this.content = content;
    }

    @Override
    public String getContents() {
        //general email stuff
        return content;
    }
}


//region Decorators
abstract class EmailDecorator implements IEmail
{
    //wrapped component
    IEmail originalEmail;

}

//concrete decorator
class ExternalEmailDecorator extends EmailDecorator
{
    private String content;

    public ExternalEmailDecorator(IEmail basicEmail)
    {
        originalEmail = basicEmail;
    }

    @Override
    public String getContents()
    {
        //  decorate original
        content = addDisclaimer(originalEmail.getContents());
        return content;
    }

    private String addDisclaimer(String message)
    {
        //append company disclaimer to message
        return  message + "\n Company Disclaimer";
    }

}

//concrete decorator
class SecureEmailDecorator extends EmailDecorator
{
    private String content;
    private SecretKey myDesKey;
    private Cipher desCipher;

    public SecureEmailDecorator(IEmail basicEmail)
    {
        originalEmail = basicEmail;
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = keygenerator.generateKey();
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getContents()
    {
        //  secure original
        content = encrypt(originalEmail.getContents());
        return content;
    }

    private String encrypt(String message)
    {
        //encrypt the string
        String encryptedMessage = null;
        try {
            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            // Encrypt the text
            byte[] textEncrypted = desCipher.doFinal(message.getBytes());
            encryptedMessage = new String(textEncrypted);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return  encryptedMessage;
    }
}
//endregion

class EmailSender
{

    public static void sendEmail(IEmail email) {
        //read the email to-address, to see if it's going outside of the company
        //if so decorate it
        ExternalEmailDecorator external = new ExternalEmailDecorator(email);
        //send
        System.out.println(external.getContents());
    }

    public static void sendSecureEmail(IEmail email) {
        SecureEmailDecorator secureEmailDecorator = new SecureEmailDecorator(email);
        System.out.println(secureEmailDecorator.getContents());
    }
}

public class Decorator {

    public static void main(String[] args) {

        IEmail email = new Email("Sending a nice message from java");

        System.out.println("External implementation");
        EmailSender.sendEmail(email);
        EmailSender.sendEmail( () -> "ceva");

        System.out.println("\nSecure implementation");
        EmailSender.sendSecureEmail(email);
    }
}
