/*
 * Leetcode: https://leetcode.com/problems/contains-duplicate-iii/
 *
 * Given an array of integers, 
 * find out whether there are two distinct indices i and j in the array
 * such that the difference between nums[i] and nums[j] is at most t
 * and the difference between i and j is at most k.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ContainsDuplicateIII {

    private static final boolean RED = true;
    private static final boolean BLACK = !RED;

    class Node {

        private long key;
        private int count;
        private boolean color;
        private Node l, r;

        Node(long key) {
            this.key = key;
            this.color = RED;
        }
    }

    class RbTree {

        private Node root;
        private int n;

        private int size() {
            return n;
        }

        private boolean isEmpty() {
            return n == 0;
        }

        private boolean containsKey(long key) {
            return get(key) != null;
        }

        private Node get(long key) {
            return get(root, key);
        }

        private Node get(Node x, long key) {
            for (; x != null;) {
                if (x.key == key) {
                    return x;
                } else if (key < x.key) {
                    x = x.l;
                } else {
                    x = x.r;
                }
            }
            return null;
        }

        private boolean isRed(Node x) {
            if (x == null) {
                return false;
            }
            return x.color == RED;
        }

        private void put(long key) {
            root = put(root, key);
            root.color = BLACK;
        }

        private Node put(Node x, long key) {
            if (x == null) {
                x = new Node(key);
            }
            if (key == x.key) {
                x.count++;
                n++;
            } else if (key < x.key) {
                x.l = put(x.l, key);
            } else {
                x.r = put(x.r, key);
            }
            if (isRed(x.r) && !isRed(x.l)) {
                x = rotateLeft(x);
            }
            if (isRed(x.l) && isRed(x.l.l)) {
                x = rotateRight(x);
            }
            if (isRed(x.l) && isRed(x.r)) {
                flipColors(x);
            }
            return x;
        }

        private Node rotateLeft(Node h) {
            Node x = h.r;
            h.r = x.l;
            x.l = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.l;
            h.l = x.r;
            x.r = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private void flipColors(Node h) {
            h.l.color = !h.l.color;
            h.r.color = !h.r.color;
            h.color = !h.color;
        }

        private Node floor(long key) {
            return floor(root, key);
        }

        private Node floor(Node x, long key) {
            if (x == null) {
                return null;
            }
            if (key == x.key) {
                return x;
            } else if (key < x.key) {
                return floor(x.l, key);
            } else {
                Node t = floor(x.r, key);
                return t == null ? x : t;
            }
        }

        private Node ceil(long key) {
            return ceil(root, key);
        }

        private Node ceil(Node x, long key) {
            if (x == null) {
                return null;
            }
            if (x.key == key) {
                return x;
            } else if (x.key < key) {
                return ceil(x.r, key);
            } else {
                Node t = ceil(x.l, key);
                return t == null ? x : t;
            }
        }

        private Node min() {
            return min(root);
        }

        private Node min(Node x) {
            if (x.l == null) {
                return x;
            }
            return min(x.l);
        }

        public void delete(long key) {

            Node x = get(key);
            if (x != null && x.count > 1) {
                x.count--;
                return;
            }

            if (!isRed(root.l) && !isRed(root.r)) {
                root.color = RED;
            }

            root = delete(root, key);
            if (root != null) {
                root.color = BLACK;
            }
        }

        private Node moveRedRight(Node h) {
            flipColors(h);
            if (isRed(h.l.l)) {
                h = rotateRight(h);
                flipColors(h);
            }
            return h;
        }

        private Node delete(Node h, long key) {
            if (key < h.key) {
                if (!isRed(h.l) && !isRed(h.l.l)) {
                    h = moveRedLeft(h);
                }
                h.l = delete(h.l, key);
            } else {
                if (isRed(h.l)) {
                    h = rotateRight(h);
                }
                if (key == h.key && h.r == null) {
                    return null;
                }
                if (!isRed(h.r) && !isRed(h.r.l)) {
                    h = moveRedRight(h);
                }
                if (key == h.key) {
                    Node x = min(h.r);
                    h.key = x.key;
                    h.count = x.count;
                    h.r = deleteMin(h.r);
                } else {
                    h.r = delete(h.r, key);
                }
            }
            return balance(h);
        }

        private void deleteMin() {
            if (!isRed(root.l) && !isRed(root.r)) {
                root.color = RED;
            }
            root = deleteMin(root);
            if (root != null) {
                root.color = BLACK;
            }
        }

        private Node deleteMin(Node x) {
            if (x.l == null) {
                return null;
            }
            if (!isRed(x.l) && !isRed(x.l.l)) {
                x = moveRedLeft(x);
            }
            x.l = deleteMin(x.l);
            return balance(x);
        }

        private Node balance(Node h) {
            if (isRed(h.r)) {
                h = rotateLeft(h);
            }
            if (isRed(h.l) && isRed(h.l.l)) {
                h = rotateRight(h);
            }
            if (isRed(h.l) && isRed(h.r)) {
                flipColors(h);
            }
            return h;
        }

        private Node moveRedLeft(Node h) {
            flipColors(h);
            if (isRed(h.r.l)) {
                h.r = rotateRight(h.r);
                h = rotateLeft(h);
                flipColors(h);
            }
            return h;
        }

    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 1 || t < 0) {
            return false;
        }

        RbTree rbt = new RbTree();
        for (int i = 0, j = 0; i < nums.length; i++) {
            long ckey = (long) nums[i];
            if (!rbt.isEmpty()) {
                long min = ckey - t;
                long max = ckey + t;
                Node mnode = rbt.ceil(min);
                if (mnode != null && mnode.key <= max) {
                    return true;
                }
                Node xnode = rbt.floor(max);
                if (xnode != null && xnode.key >= min) {
                    return true;
                }
            }
            rbt.put(ckey);
            if (rbt.size() > k) {
                rbt.delete(nums[j++]);
            }
        }
        return false;
    }

    public static void main(String... args) {
        ContainsDuplicateIII cd = new ContainsDuplicateIII();
        System.out.println(cd.containsNearbyAlmostDuplicate(new int[]{1, 2147483647}, 1, 2147483647));
    }
}
