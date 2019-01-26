package com.anabol;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class FileManager {
    final static int BUFFER_SIZE = 100;

    // public static int countFiles(String path) - принимает путь к папке,
    // возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        int count = 0;
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File innerFile : files) {
                    count += countFiles(innerFile.getPath());
                }
            }
        } else if (file.isFile()) {
            count++;
        }
        return count;
    }

    // public static int countDirs(String path) - принимает путь к папке,
    // возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        int count = 0;
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File innerFile : files) {
                    if (innerFile.isDirectory()) {
                        count++;
                        count += countDirs(innerFile.getPath());
                    }
                }
            }
        }
        return count;
    }

    // Метод по копированию папок и файлов
    // Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование
    public static void copy(String from, String to) throws IOException {
        copyOrMove(from, to, false);
    }

    // Метод по перемещению папок и файлов
    // Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование
    public static void move(String from, String to) throws IOException {
        copyOrMove(from, to, true);
    }

    private static void copyOrMove(String from, String to, boolean isRemove) throws IOException {
        File file = new File(from);
        if (file.isDirectory()) {
            String newDirPath = to + File.separator + file.getName();
            new File(newDirPath).mkdirs();
            for (File innerFile : file.listFiles()) {
                if (innerFile != null) {
                    copyOrMove(innerFile.getPath(), newDirPath, isRemove);
                }
            }
        } else if (file.isFile()) {
            copyFile(file, to);
        }
        if (isRemove) {
            file.delete();
        }
    }

    private static void copyFile(File file, String to) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(new File(to, file.getName()));
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}