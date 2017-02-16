/*-
 * Print all substring of a string.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PrintSubstring {
    private void printSubstring(String a) {
        if(a==null || a.length()==0) {
            return;
        }
        int n = a.length();
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<=n; j++) {
                System.out.println(a.substring(i,j));
            }
        }
    }
    
    public static void main(String...args) {
        PrintSubstring ps = new PrintSubstring();
        ps.printSubstring("banana");
    }
}