/*
Coding Exercise:
Write a program in Java that converts a sentence from English to Inflationary English. 
Inflationary English takes words and word parts that sound the same as a number 
(e.g. “won” v. “one”) and then inflates that to the next number (e.g. “won” becomes “two”). 
Provide tests for your program.
          
Example of input and output:

Input: Today I won an award for being awesome.
Output: Threeday I two an award five being awesome.

Input: zero xero won one to two too three four for five six sics seven eight ate ait nine
Output: one one two two three three three four five five six seven seven eight nine nine nine zero

Input: The quick brown fox jumps over the lazy dog
Output: The quick brown fox jumps over the lazy dog

Input: Before she was Wonder Woman, she was Diana, princess of the Amazons
Output: Befivee she was Twoder Woman, she was Diana, princess of the Amazons

*/
package excercise;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author priyankb
 */
public class InflationaryEnglish {

    //hashmap for words that sound the same as number to corresponding number
    HashMap<String, String> wordToTempNumb = new HashMap<>();
    //hashmap for nymbers to corresponding word for that number
    HashMap<String, String> tempNumbToWords = new HashMap<>();

    //initialize the calss and the hashmaps
    public InflationaryEnglish() {

        wordToTempNumb.put("zero", "@111@");
        wordToTempNumb.put("xero", "@111@");

        wordToTempNumb.put("won", "@222@");
        wordToTempNumb.put("one", "@222@");

        wordToTempNumb.put("to", "@333@");
        wordToTempNumb.put("two", "@333@");
        wordToTempNumb.put("too", "@333@");

        wordToTempNumb.put("three", "@444@");

        wordToTempNumb.put("four", "@555@");
        wordToTempNumb.put("for", "@555@");

        wordToTempNumb.put("five", "@666@");

        wordToTempNumb.put("six", "@777@");
        wordToTempNumb.put("sics", "@777@");

        wordToTempNumb.put("seven", "@888@");

        wordToTempNumb.put("eight", "@999@");
        wordToTempNumb.put("ate", "@999@");
        wordToTempNumb.put("ait", "@999@");

        wordToTempNumb.put("nine", "@000@");

        tempNumbToWords.put("@000@", "zero");
        tempNumbToWords.put("@111@", "one");
        tempNumbToWords.put("@222@", "two");
        tempNumbToWords.put("@333@", "three");
        tempNumbToWords.put("@444@", "four");
        tempNumbToWords.put("@555@", "five");
        tempNumbToWords.put("@666@", "six");
        tempNumbToWords.put("@777@", "seven");
        tempNumbToWords.put("@888@", "eight");
        tempNumbToWords.put("@999@", "nine");

    }

    //method to convert the input string to Inflationary English
    private String converrtToInflamtionary(String inputString) {
        //store for latter use
        String oldInputString = inputString;

        //replace every occurence of words to corresponding numbers 
        for (Map.Entry<String, String> entry : wordToTempNumb.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            inputString = inputString.replaceAll("(?i)" + key, value);

        }

        //replace every number to corresponding numbers
        for (Map.Entry<String, String> entry : tempNumbToWords.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            inputString = inputString.replaceAll("(?i)" + key, value);

        }

        //keep track of words that start with capital letters
        String pattern = "(?U)\\b\\p{Lu}\\p{L}*\\b";
        String[] words = oldInputString.split(" ");
        List<Integer> capitalWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].matches(pattern)) {
                capitalWords.add(i);
            }
        }

        // capitalize the fist char of every word that previously had a capital letter
        words = inputString.split(" ");
        for (int next : capitalWords) {
            words[next] = words[next].substring(0, 1).toUpperCase() + words[next].substring(1);
        }
        inputString = String.join(" ", words);

        return inputString;
    }

    public static void main(String[] args) throws IOException {

        InflationaryEnglish ie = new InflationaryEnglish();

        //while loop that breaks when 'exit' is written
        while (true) {
            System.out.println("Input: ");
            Scanner scan = new Scanner(System.in);
            String sentence = scan.nextLine();
            if (sentence.equalsIgnoreCase("exit")) {
                break;
            }
            sentence = ie.converrtToInflamtionary(sentence);
            System.out.println("Output: " + sentence);
            System.out.println("----------");

        }
    }
}
