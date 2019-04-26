package com.dyy.binarysearchtree;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestBST {
    public static void main(String[] args){
        BST bst = new BST();
        int[] nums = {3,5,6,8,4,2};
        for(int i:nums){
            bst.add(i);
        }
        //bst.preOrderNR();
        bst.levelOrder();
        System.out.println("============");
        bst.remove(6);
        System.out.println("============");
        bst.levelOrder();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i:nums1){
            set.add(i);
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i:nums2){
            if(set.contains(i)){
                list.add(i);
                set.remove(i);
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
        for(int i:nums1){
            if(!map.containsKey(i)){
                map.put(i,1);
            }else{
                map.put(i,map.get(i)+1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i:nums2){
            if(map.containsKey(i)){
                list.add(i);
                map.put(i,map.get(i)-1);
                if(map.get(i)==0){
                    map.remove(i);
                }
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<res.length;i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
