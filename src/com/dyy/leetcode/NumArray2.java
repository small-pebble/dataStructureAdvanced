package com.dyy.leetcode;

import com.dyy.segmenttree.Merger;
import com.dyy.segmenttree.SegmentTree;

public class NumArray2 {
    private SegmentTree<Integer> segTree;

    public NumArray2(int[] nums) {
        Integer[] data = new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            data[i] = nums[i];
        }
        segTree = new SegmentTree<>(data,new Merger<Integer>(){
            @Override
            public Integer merge(Integer a,Integer b){
                return a+b;
            }
        });
    }

    public void update(int i, int val) {
        if(segTree==null){
            throw new IllegalArgumentException("Error");
        }
        segTree.set(i,val);
    }

    public int sumRange(int i, int j) {
        if(segTree==null){
            throw new IllegalArgumentException("Error");
        }
        return segTree.query(i,j);
    }

    public static void main(String[] args){
        int[] nums = {2,6,4,8,9,5,34,23,67,47,76,53};
        NumArray2 obj = new NumArray2(nums);
        obj.update(6,66);
        int param_2 = obj.sumRange(3,6);
        System.out.println(param_2);
    }
}
