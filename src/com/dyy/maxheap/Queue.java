package com.dyy.maxheap;

public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    E getFront();
    void enqueue(E e);
    E dequeue();
}
