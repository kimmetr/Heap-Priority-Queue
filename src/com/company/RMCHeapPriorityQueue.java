package com.company;

import java.util.Comparator;

public class RMCHeapPriorityQueue<E> implements RMCPriorityQueue<E> {

    private E[] data;
    private int size;
    private Comparator<E> comparator;

    public RMCHeapPriorityQueue(Comparator<E> comparator) {
        new RMCHeapPriorityQueue(comparator, 100);
    }

    public RMCHeapPriorityQueue(Comparator<E> comparator, int dataSize) {
        this.comparator = comparator;
        this.data = (E[]) new Object[dataSize];
        this.size = 0;
    }

    public E peek() {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[0];
    }

    public E poll() {
        if (data == null || size == 0) {
            System.out.println("Empty Queue...");
            return null;
        }
        E value = data[0];
        data[0] = data[size - 1];
        percolateDown(0);
        size--;
        data[size] = null;
        return value;
    }

    public void add(E elt) {
        if (data.length <= size) {
            growArray();
        }
        data[size++] = elt;
        percolateUp(size - 1);
    }

    public int size() {
        return size;
    }

    private int leftChildOf(int i) {
        return (i * 2 + 1);
    }

    private int rightChildOf(int i) {
        return (i * 2 + 2);
    }

    private int parentOf(int i) {
        if (i > 0) {
            return ((i - 1) / 2);
        }
        return 0;
    }

    private void swap(int a, int b) {
        E element = data[a];
        data[a] = data[b];
        data[b] = element;
    }

    private void percolateUp(int index) {
        if ((index > 0) && (comparator.compare(data[parentOf(index)], data[index]) < 0)) {
            swap(parentOf(index), index);
            percolateUp(parentOf(index));
        }
    }

    private void percolateDown(int index) {
        if ((leftChildOf(index) < size) && (rightChildOf(index) < size) && (data[leftChildOf(index)] != null) && (data[rightChildOf(index)] != null)) {
            if ((comparator.compare(data[index], data[leftChildOf(index)]) < 0) && (comparator.compare(data[leftChildOf(index)], data[rightChildOf(index)]) > 0)) {
                swap(index, leftChildOf(index));
                percolateDown(leftChildOf(index));
            } else if (comparator.compare(data[index], data[rightChildOf(index)]) < 0) {
                swap(index, rightChildOf(index));
                percolateDown(rightChildOf(index));
            }
        } else if ((leftChildOf(index) < size) && (data[leftChildOf(index)] != null) && (comparator.compare(data[index], data[leftChildOf(index)]) < 0)) {
            swap(index, leftChildOf(index));
            percolateDown(leftChildOf(index));
        }
    }

    private void growArray() {
        E[] array = (E[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            array[i] = data[i];
        }
        data = array;
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < size; i++) {
            string += data[i];
            if (i != (size - 1)) {
                string += ", ";
            }
        }
        return string;
    }

    public void sortAndPrint() {
        String string = "";
        for (int i = 0; i < data.length; i++) {
            string += this.poll();
            if (i < data.length - 1) {
                string += ", ";
            }
        }
        if (data.length <= 20) {
            System.out.println(string);
        }
    }

}

