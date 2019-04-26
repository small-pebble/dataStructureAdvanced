package com.dyy.stack;

public class ArrayStack<E> implements Stack<E> {

    private Array<E> stack;

    public ArrayStack(int capacity){
        stack = new Array<E>(capacity);
    }

    public ArrayStack(){
        stack = new Array<E>();
    }

    @Override
    public int getSize(){
        return stack.getSize();
    }

    public int getCapacity(){
        return stack.getCapacity();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public void push(E e){
        stack.addLast(e);
    }

    @Override
    public E pop(){
        return stack.removeLast();
    }

    @Override
    public E peek(){
        return stack.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for(int i=0;i<stack.getSize();i++){
            res.append(stack.get(i));
            if(i!=stack.getSize()-1){
                res.append(",");
            }
        }
        res.append("] top");
        return res.toString();
    }

}
