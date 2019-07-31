package com.xufree.learning.algorithm.zjtd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间戳 用户 操作(0登陆1登出)
 * 1 a 0
 * 2 a 1
 * 6 a 0
 * 8 a 1
 * 9 a 0
 * 13 a 1
 * 14 a 0
 * 22 a 1
 * 2 b 0
 * 4 b 1
 * 5 b 0
 * 7 b 1
 * 8 b 0
 * 12 b 1
 * 14 b 0
 * 16 b 1
 * 17 b 0
 * 18 b 1
 * 20 b 0
 * 22 b 1
 * 3 c 0
 * 4 c 1
 * 5 c 0
 * 6 c 1
 * 7 c 0
 * 9 c 1
 * 11 c 0
 * 14 c 1
 * 15 c 0
 * 19 c 1
 * 3 d 0
 * 5 d 1
 * 6 d 0
 * 9 d 1
 * 10 d 0
 * 12 d 1
 * 13 d 0
 * 15 d 1
 * 16 d 0
 * 19 d 1
 * 21 d 0
 * 22 d 1
 * 求一段时间 最大同时在线用户量
 *
 * @author zhangmingxu ON 11:00 2019-07-31
 **/
public class LoginData {
    private static final String PATH = "/Users/zhangmingxu/Desktop/login.data";

    public static void main(String[] args) {
        List<String> data = getDataFromFile();
        int start = 0;
        int end = 20;
        int count = process(data, start, end);
        System.out.println(count);
    }

    private static int process(List<String> data, int start, int end) {
        int length = end - start;
        int[][] array = new int[length][2];
        int cur = start;
        for (int[] ints : array) {
            ints[0] = cur++;
        }
        for (String s : data) {
            String[] ss = s.split(" ");
            int time = Integer.parseInt(ss[0]);
            int login = Integer.parseInt(ss[2]);
            int i = Integer.max(start, time);
            for (int k = i; k < end; k++) {
                if (login == 0) {
                    array[k - start][1]++;
                } else {
                    array[k - start][1]--;
                }
            }
        }
        int max = 0;
        for (int[] ints : array) {
            max = Integer.max(ints[1], max);
        }
        return max;
    }

    private static List<String> getDataFromFile() {
        List<String> data = new LinkedList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(PATH))) {
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                data.add(line);
            }
        } catch (Exception ignored) {

        }
        return data;
    }
}
