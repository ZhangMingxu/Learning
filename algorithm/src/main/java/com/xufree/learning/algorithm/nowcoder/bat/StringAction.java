package com.xufree.learning.algorithm.nowcoder.bat;

/**
 * @author zhangmingxu ON 16:36 2019-07-24
 **/
public class StringAction {
    public static void main(String[] args) {
        System.out.println(contains("asdasdasd", "asd"));
    }

    /**
     * 暴力模式判断是否存在
     *
     * @param source 包含
     * @param target 被包含
     * @return 是否包含
     */
    private static boolean contains(String source, String target) {
        if (source == null || target == null) {
            throw new NullPointerException();
        }
        char[] sourceChars = source.toCharArray();
        char[] targetChars = target.toCharArray();
        if (sourceChars.length < targetChars.length) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < sourceChars.length) {
            if (sourceChars[i] == targetChars[j]) {
                i++;
                j++;
                if (j == targetChars.length) {
                    return true;
                }
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        return false;
    }

    /**
     * KMP算法判断是否存在
     *
     * @param source 包含
     * @param target 被包含
     * @return 是否包含
     */
    private static boolean containsWithKMP(String source, String target) {
        return false;
    }
}
