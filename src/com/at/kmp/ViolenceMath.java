package com.at.kmp;


public class ViolenceMath {

    public static void main(String[] args) {
        String s1 = "hellworldhello worldhelloworld";
        String s2 = "helloworld";
        System.out.println(violenceMath(s1, s2));
    }

    /**
     * 暴力匹配算法
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int violenceMath(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        final int l1 = c1.length;
        final int l2 = c2.length;
        int i = 0, j = 0;
        while (i < l1 && j < l2) {
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == l2) {
            return i - j;
        } else {
            return -1;
        }
    }
}
