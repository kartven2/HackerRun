/*
 * Leetcode: https://leetcode.com/problems/find-duplicate-file-in-system/#/description
 *
 * Given a list of directory info including directory path,
 * and all the files with contents in this directory, you need to find out all the groups
 * of duplicate files in the file system in terms of their paths.
 * A group of duplicate files consists of at least two files that have exactly the same content.
 * A single directory info string in the input list has the following format:
 * "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
 * It means there are n files (f1.txt, f2.txt ... fn.txt 
 * with content f1_content, f2_content ... fn_content, respectively) in
 * directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
 * The output is a list of group of duplicate file paths. For each group,
 * it contains all the file paths of the files that have the same content.
 * A file path is a string that has the following format:
 * "directory_path/file_name.txt"
 *
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindDuplicateFileInSystem {

    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new LinkedList<>();
        if (paths == null || paths.length == 0) {
            return res;
        }
        int n = paths.length;
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] file = paths[i].split("\\s+");
            int m = file.length;
            for (int j = 1; j < m; j++) {
                int k = 0;
                for (; k < file[j].length() && file[j].charAt(k) != '('; k++);
                String fileName = file[j].substring(0, k);
                String content = file[j].substring(k + 1, file[j].length() - 1);
                Set<String> set = map.get(content);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(file[0] + "/" + fileName);
                map.put(content, set);
            }
        }
        for (Set<String> set : map.values()) {
            if (set.size() > 1) {
                List<String> sub = new LinkedList<>(set);
                res.add(sub);
            }
        }
        return res;
    }
}