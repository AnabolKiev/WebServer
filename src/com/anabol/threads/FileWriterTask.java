package com.anabol.threads;

import java.io.*;
import java.util.List;
import java.util.TimerTask;

public class FileWriterTask extends TimerTask {
    private List<String> list;

    public FileWriterTask(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("test.log", true)) {  // true - append to file
        }) {
            System.out.println("FileWriterTask started");
            for (String s : list) {
                fileWriter.write(s);
                fileWriter.newLine();
                fileWriter.flush();
            }
            System.out.println(list.size() + " lines were written");
            list.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




