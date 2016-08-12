/*
 * LeetCode problem :
 * https://leetcode.com/problems/lru-cache/
 * Design and implement a data structure for
 * Least Recently Used (LRU) cache.
 * It should support the following operations: get and set
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LRUCache {

    class Node {

        private int key;
        private Dnode value;
        private Node next;

        private Node(int key, Dnode value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    class SimpleList {

        private int n;
        private Node first;

        private Node[] nodes() {
            Node[] result = new Node[n];
            int i = 0;
            for (Node x = first; x != null; x = x.next) {
                result[i++] = x;
            }
            return result;
        }

        private boolean createIfNotExists(int key, Dnode value) {
            for (Node x = first; x != null; x = x.next) {
                if (key == x.key) {
                    x.value = value;
                    return false;
                }
            }
            first = new Node(key, value, first);
            n++;
            return true;
        }

        private boolean delete(int key) {
            Node prev = null;
            for (Node x = first; x != null; x = x.next) {
                if (key == x.key) {
                    if (prev == null) {
                        first = x.next;
                    } else {
                        prev.next = x.next;
                    }
                    x = null;
                    n--;
                    return true;
                }
                prev = x;
            }
            return false;
        }

        private Dnode get(int key) {
            for (Node x = first; x != null; x = x.next) {
                if (key == x.key) {
                    return x.value;
                }
            }
            return null;
        }
    }

    class SimpleHash {

        private static final int INIT_CAPACITY = 256;
        private int n;
        private int m;
        private SimpleList[] sl;

        SimpleHash() {
            this(INIT_CAPACITY);
        }

        SimpleHash(int m) {
            this.m = m;
            this.sl = new SimpleList[m];
            for (int i = 0; i < m; i++) {
                sl[i] = new SimpleList();
            }
        }

        private void resize(int chains) {
            SimpleHash temp = new SimpleHash(chains);
            for (int i = 0; i < m; i++) {
                Node[] nodeArr = sl[i].nodes();
                for (int j = 0; j < nodeArr.length; j++) {
                    temp.put(nodeArr[j].key, nodeArr[j].value);
                }
            }
            this.m = temp.m;
            this.n = temp.n;
            this.sl = temp.sl;
        }

        private int size() {
            return n;
        }

        private boolean contains(int key) {
            return get(key) != null;
        }

        private Dnode get(int key) {
            int i = indexFor(key);
            return sl[i].get(key);
        }

        private void delete(int key) {
            int i = indexFor(key);
            if (sl[i].delete(key)) {
                n--;
            }
            if (m > INIT_CAPACITY && n <= 2 * m) {
                resize(m / 2);
            }
        }

        private void put(int key, Dnode value) {
            if (value == null) {
                delete(key);
                return;
            }
            if (n >= 10 * m) {
                resize(2 * m);
            }
            int i = indexFor(key);
            if (sl[i].createIfNotExists(key, value)) {
                n++;
            }
        }

        private int indexFor(int x) {
            return (new Integer(x).hashCode()) & (m - 1);
        }
    }

    class Dnode {

        private int key;
        private int value;
        private Dnode prev;
        private Dnode next;

        Dnode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private SimpleHash sh;
    private Dnode first;
    private Dnode last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.sh = new SimpleHash();
    }

    private void remove(Dnode n) {
        if (n.prev == null && n.next == null) {
            first = last = null;
            return;
        }
        if (n.prev == null) {
            first = n.next;
            first.prev = null;
            return;
        }
        if (n.next == null) {
            last = n.prev;
            last.next = null;
            return;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void setHead(Dnode n) {
        n.next = first;
        n.prev = null;
        if (first != null) {
            first.prev = n;
        }
        first = n;
        if (last == null) {
            last = first;
        }
    }

    public int get(int key) {
        Dnode node = sh.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        setHead(node);
        return node.value;
    }

    public void set(int key, int value) {
        Dnode node = sh.get(key);
        if (node == null) {
            if (sh.size() == capacity) {
                sh.delete(last.key);
                remove(last);
            }
            node = new Dnode(key, value);
            sh.put(key, node);
            setHead(node);
        } else {
            node.value = value;
            remove(node);
            setHead(node);
        }
    }
}
