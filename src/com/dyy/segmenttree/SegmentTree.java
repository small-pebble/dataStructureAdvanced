package com.dyy.segmenttree;

public class SegmentTree<E> {
    private E[] tree;
    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }
    private void buildSegmentTree(int treeIndex,int l,int r){
        if(l==r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l+(r-l)/2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    public int leftChild(int index){
        return index*2+1;
    }

    public int rightChild(int index){
        return index*2+2;
    }
    //查询[l,r]之间的值
    public E query(int l,int r){
        if(l<0 || l>data.length-1 || r<0 || r>data.length-1 || l>r){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0,0,data.length-1,l,r);
    }
    //查询以treeIndex为根的线段树中[l,r]范围里，[queryL,queryR]之间的值
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if(l==queryL && r==queryR){
            return tree[treeIndex];
        }
        int mid = l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if(queryR<=mid){
            return query(leftChild,l,mid,queryL,queryR);
        }else if(queryL>mid){
            return query(rightChild,mid+1,r,queryL,queryR);
        }

        return merger.merge(query(leftChild,l,mid,queryL,mid),query(rightChild,mid+1,r,mid+1,queryR));
    }

    public void set(int index,E e){
        if(index<0 || index>=data.length-1){
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }
    //在以treeIndex为根的线段树中更新index的值为e
    public void set(int treeIndex,int l,int r,int index,E e){
        if(r==l){
            tree[treeIndex] = e;
            return;
        }
        int mid = l+(r-l)/2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        if(index>mid){
            set(rightChild,mid+1,r,index,e);
        }else{
            set(leftChild,l,mid,index,e);
        }
        tree[treeIndex] = merger.merge(tree[leftChild],tree[rightChild]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("SegmentTree [");
        for(int i=0;i<tree.length;i++){
            if(tree[i]!=null){
                res.append(tree[i].toString());
            }else{
                res.append("null");
            }
            if(i!=tree.length-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
