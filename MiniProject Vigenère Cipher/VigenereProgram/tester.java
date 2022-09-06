
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class tester {
    VigenereCipher VigCipher;
    public tester() {
        int[] key = {17,14,12,4};
        VigCipher = new VigenereCipher(key);
    }
    public void testerOn() {
        FileResource resource = new FileResource();
        String s = resource.asString();
        System.out.println("The message is: ");
        System.out.println(s);
        String encryptVigCipher = VigCipher.encrypt(s);
        System.out.println("The encrypted message is: ");
        System.out.println(encryptVigCipher);
    }

}
