package com.anabol.threads;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer implements Runnable {
    private String name;
    private int secondsLeft;
    private Timer timer;

    public MyTimer(String name, int secondsLeft) {
        this.name = name;
        this.secondsLeft = secondsLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    @Override
    public String toString() {
        return "MyFileWriter{" +
                "name='" + name + '\'' +
                ", secondsLeft=" + secondsLeft +
                '}';
    }

    @Override
    public void run() {
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, 1000);
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (secondsLeft > 0) {
                System.out.println(MyTimer.this);
                secondsLeft--;
            } else {
                timer.cancel();
                System.out.println("Thread " + name + " finished");
            }
        }
    }

}


