/*
* Given a string consisting of letters, 'aa', 'bb' and 'cc', 
* we can perform the following operation: Take any two adjacent distinct characters and replace 
* them with the third character. For example, if 'aa' and 'cc' are adjacent, they can replaced by 'bb'. 
* Find the smallest string which we can obtain by applying this operation repeatedly?
*
* Input Format: 
* The first line contains the number of test cases TT. 
* TT test cases follow. Each test case contains the string you start with.
*
* Constraints:
*
* 1<=T<=100
* The string will have at most 100 characters.
*
*
* Output Format: 
* 
* Output TT lines, one for each test case, 
* containing the smallest length of the resultant string after applying the operations optimally.
*
* Sample Input:
*
* 3  
* cab  
* bcab  
* ccccc
*
* Sample Output:
*
* 2
* 1
* 5
*
 */
package com.karthik.hackerrank;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StringReduction {

    private int n;

    private String[] strInput;
    private int[] intOutput;

    public static void main(String[] args) {
        StringReduction solve = new StringReduction();
        solve.input();
        solve.compute();
        solve.output();
    }

    private void input() {
        //Scanner sc = new Scanner(System.in);
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./resources/inputfile2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }
        n = sc.nextInt();
        strInput = new String[n];
        intOutput = new int[n];
        for (int i = 0; i < n; i++) {
            strInput[i] = sc.next();
        }
    }

    private void output() {
        for (int x : intOutput) {
            System.out.println(x);
        }
    }

    private void compute() {
        for (int i = 0; i < n; i++) {
            intOutput[i] = processString(strInput[i]);
        }
    }

    private int processString(String str) {
        String reducedStr = reduceString(str, 0, new StringBuilder());
        String reducedStrFromEnd = reduceStringFromEnd(str, str.length() - 1, new StringBuilder());
        int result = Math.max(1, reducedStr.length());
        int endResult = Math.min(result, reducedStrFromEnd.length());
        return endResult;
    }

    private String reduceString(String str, int i, StringBuilder shortStr) {

        if (str.length() < 2) {
            return str;
        }

        if (isSameChar(str)) {
            return str;
        }

        if (i >= str.length()) {
            return reduceString(shortStr.toString(), 0, new StringBuilder());
        }

        if (i == str.length() - 1) {
            if (shortStr.length() < str.length()) {
                shortStr.append(str.charAt(i));
            }
            return reduceString(shortStr.toString(), 0, new StringBuilder());
        }

        if (str.charAt(i) == str.charAt(i + 1)) {
            if (shortStr.length() < str.length()) {
                shortStr.append(str.charAt(i));
            }
            return reduceString(str, i + 1, shortStr);
        }

        String twoChars = str.substring(i, i + 2);
        if (twoChars.equalsIgnoreCase("ab") || twoChars.equalsIgnoreCase("ba")) {
            shortStr.append("c");
            return reduceString(str, i + 2, shortStr);
        }

        if (twoChars.equalsIgnoreCase("bc") || twoChars.equalsIgnoreCase("cb")) {
            shortStr.append("a");
            return reduceString(str, i + 2, shortStr);
        }

        if (twoChars.equalsIgnoreCase("ca") || twoChars.equalsIgnoreCase("ac")) {
            shortStr.append("b");
            return reduceString(str, i + 2, shortStr);
        }

        return "";
    }

    private String reduceStringFromEnd(String str, int i, StringBuilder shortStr) {

        if (str.length() < 2) {
            return str;
        }

        if (isSameChar(str)) {
            return str;
        }

        if (i < 0) {
            return reduceStringFromEnd(shortStr.toString(), shortStr.toString().length() - 1, new StringBuilder());
        }

        if (i == 0) {
            if (shortStr.length() < str.length()) {
                shortStr.append(str.charAt(i));
            }
            return reduceStringFromEnd(shortStr.toString(), shortStr.toString().length() - 1, new StringBuilder());
        }

        if (str.charAt(i) == str.charAt(i - 1)) {
            if (shortStr.length() < str.length()) {
                shortStr.append(str.charAt(i));
            }
            return reduceStringFromEnd(str, i - 1, shortStr);
        }

        String twoChars = new StringBuilder().append(str.charAt(i - 1)).append(str.charAt(i)).toString();
        if (twoChars.equalsIgnoreCase("ab") || twoChars.equalsIgnoreCase("ba")) {
            shortStr.append("c");
            return reduceStringFromEnd(str, i - 2, shortStr);
        }

        if (twoChars.equalsIgnoreCase("bc") || twoChars.equalsIgnoreCase("cb")) {
            shortStr.append("a");
            return reduceStringFromEnd(str, i - 2, shortStr);
        }

        if (twoChars.equalsIgnoreCase("ca") || twoChars.equalsIgnoreCase("ac")) {
            shortStr.append("b");
            return reduceStringFromEnd(str, i - 2, shortStr);
        }

        return "";
    }

    private boolean isSameChar(String str) {
        char[] strArray = str.toCharArray();
        char c = strArray[0];
        for (int i = 1; i < strArray.length; i++) {
            if (c != strArray[i]) {
                return false;
            }
        }
        return true;
    }
}
