package com.anabol;

import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStream extends InputStream {
    private InputStream inputStream;
    private byte[] buffer = new byte[1024];
    private int index;
    private int readCount;

    public BufferedInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        if (index == readCount) {
            if ((readCount = inputStream.read(buffer)) == -1) {
                return -1;
            }
            index = 0;
        }
        return (int) buffer[index++];
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int count = 0;
        int value = 0;
        while (count < b.length - off && count < len && (value = read()) != -1) {
            b[off + count++] = (byte) value;
        }
        if (value == -1 && count == 0) {
            return -1;
        }
        return count;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}