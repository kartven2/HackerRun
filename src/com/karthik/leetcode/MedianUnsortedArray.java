package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MedianUnsortedArray {

    private int[] nums;
    private int n;

    private void exchange(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int partition(int lo, int hi) {
        int v = nums[lo], i = lo, j = hi + 1;
        while (true) {
            while (nums[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            while (nums[--j] > v) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(i, j);
        }
        exchange(lo, j);
        return j;
    }

    private int smallest(int k, int lo, int hi) {
        while (true) {
            int m = partition(lo, hi);
            if (k == m) {
                return nums[m];
            } else if (k > m) {
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }
    }

    public void findMedian(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        this.nums = nums;
        this.n = nums.length;
        double median = 0.0;
        if (n % 2 == 0) {
            int a = smallest((n >> 1) - 1, 0, n - 1);
            int b = smallest(n >> 1, 0, n - 1);
            median = (double) (a + b) / 2;
        } else {
            median = (double) smallest(n >> 1, 0, n - 1);
        }
        System.out.println("Median :" + median);
    }

    public static void main(String... args) {
        int[] input = {4, 3, 6, 2, 10, 50, 100};
        //2,3,4,6,10,50,100
        MedianUnsortedArray ws = new MedianUnsortedArray();
        ws.findMedian(input);
    }
}
