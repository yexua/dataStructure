package com.at.kmp;

import java.util.Arrays;

public class KMPAlgorithm {

    public static void main(String[] args) {
        String s = "hellwordhellhelloworld";
        String t = "helloworld";
        int[] next = next("abaabcac");
        System.out.println(Arrays.toString(next));
        //System.out.println(indexOfKMP(s, t, next));
    }

    public static int indexOfKMP(String s, String t, int[] next) {
        int i = 0, j = 0;
        while (i <= s.length() && j <= t.length()) {
            if (j == 0 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else  {
                j = next[i - 1];
            }
        }
        if (j > t.length()) {
            return i - t.length();
        }
        return -1;
    }

    /**
     * 计算模式串的next函数值并存入数组next
     *
     * @param dest
     * @return
     */
//    public static int[] next(String dest) {
//        int[] next = new int[dest.length()];
//        next[0] = 0;
//        int i = 1, j = 0;
//        while (i < dest.length()) {
//            if (j == 0 || dest.charAt(i) == dest.charAt(j)) {
//                j++;
//            } else {
//                j = next[j - 1];
//            }
//            next[i] = j;
//        }
//        return next;
//    }
    public static int[] next(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
