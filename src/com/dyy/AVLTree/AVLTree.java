package com.dyy.AVLTree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V>{

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }
    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //判断二叉树是否是一颗二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for(int i=1;i<keys.size();i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0){
                return false;
            }
        }
        return true;
    }
    private void inOrder(Node node,ArrayList<K> keys){
        if(node==null){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    //判断二叉树是否为平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){
        if(node==null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //获得节点的高度
    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    //计算节点的平衡因子
    private int getBalanceFactor(Node node){
        if(node==null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    //右旋转
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //旋转
        x.right = y;
        y.left = T3;
        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;

        return x;

    }
    //左旋转
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T3 = x.left;
        //左旋转
        x.left = y;
        y.right = T3;
        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right));
        x.height = Math.max(getHeight(x.left),getHeight(x.right));
        return x;
    }

    public void add(K key,V value){
        root = add(root,key,value);
    }
    public Node add(Node node, K key, V value){
        if(node==null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }else{
            node.value = value;
        }
        //更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算节点的平衡因子
        int balanceFactor = getBalanceFactor(node);
        //平衡维护
        //LL
        if(balanceFactor>1 && getBalanceFactor(node.left)>=0){
            return rightRotate(node);
        }

        //RR
        if(balanceFactor<-1 && getBalanceFactor(node.right)<=0){
            return leftRotate(node);
        }
        //LR
        if(balanceFactor>1 && getBalanceFactor(node.left)<0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if(balanceFactor<-1 && getBalanceFactor(node.right)>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }else if(key.compareTo(node.key)>0){
            return getNode(node.right,key);
        }else{
            return node;
        }
    }
    public boolean contains(K key){
        return getNode(root,key)!=null;
    }

    public V get(K key){
        Node retNode = getNode(root,key);
        return retNode==null?null:retNode.value;
    }


    public void set(K key,V newValue){
        Node node = getNode(root,key);
        if(node==null){
            throw new IllegalArgumentException(key + "dosen't exist");
        }else{
            node.value = newValue;
        }
    }

    //返回以node为根的最小节点
    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }
    //删除以node为根的二分搜索树的最小节点
    //并返回删除最小节点后的根
    private Node removeMin(Node node){
        if(node.left==null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key){
        Node node = getNode(root,key);
        if(node!=null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }
    //删除以node为根的二分搜索树中，键为key的节点，并返回删除节点后的新的根
    private Node remove(Node node, K key){
        if(node==null){
            return null;
        }
        Node retNode;
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            retNode  = node;
        }else{
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            else if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            else{
                //待删除的节点左右子树都存在
                Node successor = minimum(node);
                successor.left = node.left;
                successor.right = remove(node.right, successor.key);

                node.left = node.right = null;
                retNode = successor;
            }
        }
        if(retNode==null){
            return null;
        }
        int balanceFactor = getBalanceFactor(retNode);
        //平衡维护
        //LL
        if(balanceFactor>1 && getBalanceFactor(retNode.left)>=0){
            return rightRotate(retNode);
        }
        //RR
        if(balanceFactor<-1 && getBalanceFactor(retNode.right)<=0){
            return leftRotate(retNode);
        }
        //LR
        if(balanceFactor>1 && getBalanceFactor(retNode.left)<0){
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if(balanceFactor<-1 && getBalanceFactor(retNode.right)>0){
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    public static void main(String[] args) {
        String word = "jhasfuafbcxbzjhkjsadfjkhgd";
        AVLTree<Character,Integer> avl = new AVLTree<>();
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(avl.get(c)!=null){
                avl.set(c,avl.get(c)+1);
            }else{
                avl.add(c,1);
            }
        }

        avl.remove('j');
        avl.remove('a');


        System.out.println(avl.isBST());
        System.out.println(avl.isBalanced());
    }
}


