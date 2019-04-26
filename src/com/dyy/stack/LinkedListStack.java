package com.dyy.stack;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<E>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.getSize()==0;
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.deleteFirst();
    }

    @Override
    public E peek(){
        return list.getFrist();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args){
        LinkedListStack<Integer> linkedList = new LinkedListStack<Integer>();
        for(int i=0;i<5;i++){
            linkedList.push(i);
            System.out.println(linkedList);
        }
        linkedList.push(66);
        System.out.println(linkedList);
        linkedList.pop();
        System.out.println(linkedList);
    }
}
