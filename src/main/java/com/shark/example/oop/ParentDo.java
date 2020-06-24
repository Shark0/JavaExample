package com.shark.example.oop;

public abstract class ParentDo {

    public void start() {
        long startTime = System.currentTimeMillis();
        work();
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("class: " + this.getClass().getSimpleName() + ", executeTime: " + executeTime);
    }

    public abstract void work();
}
