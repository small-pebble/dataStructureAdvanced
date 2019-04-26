package com.dyy.maxheap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeetCode347 {
    private class Freq implements Comparable<Freq> {
        int e,freq;
        public Freq(int e,int freq){
            this.e = e;
            this.freq = freq;
        }
        @Override
        public int compareTo(Freq another){
            if(this.freq<another.freq){
                return 1;
            }else if(this.freq>another.freq){
                return -1;
            }else{
                return 0;
            }
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i:nums){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else{
                map.put(i,1);
            }
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for(int key:map.keySet()){
            if(pq.getSize()<k){
                pq.enqueue(new Freq(key,map.get(key)));
            }else{
                if(map.get(key)>pq.getFront().freq){
                    pq.dequeue();
                    pq.enqueue(new Freq(key,map.get(key)));
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            list.add(pq.dequeue().e);
        }
        return list;
    }
}
