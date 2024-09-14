package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Takes 2 parameters and either encodes or decodes a string using a key
 * and the caesar cipher method.
 */
public class AllCaesar {
  /**
   * Will either encode or decode a string using the caesar cipher.
   * @param args
   *  Takes 2 arguments - encode/decode and a string to en/decode
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
    } else if (!(CipherUtils.stringError(args[1]))) {
      System.err.println("Error: String contains characters other than lowercase letters.");
    } else {
      if (args[0].equals("encode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(args[1], ch));
        } // for loop
        pen.close();
      } else if (args[0].equals("decode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(args[1], ch));
        } // for loop
        pen.close();
      } else {
        System.err.printf("Error: Invalid option: \"%s\".", args[0]);
        System.err.printf("Valid options are \"encode\" or \"decode\".\n");
      } //endif not en or de code
    } //endif
  } // main
} // class AllCaesar
