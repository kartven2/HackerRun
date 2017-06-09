/*
 * LeetCode: https://leetcode.com/problems/kill-process/#/description
 * Given n processes, each process has a unique PID (process id) and
 * its PPID (parent process id).
 * Each process only has one parent process, but may have one or more
 * children processes. This is just like a tree structure.
 * Only one process has PPID that is 0, which means this process has no parent process.
 * All the PIDs will be distinct positive integers.
 * We use two list of integers to represent a list of processes,
 * where the first list contains PID for each process and the second list contains the corresponding PPID.
 * Now given the two lists, and a PID representing a process you want to kill,
 * return a list of PIDs of processes that will be killed in the end.
 * You should assume that when a process is killed, all its children processes will be killed.
 * No order is required for the final answer.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new LinkedList<>();
        if (pid == null || ppid == null || pid.isEmpty() || ppid.isEmpty()) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int i = 0;
        for (int x : ppid) {
            List<Integer> list = map.get(x);
            if (list == null) {
                list = new LinkedList<>();
            }
            list.add(pid.get(i++));
            map.put(x, list);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(kill);
        while (!q.isEmpty()) {
            int v = q.remove();
            res.add(v);
            List<Integer> list = map.get(v);
            if (list == null) {
                continue;
            }
            for (int w : list) {
                q.add(w);
            }
        }
        return res;
    }
}
