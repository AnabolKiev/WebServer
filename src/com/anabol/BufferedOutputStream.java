package com.anabol;

import java.io.IOException;
import java.io.OutputStream;

// decorator
public class BufferedOutputStream extends OutputStream {
    private OutputStream outputStream;
    private byte[] buffer = new byte[5];
    private int index;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        if (index == buffer.length) {
            flush();
        }
        buffer[index++] = (byte) b;
    }

    // 8192
    // write byte[] 128
    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (len > 0) {
            byte[] array = new byte[len];
            System.arraycopy(b, off, array, 0, len);
            for (byte temp : array) {
                write(temp);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        outputStream.write(buffer, 0, index);
        index = 0;
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }
}