package com.dyy.linkedlist;

public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node node){
            this.e = e;
            this.next = node;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        public Node(E[] arr){
            if(arr==null || arr.length==0){
                throw new IllegalArgumentException("arr cannot be empty");
            }

            this.e = arr[0];
            Node cur = this;
            for(int i=1;i<arr.length;i++){
                cur.next = new Node(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    //获取链表中元素的个数
    public int getSize(){
        return size;
    }
    //获得某元素的下标
    private int getIndex(E e){
        Node cur = dummyHead.next;
        int index = 0;
        while(cur!=null){
            if(cur.e==e){
                return index;
            }
            cur = cur.next;
            index++;
        }
        return -1;
    }

    //在末尾添加节点
    public void addLast(E e){
        add(size,e);
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //在链表头添加元素
    public void addFirst(E e){
       add(0,e);
    }

    //在链表中间添加元素
    public void add(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size++;
    }

    //获得index处的节点
    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        return cur.e;
    }

    //获得第一个元素
    public E getFrist(){
        return get(0);
    }
    //获得最后一个元素
    public E getLast(){
        return get(size-1);
    }

    //修改index处的元素
    public E set(int index,E e){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        Node indexNode = cur;
        cur.e = e;
        return indexNode.e;
    }

    //查找是否存在e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e == e){
                return true;
            }else{
                cur = cur.next;
            }
        }
        return false;
    }

    //删除元素
    public E delete(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Delete failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }
    public void deleteElements(E e){
        int index = getIndex(e);
        delete(index);
    }

    //删除第一个元素
    public E deleteFirst(){
        return delete(0);
    }

    //删除最后一个
    public E deleteLast(){
        return delete(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}

