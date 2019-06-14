package com.at;

public class EightQueens {
    public static final int max = 4;
    int[] array = new int[max];

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.check(0);
    }

    /**
     * @param n 放置第n个皇后
     */
    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            array[n] = i;
            //检测冲突
            if (judge(n)) {
                check(n + 1);
            }
            //如果冲突
        }
    }


    /**
     * 放置皇后后，检测是否冲突
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 判断第n个皇后是否和前面的皇后在同一列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 判断第n个皇后是否和第i个皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
