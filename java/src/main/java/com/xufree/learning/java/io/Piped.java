package com.xufree.learning.java.io;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Piped {
    public static void main(String[] args) throws IOException {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();
        out.connect(in);
        new Thread(new Print(in),"Print Thread").start();
        int receive;
        try {
            while ((receive = System.in.read())!=-1){
                out.write(receive);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static class Print implements Runnable{
        private PipedReader in;

        Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = in.read()) != -1){
                    System.out.print((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
