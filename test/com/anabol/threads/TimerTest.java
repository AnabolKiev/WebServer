package com.anabol.threads;

import java.util.ArrayList;
import java.util.List;

public class TimerTest {
    public static void main(String[] args) throws InterruptedException {
        MyTimer myTimer1 = new MyTimer("1", 10);
        MyTimer myTimer2 = new MyTimer("2", 5);
        MyTimer myTimer3 = new MyTimer("3", 15);

        List<MyTimer> myTimers = new ArrayList<>();
        myTimers.add(myTimer1);
        myTimers.add(myTimer2);
        myTimers.add(myTimer3);

        List<Thread> threads = new ArrayList<>();
        for (MyTimer myTimer : myTimers) {
            Thread thread = new Thread(myTimer);
            threads.add(thread);
            thread.start();
        }
    }
}
