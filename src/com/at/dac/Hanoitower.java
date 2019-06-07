package com.at.dac;

/**
 * 汉诺塔问题
 */
public class Hanoitower {
    private static int count = 0;

    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

    /**
     * 将编号为n的圆盘从a移动到c
     *
     * @param a
     * @param n
     * @param c
     */
    public static void move(char a, int n, char c) {
        count++;
        System.out.printf("第%d个盘从%c盘移动到%c盘\n", n, a, c);
    }

    public static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            // b做辅助塔
            move(a, n, c);
        } else {
            // c做辅助塔
            hanoi(n - 1, a, c, b);
            move(a, n, c);
            hanoi(n - 1, b, a, c);
        }
    }
}
