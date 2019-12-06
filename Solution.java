import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

/*
 *
 */
public class Solution {

  /*
   * Remove the last character in the string and put it into the bucket.
   */
  static void lastCharToBucket(StringBuilder sb, TreeMap<Character, Integer> bucket) {
    // **** put last character into bucket ****
    if (bucket.containsKey(sb.charAt(sb.length() - 1))) {
      int count = bucket.get(sb.charAt(sb.length() - 1));
      count++;
      bucket.put(sb.charAt(sb.length() - 1), count);
    } else {
      bucket.put(sb.charAt(sb.length() - 1), 1);
    }

    // **** remove last character from string ****
    sb.deleteCharAt(sb.length() - 1);
  }

  /*
   * Append char to string and remove from bucket (if needed).
   */
  static void appendCharRemoveFromBucket(char ch, StringBuilder sb, TreeMap<Character, Integer> bucket) {

    // **** append character to string ****
    sb.append(ch);

    // **** remove character from bucket or decrement count ****
    int count = bucket.get(ch);
    if (count == 1)
      bucket.remove(ch);
    else
      bucket.put(ch, --count);
  }

  /*
   * Complete the biggerIsGreater function below.
   */
  static String biggerIsGreater(String w) {

    // **** check for single character ****
    if (w.length() <= 1) {
      return "no answer";
    }

    // **** create a character bucket ****
    TreeMap<Character, Integer> bucket = new TreeMap<Character, Integer>();

    // **** for ease of use ****
    StringBuilder sb = new StringBuilder(w);

    // **** go left looking for smaller character ****
    while (sb.length() > 1) {

      // **** move last character in string into the bucket ****
      lastCharToBucket(sb, bucket);

      // **** check if larger character in bucket ****
      Map.Entry<Character, Integer> entry = bucket.higherEntry(sb.charAt(sb.length() - 1));
      if (entry != null) {
        break;
      }

    }

    // **** ****
    if (bucket.isEmpty())
      return "no answer";

    // **** look in bucket for the smallest character larger than this one ****
    Map.Entry<Character, Integer> entry = bucket.higherEntry(sb.charAt(sb.length() - 1));

    // **** no character found ****
    if (entry == null)
      return "no answer";

    // ***** for ease of use ****
    char ch = entry.getKey();

    // **** move last character in string into the bucket ****
    lastCharToBucket(sb, bucket);

    // **** append character to string and remove from bucket ****
    appendCharRemoveFromBucket(ch, sb, bucket);

    // **** loop appending characters (in ascending order) from bucket ****
    while (!bucket.isEmpty()) {

      // **** get smallest character from bucket ****
      ch = bucket.firstKey();

      // **** append character to string and remove from bucket ****
      appendCharRemoveFromBucket(ch, sb, bucket);

    }

    // **** return edited string ***
    return sb.toString();
  }

  /*
   * Test scaffolding.
   */
  public static void main(String[] args) {

    // **** open a scanner ****
    Scanner sc = new Scanner(System.in);

    // **** read the number of test casses ****
    int t = sc.nextInt();
    sc.nextLine();

    // **** loop reading and processing words ****
    for (int i = 0; i < t; i++) {

      // **** read the next word ****
      String w = sc.next();

      // ***** process the word ****
      System.out.println(biggerIsGreater(w));
    }

    // **** close scanner ****
    sc.close();
  }

}