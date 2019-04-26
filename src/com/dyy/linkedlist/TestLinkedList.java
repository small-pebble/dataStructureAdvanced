package com.dyy.linkedlist;

public class TestLinkedList {
    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for(int i=0;i<5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,66);
        System.out.println(linkedList);
        linkedList.delete(2);
        System.out.println(linkedList);
    }
}
