package com.dyy.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node right,left;

        public Node(E e){
            this.e = e;
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

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(E e){
        root = add(root,e);
    }

    //向以node为根的二分搜索树中插入元素e
    //返回插入新节点后的二叉树的根
    public Node add(Node node,E e){
        if(node==null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }

    //查看二分搜索树是否包含某元素
    public boolean contains(E e){
        return contains(root,e);
    }

    //查看以node为根的树是否含有元素e
    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }
        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    //以node为根的前序遍历
    private void preOrder(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //前序遍历的非递归实现
    public void preOrderNR(){
        Stack<Node> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }

        }
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left!=null){
                q.add(cur.left);
            }
            if(cur.right!=null){
                q.add(cur.right);
            }
        }
    }

    //删除最大值
    public E deleteMax(){
        Node pre = root;
        if(pre.right==null && pre.left==null){

            return root.e;

        }
        Node cur = pre.right;
        while(cur.right!=null){
            pre = cur;
            cur = cur.right;
        }
        Node retNode = cur;
        if(cur.left!=null){
            pre.right = cur.left;
        }else{
            pre.right = null;
        }
        return retNode.e;
    }

    //返回最小值的节点
    private Node minimun( Node node){
        if(node.left==null){
            return node;
        }
        return minimun(node.left);
    }
    //返回最小的值
    public E minimun(){
        if(root==null){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimun(root).e;
    }

    //返回最大值节点
    private Node maximun(Node node){
        if(node.right==null){
            return node;
        }
        return maximun(node.right);
    }
    //返回最大值
    public E maximun(){
        if(root==null){
            throw new IllegalArgumentException("BST is empty");
        }
        return maximun(root.right).e;
    }

    //从二分搜索树删掉最小值，并返回
    public E removeMin(){
        E ret = minimun();
        root = removeMin(root);
        return ret;
    }
    //删除二分搜索树的最小节点并返回
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

    //从二分搜索树中删除最大值，并返回
    public E removeMax(){
        E ret = maximun();
        root = removeMax(root);
        return ret;
    }
    //从以node为根的树中，删除最大节点并返回
    private Node removeMax(Node node){
        if(node.right==null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //删除节点
    public void remove(E e){
        root = remove(root,e);
    }
    private Node remove(Node node,E e){
        if(node==null){
            return null;
        //传入的值小于该节点的值
        }else if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else{  //e.compareTo(node.e)==0
            //待删除节点的左子树为空
            if(node.left==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            else if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else{
                Node succeeor = minimun(node.right);
                succeeor.right = removeMin(node.right);
                succeeor.left = node.left;
                node.left = node.right = null;
                return succeeor;
            }
        }
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node==null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }
}
