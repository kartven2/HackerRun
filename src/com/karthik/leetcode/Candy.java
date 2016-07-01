/*
 * Leetcode: https://leetcode.com/problems/candy/
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candyArray = new int[n];
        candyArray[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyArray[i] = candyArray[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                int pos = i;
                int count = 1;
                while (pos + 1 < n && ratings[pos + 1] < ratings[pos]) {
                    count++;
                    pos++;
                }
                if (candyArray[i - 1] < count + 1) {
                    candyArray[i - 1] = count + 1;
                }
                for (int j = i; j <= pos; j++) {
                    candyArray[j] = count--;
                    i = j;
                }
            } else {
                candyArray[i] = 1;
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += candyArray[i];
        }
        return result;
    }

    public static void main(String... args) {
        Candy c = new Candy();
        int[] r = {1, 2, 2};
        System.out.println(c.candy(r));
    }
}
