package com.dyy.trie;

import java.util.TreeMap;

public class Trie {

    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isword){
            this.isWord = isword;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }
    //获得trie中的单词数量
    public int getSize(){
        return size;
    }
    //向trie中添加单词
    public void add(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }
    //在trie中查询单词
    public boolean contains(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    //在trie中查找是否有以prefix为前缀的单词
    public boolean isPrefix(String prefix){
        Node cur = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c)==null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
