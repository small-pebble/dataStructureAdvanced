package com.dyy.maxheap;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }
    public MaxHeap(){
        data = new Array<>();
    }
    //将传入的数组构造成堆
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i=parent(arr.length-1);i>=0;i--){
            siftDown(i);
        }
    }
    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //父节点的索引
    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("index-0 dosen't have parent");
        }
        return (index-1)/2;
    }
    //左孩子的索引
    private int leftChild(int index){
        return index*2+1;
    }
    //右孩子的索引
    private int rightChild(int index){
        return index*2+2;
    }

    //向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }
    private void siftUp(int k){
        while(k>0 && data.get(k).compareTo(data.get(parent(k)))>0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }
    //查看最大元素
    public E findMax(){
        if(data.getSize()==0){
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }
    //取出最大元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }
    public void siftDown(int k){
        while(leftChild(k)<data.getSize()){
            int j = leftChild(k);
            if(j+1<data.getSize() && data.get(j).compareTo(data.get(j+1))<0){
                j++;
            }
            if(data.get(k).compareTo(data.get(j))>=0){
                break;
            }
            data.swap(k,j);
            k = j;
        }
    }

    //取出最大元素，并替换成e
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
