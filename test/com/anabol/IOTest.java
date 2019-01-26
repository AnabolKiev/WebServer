package com.anabol;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;

public class IOTest {

    @Test
    public void writeAndReadBufferedStreamTest() throws IOException {
        String content = "Hello world";
        String path = "c:/DevTools/Luxoft/test.log";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        try {
            for (byte b : content.getBytes()) {
                bufferedOutputStream.write(b);
            }
        } finally {
            bufferedOutputStream.close();
        }
        int value;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        try {
            while ((value = bufferedInputStream.read()) != -1) {
                stringBuilder.append((char) value);
            }
        } finally {
            bufferedInputStream.close();
        }
        assertEquals(content, stringBuilder.toString());
    }

    @Test
    public void writeAndReadByArrayBufferedStreamTest() throws IOException {
        String content = "Hello world";
        String path = "c:/DevTools/Luxoft/test.log";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        try {
            bufferedOutputStream.write(content.getBytes());
        } finally {
            bufferedOutputStream.close();
        }
        byte[] b = new byte[5];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        try {
            bufferedInputStream.read(b);
        } finally {
            bufferedInputStream.close();
        }
        assertArrayEquals("Hello".getBytes(), b);
    }

    @Test
    public void writeAndReadByArrayOffsetBufferedStreamTest() throws IOException {
        String content = "Hello world";
        String path = "c:/DevTools/Luxoft/test.log";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        try {
            bufferedOutputStream.write(content.getBytes(), 6, 5);
        } finally {
            bufferedOutputStream.close();
        }
        byte[] b = new byte[4];
        b[0] = (byte) 'X';
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
        try {
            bufferedInputStream.read(b, 1, 3);
        } finally {
            bufferedInputStream.close();
        }
        assertArrayEquals("Xwor".getBytes(), b);
    }
}