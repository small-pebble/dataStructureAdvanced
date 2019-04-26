package com.dyy.maxheap;

public class Array<E> {

    private E[] data;
    private int size;

    //构造函数，传入数组的容量
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }
    //构造函数，传入一个数组
    public Array(E[] arr){
        if(arr.length==0){
            data = (E[])new Object[10];
        }
        for(int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
    }

    //无参构造函数
    public Array(){
        this(10);
    }

    //获取数组中元素的个数
    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //在所有元素后添加一个元素
    public void addLast(E e){
       add(size,e);
    }

    //在所有元素前添加一个元素
    public void addFirst(E e){
        add(0,e);
    }

    //在index处插入一个元素e
    public void add(int index,E e){

        if(index<0 || index>size){
            throw new IllegalArgumentException("AddLast failed.Index is illegal.");
        }

        if(size==data.length){
            resize(2*data.length);
        }

        for(int i=size-1;i>=index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //交换元素
    public void swap(int i,int j){
        if(i<0 || i>=size || j<0 || j>=size){
            throw new IllegalArgumentException("Index is illegal.");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i=0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d,capacity = %d\n",size,data.length));
        res.append("[");
        for(int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    //获得某索引处的元素
    E get(int index){
        if(index<0 || index>size-1){
            throw new IllegalArgumentException("Get failed.Index is illegal");
        }
        return data[index];
    }

    //修改数组中某位置的元素
    void set(int index,E e){
        if(index<0 || index>size-1){
            throw new IllegalArgumentException("Set failed.Index is illegal");
        }
        data[index] = e;
    }

    //数组中是否包含某个元素
    public boolean contains(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    //查找某元素在数组中的位置，没有返回-1
    public int find(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    //删除某索引处的元素,并返回删除的元素
    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Remove failed.Index is illegal");
        }
        E ret = data[index];
        for(int i=index;i<size-1;i++){
            data[i] = data[i+1];
        }
        size--;
        data[size] = null;
        if(size==data.length/4 && data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }

    //移除第一个元素
    public E removeFirst(){
        return remove(0);
    }
    //移除最后一个元素
    public E removeLast(){
        return remove(size-1);
    }

    //删除元素
    public void removeElement(E e){
        int index = find(e);
        if(index!=-1){
            remove(index);
        }
    }
}
