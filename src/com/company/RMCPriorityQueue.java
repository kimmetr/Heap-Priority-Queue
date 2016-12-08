package com.company;

public interface RMCPriorityQueue<E> {
    E peek();

    E poll();

    void add(E elt);

    int size();
}