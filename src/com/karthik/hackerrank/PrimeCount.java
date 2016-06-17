/*
 * Fast prime count practice.
 *
 * Find all prime numbers less than n;
 */
package com.karthik.hackerrank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PrimeCount {

    private List<Integer> smallPrimes;
    private List<Integer> primes;
    private int n;
    private int t;
    private Scanner sc;

    private void compute() throws FileNotFoundException {
        //Scanner sc = new Scanner(System.in);
        sc = new Scanner(new FileInputStream("./resources/primetest"));
        t = sc.nextInt();
        while (t-- > 0) {
            smallPrimes = new ArrayList<>();
            primes = new ArrayList<>();
            n = sc.nextInt();
            segmentedSieve(n);
        }
    }

    private void printOutput(List<Integer> output) {
        if (output != null && !output.isEmpty()) {
            System.out.println("There are " + output.size() + " prime numbers less than " + n);
            Iterator<Integer> primeItr = output.iterator();
            while (primeItr.hasNext()) {
                System.out.print(primeItr.next());
                if (primeItr.hasNext()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
    }

    private void simpleSieve(int limit) {
        BitSet mark = new BitSet(limit + 1);
        mark.set(0, limit + 1);
        for (int p = 2; p * p < limit; p++) {
            if (mark.get(p)) {
                for (int i = 2 * p; i < limit; i += p) {
                    mark.clear(i);
                }
            }
        }
        for (int j = 2; j < limit; j++) {
            if (mark.get(j)) {
                smallPrimes.add(j);
            }
        }
    }

    private void segmentedSieve(int x) {
        if (x < 2) {
            printOutput(primes);
            return;
        }
        int limit = roundSqrt(x);
        simpleSieve(limit);
        primes.addAll(smallPrimes);
        int low = limit;
        int high = 2 * limit;

        while (low < x) {
            BitSet mark = new BitSet(limit + 1);
            mark.set(0, limit + 1);
            for (int i = 0; i < smallPrimes.size(); i++) {
                int primesi = smallPrimes.get(i);
                int loLimit = Math.floorDiv(low, primesi) * primesi;
                if (loLimit < low) {
                    loLimit += primesi;
                }
                for (int j = loLimit; j < high; j += primesi) {
                    mark.clear(j - low);
                }
            }
            for (int i = low; i < high; i++) {
                if (mark.get(i - low)) {
                    primes.add(i);
                }
            }
            low += limit;
            high += limit;
            if (high >= x) {
                high = x;
            }
        }

        printOutput(primes);
    }

    private int roundSqrt(double x) {
        double result = Math.sqrt(x);
        return (result % 1 == 0.0d) ? (int) result : (int) (result + 1);
    }

    public static void main(String... args) {
        PrimeCount p = new PrimeCount();
        try {
            p.compute();
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
