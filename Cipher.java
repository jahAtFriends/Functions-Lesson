/*
* Simple substitution cipher solver for illustration of methods in java.
* This class intended for Intro to Java
*
*
*
* compile: javac Cipher.java
* run:     java Cipher ["e" or "d"] ["message"] < key.txt
* options: e for encrypt, d for decrypt, message as a singls string.
* input:   key.txt some text file with a scrambling of the upper-case
*          english alphabet.
*
*
*
* (c) 2021 Joel Hammer
* Friends School of Baltimore
*
*/
import java.util.Scanner;

/**
* A simple substitution cipher. This library can encode and decode a given text
* expressed in latin characters (English alphabet). A key is required (usually
* given as a text file) but can be fed into StdIn/System.in in a variety of
* ways.
* </br>
* A fully-functional main method is provided which requires command line
* arguments for encrypt/decrypt option selection and the text to process.
* </br>
* <b>Helpful Note:</b> When providing a key through System.in, the key
* <i>must</i> be a scrambling of the complete, upper-case English alphabet.
* Other keys may result in unexpected behavior.
*/
public class Cipher {

    //Gobal variables
    static char[] alpha = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    static char[] key;
    
    /**
    * Encrypts a given plaintext using the globally-defined key. Requires loadKey() to be called first.
    * @param pt a plaintext message (as a String) to be encoded.
    * @return a char[] representing the encoding of each letter of the passed plaintext as a simple
    * substitution cipher.
    */
    public static char[] encrypt(String pt) {
        //Convert the plaintext to an uppercase array of characters.
        pt = pt.toUpperCase();
        char[] plaintext = pt.toCharArray();
        char[] ciphertext = new char[plaintext.length];
        
        //Encode each of the characters.
        for(int i = 0; i < plaintext.length; i++) {
            ciphertext[i] = encodeChar(plaintext[i]);
        }
        
        return ciphertext;
    }
    
   
    /**
    * Decrypts a given ciphertext using the globally-defined key. Requires loadKey() to be called first.
    * @param ct a ciphertext message (as a String) to be decoded.
    * @return a char[] representing the decoding of each letter of the passed ciphertext.
    */
    public static char[] decrypt(String ct) {
        ct = ct.toUpperCase();
        char[] ciphertext = ct.toCharArray();
        char[] plaintext = new char[ciphertext.length];
        
        for(int i = 0; i < ciphertext.length; i++) {
            plaintext[i] = decodeChar(ciphertext[i]);
        }
        
        return plaintext;
    }
    
    /**
    * Loads a key from StdIn. Must be run before encrypt() or decrypt().
    */
    public static void loadKey() {
        Scanner s = new Scanner(System.in);
        String rawKey = s.next();
        
        key = rawKey.toCharArray();
    }
    
    /**
    * Helper method to encode an individual character using a simple-substitution cipher.
    * requires loadKey() to be run first.
    * @param c a character of plaintext to be encoded.
    * @return the encoding of c using the loaded key.
    */
    public static char encodeChar(char c) {
        //Do not encode spaces.
        if (c == ' ') {
            return ' ';
        }
        
        //Determine the index of the given character in the alphabet.
        int i = 0;
        while(alpha[i] != c) {
            i++;
        }
        
        //Return the corresponding letter of the key.
        return key[i];
    }
    
    /**
    * Helper method to decode an individual character using a simple-substitution cipher.
    * Requires loadKey() to be run first.
    * @param c a character of ciphertext to be decoded.
    * @return the decoding of c using the loaded key.
    */
    public static char decodeChar(char c) {
        //Do not decode spaces.
        if (c == ' ') {
            return ' ';
        }
        
        //Determine the index of the given character in the key.
        int i = 0;
        while(key[i] != c) {
            i++;
        }
        
        //Return the corresponding letter of the plaintext.
        return alpha[i];
    }
    
    /**
    * Prints the given array of output to the terminal.
    */
    public static void print(char[] output) {
        //Print each element of the array on the same line.
        for(int i = 0; i < output.length; i++) {
            System.out.print(output[i]);
        }
        
        //Line break once all elements have been printed.
        System.out.println();
    }
    
    
    /**
    * Encrypts and decrypts a given message (coded or plaintext).
    * @param args an array of arguments from the command line, the first
    * should be "e" or "d" for "encrypt" or "decrypt" and the second should
    * be some message as a single String.
    */
    public static void main(String[] args) {
        
        //Require correct number of arguments.
        if (args.length != 2) {
            System.out.println("Usage is java Cipher [e or d] [message] ");
            return;
        }
        
        loadKey();
        
        //Encode/decode the input based on user-selected option.
        char[] output;
        if (args[0].equals("e")) {
            output = encrypt(args[1]);
        } else if (args[0].equals("d")) {
            output = decrypt(args[1]);
        } else {
            output = new char[0];
        }
        
        print(output);
    }

}