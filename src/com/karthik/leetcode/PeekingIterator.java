/*
 * https://leetcode.com/problems/peeking-iterator/#/description
 *
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation
 * -- it essentially peek() at the element that will be returned by the next call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 *
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
package com.karthik.leetcode;

import java.util.Iterator;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> itr;
    private Integer head = null;

    public PeekingIterator(Iterator<Integer> itr) {
        this.itr = itr;
    }

    public Integer peek() {
        if (head == null) {
            head = itr.next();
        }
        return head;
    }

    @Override
    public Integer next() {
        if (head == null) {
            return itr.next();
        }
        int tmp = head;
        head = null;
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return head != null || itr.hasNext();
    }
}

class PeekingIterator2<T extends Object> implements Iterator<T> {

    private T[] q;
    private int first;
    private int last;
    private int n;

    public PeekingIterator2(Iterator<T> itr) {
        first = 0;
        last = 0;
        q = (T[]) new Object[16];
        while (itr.hasNext()) {
            enqueue(itr.next());
        }
    }

    public T peek() {
        return q[first];
    }

    @Override
    public T next() {
        return dequeue();
    }

    @Override
    public boolean hasNext() {
        return n > 0;
    }

    private void resize(int size) {
        T[] tmp = (T[]) new Object[size];
        for (int i = 0; i < n; i++) {
            tmp[i] = q[(first + i) % size];
        }
        q = tmp;
        first = 0;
        last = n;
    }

    private void enqueue(T val) {
        if (q.length == n) {
            resize(2 * q.length);
        }
        n++;
        q[last] = val;
        last = (last + 1) % q.length;
    }

    private T dequeue() {
        if (n == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        T val = q[first];
        q[first] = null;
        first = (first + 1) % q.length;
        n--;
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return val;
    }
}
