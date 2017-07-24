package com.karthik.leetcode;

public class AVLTree {

    class Node {

        private Node left, right;
        private int key, size, height;

        Node(int ky, int sz, int ht) {
            key = ky;
            size = sz;
            height = ht;
        }
    }
    private Node root;

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public int height(Node x) {
        if (x == null) {
            return 0;
        }
        return x.height;
    }

    public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node x, int key) {
        if (x == null) {
            return null;
        }
        if (x.key > key) {
            return search(x.left, key);
        } else if (x.key < key) {
            return search(x.right, key);
        } else {
            return x;
        }
    }

    private int balanceFactor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

    private Node rotateLeft(Node x) {
        if (x == null) {
            return null;
        }
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private Node rotateRight(Node x) {
        if (x == null) {
            return null;
        }
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node x, int key) {
        if (x == null) {
            return new Node(key, 1, 0);
        }
        if (x.key > key) {
            x.left = insert(x.left, key);
        } else if (x.key < key) {
            x.right = insert(x.right, key);
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void delete(int key) {
        if (root == null) {
            return;
        }
        root = delete(root, key);
    }

    private Node delete(Node x, int key) {
        if (x.key > key) {
            x.left = delete(x.left, key);
        } else if (x.key < key) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            } else if (x.right == null) {
                return x.left;
            } else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void deleteMax() {
        if (root == null) {
            return;
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public Node min() {
        if (root == null) {
            return null;
        }
        return min(root);
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }
}
