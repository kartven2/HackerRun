/*
 * LeetCode: https://leetcode.com/problems/exclusive-time-of-functions/description/
 */
package com.karthik.leetcode;

import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ExclusiveTimeFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        if (n == 0 || logs == null || logs.isEmpty()) {
            return null;
        }
        int[] result = new int[n];
        int time = 0;
        Stack<Integer> stk = new Stack<>();
        for (String ip : logs) {
            String[] arr = ip.split(":");
            int id = Integer.parseInt(arr[0]);
            boolean start = arr[1].equals("start");
            int ctime = Integer.parseInt(arr[2]);
            if (start) {
                if (!stk.isEmpty()) {
                    result[stk.peek()] += (ctime - time);
                }
                stk.push(id);
            } else {
                result[id] += (++ctime - time);
                stk.pop();
            }
            time = ctime;
        }
        return result;
    }
}
