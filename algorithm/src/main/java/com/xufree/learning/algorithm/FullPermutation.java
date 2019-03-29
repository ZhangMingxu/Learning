package com.xufree.learning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 */
public class FullPermutation {
    public static void main(String[] args) {
        String[] strings = new String[]{"哎呀我去", "A", "3", "askldjakldjlk"};
        List<String[]> result = f(strings, strings.length - 1);
        for (String[] s : result) {
            for (String s1 : s) {
                System.out.print(s1 + " ");
            }
            System.out.print("\n");
        }

    }

    private static List<String[]> f(String[] strings, int count) {
        List<String[]> result = new ArrayList<>();
        if (count == 0) {
            String[] s1 = {strings[0]};
            result.add(s1);
        } else {
            int index = strings.length - count;
            List<String[]> ss1 = f(strings, count - 1);
            for (String[] s : ss1) {
                String next = strings[index];
                for (int i = 0; i < s.length + 1; i++) {
                    String[] temp = new String[s.length + 1];
                    System.arraycopy(s, 0, temp, 0, i);
                    temp[i] = next;
                    System.arraycopy(s, i, temp, i + 1, s.length - i);
                    result.add(temp);
                }
            }
        }
        return result;
    }
}
