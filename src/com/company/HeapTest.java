package com.company;

import java.util.Comparator;

public class HeapTest {
    public static void main(String[] args) {
        int size = 15;
        Comparator<Integer> comp = new IntComparator();
        RMCHeapPriorityQueue<Integer> myQueue = new RMCHeapPriorityQueue<>(comp, size);

        for (int i = 0; i < size; i++) {
            myQueue.add((int) (Math.random() * 1000));
        }

        if (size <= 20) {
            System.out.println(myQueue.toString());
        }

        myQueue.sortAndPrint();
    }
}