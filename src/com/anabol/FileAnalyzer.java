package com.anabol;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileAnalyzer {

//        1) Кол-во вхождений искомого слова в файле
//        2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!"), каждое преждложение с новой строки.
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Wrong arguments! Usage: java FileAnalyzer path word");
        } else {
            final String SENTENCE_REGEXP = "(?<=\\.)|(?<=!)|(?<=\\?)";
            String path = args[0];
            String word = args[1];
            String fileContent = readFromFile(path);
            int fromIndex = -1;
            int count = 0;
            while ((fromIndex = fileContent.indexOf(word, fromIndex + 1)) != -1) {
                count++;
            }
            System.out.println("Count: " + count);
            String[] sentences = fileContent.split(SENTENCE_REGEXP);
            for (String str : sentences
                    ) {
                if (str.contains(word)) {
                    System.out.println(str);
                }
            }
        }
    }

    private static String readFromFile(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        StringBuilder stringBuilder = new StringBuilder();
        byte[] buffer = new byte[100];
        int count;
        while ((count = inputStream.read(buffer)) != -1) {
            String value = new String(buffer, 0, count);
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }
}
