package com.dyy.array;

public class TestArray {
    public static void main(String[] args) {
        Array<Integer> arr = new Array();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1, 99);
        System.out.println(arr);
        arr.remove(5);
        System.out.println(arr);
    }
}
