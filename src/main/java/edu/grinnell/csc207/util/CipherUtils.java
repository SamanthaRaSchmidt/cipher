package edu.grinnell.csc207.util;

/**
 * Functions needed to help implement both AllCaesar and Cipher.
 */
public class CipherUtils {

  /**
   * The number of letters in the alphabet.
   */
  private static final int NUM_LETTERS = 26;

  /**
   * The number of lowercase letters when starting at 0.
   */
  private static final int TOTAL_LETTERS = 25;

  /**
   * To help decipher coding and cipher types.
   */
  private static final int FIRST_OPTION = 1;

  /**
   * To help decipher coding and cipher types.
   */
  private static final int SECOND_OPTION = 2;

  /**
   * To help decipher coding and cipher types.
   */
  private static final int THIRD_OPTION = 3;

/**
 * Converts a letter into it's associated number 0-25.
 * @param letter
 *    the char that will be turned into a number 0-25
 * @return number
 *    an int that will be returned based on the numerical association with letter
 */
  private static int letter2int(char letter) {
    int number;
    number = (int) letter - (int) 'a';
    return number;
  } // letter2int (char)

  /**
   * Takes an integer 0-25 and converts it the corresponding lowercase char.
   * @param i
   *    an integer that must be between 0 and 25
   * @return letter
   *    The char associated with the given number
   */
  private static char int2letter(int i) {
    char letter;
    int temporary;
    temporary = (i + (int) 'a');
    letter = (char) temporary;
    return letter;
  } // int2letter (int)

   /**
    * Encodes a string according to the given letter using the caesar cipher.
    * @param str
    *   The string to be encoded
    * @param letter
        The letter that acts as a key to encrypt
    * @return ciphertext
    *   the encypted text after applying the caesar cipher
    */
  public static String caesarEncrypt(String str, char letter) {
    int length = str.length();
    char[] temp = str.toCharArray();

    /*
     * a for loop that will go through each letter of str,
     * adds the integer value of the key and then replaces
     * the letter in a  temp array
     */
    for (int i = 0; i < length; i++) {
      int coded = letter2int(str.charAt(i)) + letter2int(letter);
      if (coded > TOTAL_LETTERS) {
        coded -= NUM_LETTERS;
      } // endif
      char codedLetter = int2letter(coded);
      temp[i] = codedLetter;
    } // for loop

    String ciphertext = new String(temp);
    return ciphertext;
  } // caesarEncrypt (String, char)


  /**
   * Decodes an encrypted string using the char key.
   * @param str
   *    The encoded string
   * @param letter
   *    the char that will be used to decode the string
   * @return plaintext
   *    The string returned after being decoded by the key char
   */
  public static String caesarDecrypt(String str, char letter) {
    int length = str.length();
    char[] temp = str.toCharArray();

    /*
     * a for loop that will go through each letter of str,
     * adds the integer value of the key and then replaces
     * the letter in a temp array
     */
    for (int i = 0; i < length; i++) {
      int coded = letter2int(str.charAt(i)) - letter2int(letter);
      if (coded < 0) {
        coded += NUM_LETTERS;
      } // endif
      temp[i] = int2letter(coded);
    } // for loop

    String plaintext = new String(temp);
    return plaintext;
  } // caesarDecrypt (String, char)

  /**
   * Encodes a string using the vigenere cipher.
   * @param str
   *    The string that will be encoded
   * @param key
   *    The string that will be the key to encoding str
   * @return ciphertext
   *    The encoded string using the vigenere cipher
   */
  public static String vigenereEncrypt(String str, String key) {
    int stringLength = str.length();
    int keyLength = key.length();
    char[] keyArray = str.toCharArray();

    int j = 0;
    for (int i = 0; i < stringLength; i++) {
      keyArray[i] = key.charAt(j);
      j++;
      if (j == keyLength) {
        j = 0;
      } //endif
    } // for loop to equal the length of the original key


    String newKey = new String(keyArray);
    char[] encrypted = new char[stringLength];

    for (int i = 0; i < stringLength; i++) {
      int encryptedNumber;
      char encryptedLetter;

      encryptedNumber = letter2int(str.charAt(i)) + letter2int(newKey.charAt(i));
      if (encryptedNumber > TOTAL_LETTERS) {
        encryptedNumber -= NUM_LETTERS;
      } // endif
      encryptedLetter = int2letter(encryptedNumber);
      encrypted[i] = encryptedLetter;
    } // for loop to build up a new coded array
    String ciphertext = new String(encrypted);
    return ciphertext;
  } // vigenereEncrypt (String, String)

  /**
   * Decodes an encoded string using the vigenere cipher.
   * @param str
   *    The encoded string
   * @param key
   *    The string that will be used to decode str
   * @return ciphertext
   *    Returns the decoded string after applying the vigenere cipher backwards
   */
  public static String vigenereDecrypt(String str, String key) {

    int stringLength = str.length();
    int keyLength = key.length();
    char[] keyArray = str.toCharArray();

    int j = 0;
    for (int i = 0; i < stringLength; i++) {
      keyArray[i] = key.charAt(j);
      j++;
      if (j == keyLength) {
        j = 0;
      } //endif
    } // for loop to equal the length of the original key

    String newKey = new String(keyArray);

    char[] encrypted = new char[stringLength];

    for (int i = 0; i < stringLength; i++) {
      int encryptedNumber;
      char encryptedLetter;

      encryptedNumber = letter2int(str.charAt(i)) - letter2int(newKey.charAt(i));
      if (encryptedNumber < 0) {
        encryptedNumber += NUM_LETTERS;
      } // endif
      encryptedLetter = int2letter(encryptedNumber);

      encrypted[i] = encryptedLetter;
    } // for loop to build up a new coded array
    String ciphertext = new String(encrypted);

    return ciphertext;
  } // vigenereDecrypt (String, String)

  /**
   * Checks if a string has all lowercase letter.
   * @param str
   *  the given string or arg
   * @return boolean
   *  true if all lowercase, false otherwise
   */
  public static boolean stringError(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (letter2int(str.charAt(i)) > TOTAL_LETTERS || letter2int(str.charAt(i)) < 0) {
        return false;
      } // endif checks for lowercase only
    } // for loop
    return true;
  } // stringError (String)

  /**
   * Checks the type of cipher in an args.
   * @param str
   *  the given string or arg
   * @return int
   *  1 if caesar, 2 if vigenere, 3 elsewise
   */
  public static int checkCipher(String str) {
    if (str.equals("-caesar")) {
      return FIRST_OPTION;
    } else if (str.equals("-vigenere")) {
      return SECOND_OPTION;
    } else {
      return THIRD_OPTION;
    } //endif else
  } //checkCipher (String)

  /**
  * checks whether to encode or decode a string.
  * @param str
  *  the given string or arg
  * @return int
  *  1 if encode, 2 if decode and 3 elsewise
  */
  public static int checkCoding(String str) {
    if (str.equals("-encode")) {
      return FIRST_OPTION;
    } else if (str.equals("-decode")) {
      return SECOND_OPTION;
    } else {
      return THIRD_OPTION;
    } //endif else
  } //checkCoding (String)
} // class CipherUtils
