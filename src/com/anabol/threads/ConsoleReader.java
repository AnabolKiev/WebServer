package com.anabol.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class ConsoleReader {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        Timer timer = new Timer();
        timer.schedule(new FileWriterTask(list), 0, 15_000);

        try (Scanner scanner = new Scanner(System.in)) {
            String message = null;
            while (!("exit".equalsIgnoreCase(message))) {
                message = scanner.nextLine();
                list.add(message);
            }
        } finally {
            timer.cancel();
        }
    }
}
