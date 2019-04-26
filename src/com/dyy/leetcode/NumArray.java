package com.dyy.leetcode;

import com.dyy.segmenttree.Merger;
import com.dyy.segmenttree.SegmentTree;

public class NumArray {
    private SegmentTree<Integer> segTree;
    public NumArray(int[] nums) {

        if(nums.length>0){
            Integer[] arr = new Integer[nums.length];
            for(int i=0;i<nums.length;i++){
                arr[i] = nums[i];
            }
            segTree = new SegmentTree<>(arr, new Merger<Integer>() {
                @Override
                public Integer merge(Integer a, Integer b) {
                    return a+b;
                }
            });
        }

    }

    public int sumRange(int i, int j) {
        if(segTree==null){
            throw new IllegalArgumentException("Nums is empty.");
        }
        return segTree.query(i,j);
    }
}
