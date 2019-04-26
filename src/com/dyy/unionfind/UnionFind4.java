package com.dyy.unionfind;

public class UnionFind4 implements UF{
    private int[] parent;
    private int[] rank;//sz[i]表示以i为根的树的节点个数

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        for(int i=0;i<size;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    private int find(int p){
        if(p<0 || p>=parent.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        while(parent[p] != p){
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p,int q){
        return find(p)==find(q);
    }
    @Override
    public void unionElements(int p,int q){
        int pParent = find(p);
        int qParent = find(q);

        if(pParent==qParent){
            return;
        }

        if(rank[pParent]<rank[qParent]){
            parent[pParent] = qParent;
        }else if(rank[qParent]<rank[pParent]){
            parent[qParent] = pParent;
        }else{
            parent[qParent] = pParent;
            rank[pParent] += 1;
        }
    }
}

