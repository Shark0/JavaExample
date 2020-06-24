package com.shark.example.oop;

public class ChildDo extends ParentDo {
    @Override
    public void work() {
        System.out.println("work! work!");
    }


    public static void main(String[] argv) {
        ChildDo child = new ChildDo();
        child.start();
    }
}
