import java.util.*;
import edu.duke.*;
import java.lang.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slicing = new StringBuilder(message);
        String result = new String();
        for (int i = whichSlice; i <slicing.length(); i += totalSlices) {
             
            result += slicing.charAt(i);;
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        CaesarCracker CaesarCracker = new CaesarCracker('e');
        int aKey;
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int k = 0; k < klength; k++) {
            aKey = CaesarCracker.getKey(sliceString(encrypted,k,klength));
            //System.out.println(aKey);
            key[k] = aKey;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        //
        HashSet<String> h = new HashSet<String>();
        for(String line: fr.lines()) {
           h.add(line.toLowerCase());
        }
        for (String s:h) {
            //System.out.println("HashSet output"+s+"\t");
            
        }
        return h;
    }
    public int countWords(String message, HashSet<String> dict) {
        int count = 0;
        ArrayList<String> MessageInWords = new ArrayList<String>(Arrays.asList(message.split("\\W")));
        for(int i =0; i< MessageInWords.size(); i++) {
            //
            if (dict.contains(MessageInWords.get(1).toLowerCase())) {
                count += 1;
            }
            
        }
        return count;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dict) {
        int max = 0;
        int[] keyReturn = new int[100];
        int keyLength = 0;
        String aMessage = new String();
        String largestDecryption = new String();
        String[] decrypted = new String[100];
        for (int klength = 1; klength< 100; klength++) {
            keyReturn = tryKeyLength(encrypted,klength,'e');
            VigenereCipher VCipher = new VigenereCipher(keyReturn);
            aMessage = VCipher.decrypt(encrypted);
            int counts = countWords(aMessage,dict);
            if(counts > max) {
                //
                max = counts;
                largestDecryption = aMessage;
                keyLength = klength;
            }
        }
        System.out.println("Max counts:" + max);
        System.out.println("The proper Key Length is: "+keyLength);
        return largestDecryption;
        
    }
    
    public String breakVigenere() {
        //
        String MaxDecryption = new String();
        FileResource resource = new FileResource();
        String message = resource.asString();
        HashSet<String> DictContent = new HashSet<String>();
        FileResource dictResource = new FileResource("dictionaries/English");
        DictContent = readDictionary(dictResource);
        MaxDecryption = breakForLanguage(message,DictContent);
        
        return MaxDecryption;
    }
    
    
}
