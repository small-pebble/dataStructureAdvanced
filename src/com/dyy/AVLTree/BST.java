package com.dyy.AVLTree;

public class BST<K extends Comparable<K>,V>{

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
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
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }else{
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right==null){
               Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除的节点左右子树都存在
            Node successor = minimum(node);
            successor.left = node.left;
            successor.right = removeMin(node.right);

            node.left = node.right = null;
            return successor;
        }
    }
}
