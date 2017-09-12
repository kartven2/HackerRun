/*
* LeetCode: https://leetcode.com/problems/task-scheduler/description/
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TaskScheduler {

    public static void main(String... args) {
        TaskScheduler ts = new TaskScheduler();
        char[] c = {'A', 'B'};
        ts.leastInterval(c, 2);
    }

    class Task implements Comparable<Task> {

        char c;
        int freq;

        Task(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }

        @Override
        public int compareTo(Task b) {
            if (this.freq != b.freq) {
                return b.freq - this.freq;
            }
            return this.c - b.c;
        }
    }

    public int leastInterval(char[] a, int n) {
        if (a == null || a.length == 0) {
            return 0;
        }
        if (n == 0) {
            return a.length;
        }
        int m = a.length;
        int[] fq = new int[26];
        for (int i = 0; i < m; i++) {
            fq[a[i] - 'A']++;
        }
        PriorityQueue<Task> pq = new PriorityQueue<>();
        for (int i = 0; i < 26; i++) {
            if (fq[i] > 0) {
                pq.add(new Task((char) (i + 'A'), fq[i]));
            }
        }
        int count = 0;
        while (!pq.isEmpty()) {
            int k = n + 1;
            List<Task> done = new ArrayList<>();
            while (!pq.isEmpty() && k > 0) {
                Task v = pq.poll();
                v.freq--;
                if (v.freq > 0) {
                    done.add(v);
                }
                k--;
                count++;
            }
            for (Task t : done) {
                pq.add(t);
            }
            if (pq.isEmpty()) {
                break;
            }
            count += k;
        }
        return count;
    }
}
