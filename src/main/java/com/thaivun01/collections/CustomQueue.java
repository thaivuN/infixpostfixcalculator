
package com.thaivun01.collections;

import java.util.ArrayList;

/**
 *Custom built Queue
 * @author Thai-Vu Nguyen
 */
public class CustomQueue<T> {

    private ArrayList<T> list;

    /**
     * Default Constructor.
     */
    public CustomQueue() {
        this.list = new ArrayList<>();
    }

    /**
     * Add an element at the end of the queue
     * @param t T
     */
    public void add(T t) {
        list.add(t);
    }

    /**
     * Removes the element at the beginning of the queue
     * @return Element at the front of the queue
     * @throws ArrayIndexOutOfBoundsException is thrown if the method is called when the queue is empty 
     */
    public T remove() {
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.remove(0);
    }

    /**
     * Check if the Queue is empty.
     * @return boolean 
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the size of the queue
     * @return count of elements inside the queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the element at the beginning of the queue. Does not removes it from the queue.
     * @return Element at the front of the queue
     * @throws ArrayIndexOutOfBoundsException is thrown if the method is called when the queue is empty
     */
    public T peek() {
        if (list.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.get(0);
    }
    
}
