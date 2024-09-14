package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;

/**
 * Takes 4 arguments and gives a coded or decoded message back.
 */
public class Cipher {

  /**
   * The number of expected args.
   */
  private static final int NUM_ARGS = 4;

  /**
   * main.
   * @param args
   */
  public static void main(String[] args) {

    String cipher = null;
    String coding = null;
    String str = null;
    String key = null;

    for (int i = 0; i < args.length; i++) {
      if (CipherUtils.checkCipher(args[i]) == 1 && cipher == null) {
        cipher = "-caesar";
      } else if (CipherUtils.checkCipher(args[i]) == 2 && cipher == null) {
        cipher = "-vigenere";
      } else if (CipherUtils.checkCoding(args[i]) == 1 && coding == null) {
        coding = "-encode";
      } else if (CipherUtils.checkCoding(args[i]) == 2 && coding == null) {
        coding = "-decode";
      } else if (CipherUtils.stringError(args[i]) && str == null) {
        str = args[i];
      } else {
        key = args[i];
      } //endif the key
    } // for loop

    if (args.length != NUM_ARGS) {
      System.err.printf("Error: Expected 4 parameters, recieved %d\n", args.length);
    } else if (!(CipherUtils.stringError(str))) {
      System.err.println("Error: String contains characters other than lowercase letters.");
    } else if (!(CipherUtils.stringError(key))) {
      System.err.println("Error: Key contains characters other than lowercase letters.");
    } else if (key.length() == 0) {
      System.err.println("Error: Empty keys are not permitted.");
    } else {
      if (coding.equals("-encode")) {
        if (cipher.equals("-caesar")) {
          if (key.length() != 1) {
            System.err.println("Error: Caesar ciphers require a one-character key");
          } else {
            System.out.printf("%s\n", CipherUtils.caesarEncrypt(str, key.charAt(0)));
          } //endif regarding caesarEncrypt
        } else if (cipher.equals("-vigenere")) {
          System.out.printf("%s\n", CipherUtils.vigenereEncrypt(str, key));
        } else {
          System.err.print("Error: No valid action specified. ");
          System.err.println("Legal values are '-caesar' and '-vigenere'");
        } // endif error for non caesar or vigenere
      } else if (coding.equals("-decode")) {
        if (cipher.equals("-caesar")) {
          if (key.length() != 1) {
            System.err.println("Error: Caesar ciphers require a one-character key");
          } else {
            System.out.printf("%s\n", CipherUtils.caesarDecrypt(str, key.charAt(0)));
          } //endif regarding caesarDecrypt
        } else if (cipher.equals("-vigenere")) {
          System.out.printf("%s\n", CipherUtils.vigenereDecrypt(str, key));
        } else {
          System.err.print("Error: No valid action specified. ");
          System.err.println("Legal values are '-caesar' and '-vigenere'");
        } // endif error for non c or v
      } // end decode if
    } //end else
  } // main()
} // class Cipher
