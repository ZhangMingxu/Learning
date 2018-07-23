package com.xufree.learning.java.algorithm;

import java.util.ArrayList;
import java.util.List;

public class FullPermutation {
    public static void main(String[] args) {
        String[] strings = new String[]{"1", "2","3"};
        List<String> result = f(strings, strings.length - 1);
        for (String s : result) {
            System.out.println(s);
        }

    }

    private static List<String> f(String[] strings, int count) {
        List<String> result = new ArrayList<>();
        if (count == 0) {
            result.add(strings[0]);
            return result;
        } else {
            int index = strings.length - count;
            List<String> ss1 = f(strings, count - 1);
            for (String s : ss1) {
                String next = strings[index];
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length + 1; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        sb.append(chars[j]);
                    }
                    sb.append(next);
                    for (int j = i; j < chars.length; j++) {
                        sb.append(chars[j]);
                    }
                    result.add(sb.toString());
                }
            }
            return result;
        }
    }
}
