package com.xufree.learning.hadoop.book.chapter02.my;

import java.io.*;

public class CreateFile {
    public static void main(String[] args) {
        File dir = new File("D:/data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                File file = new File("D:/data/" + finalI + ".txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream fos = null;
                PrintWriter pw = null;
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < 1000000; j++) {
                        stringBuilder.append(Math.random()).append(System.getProperty("line.separator"));
                    }
                    fos = new FileOutputStream(file);
                    pw = new PrintWriter(fos);
                    pw.write(stringBuilder.toString().toCharArray());
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
    }
}
