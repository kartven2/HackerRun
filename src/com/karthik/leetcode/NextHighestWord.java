/*
 * Next highest word
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NextHighestWord {

    public String nextHighestWord(String x) {
        if (x == null || x.length() == 0) {
            return x;
        }
        //HEFB -> HFEB
        //HEFG -> HEGF
        //HEBB -> HEBB
        //EHBB -> HEBB
        int n = x.length(), hiidx = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (x.charAt(i) < x.charAt(i + 1)) {
                hiidx = i;
                break;
            }
        }
        if (hiidx == -1) {
            return "No Answer";
        }
        char[] res = x.toCharArray();
        for (int j = n - 1; j > hiidx; j--) {
            if (res[j] > res[hiidx]) {
                char temp = res[j];
                res[j] = res[hiidx];
                res[hiidx] = temp;
                break;
            }
        }
        for (int i = hiidx + 1, j = n - 1; i < j; i++, j--) {
            char temp = res[i];
            res[i] = res[j];
            res[j] = temp;
        }
        return new String(res);
    }

    public static void main(String... args) {
        NextHighestWord nw = new NextHighestWord();
        System.out.println(nw.nextHighestWord("814297"));
        System.out.println(nw.nextHighestWord("HEFG"));
        System.out.println(nw.nextHighestWord("HEBB"));
        System.out.println(nw.nextHighestWord("EHBB"));
    }
}
