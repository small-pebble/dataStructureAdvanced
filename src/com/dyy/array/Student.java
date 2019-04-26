package com.dyy.array;

public class Student {

    private String name;
    private int score;

    public Student(String name,int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Student(name:%s,score:%d)",name,score);
    }

    public static void main(String[] args){
        Array<Student> stus = new Array(10);
        stus.addLast(new Student("dd",99));
        stus.addLast(new Student("yy",89));
        stus.addLast(new Student("aa",100));
        System.out.println(stus);
    }
}
