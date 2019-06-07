package com.at.dynamic;

/**
 * 01背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; // 物品的个数
        //记录背包的放入记录
        int[][] path = new int[n + 1][m + 1];
        // v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    // 沿用上次策略
                    v[i][j] = v[i - 1][j];
                } else {
//                  v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] > val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }

                }
            }
        }
        //输出背包记录表格
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.printf("%4d\t", anInt);
            }
            System.out.println();
        }

        //输出背包放入记录
        //输出存在冗余，只需要最后的放入情况
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if(path[i][j] == 1){
//                    System.out.printf("第%d个商品放入背包\n", i);
//                }
//            }
//        }
        int row = path.length - 1;
        int col = path[0].length - 1;
        while (row > 0 && col > 0) {
            if (path[row][col] == 1) {
                System.out.printf("第%d个商品放入背包\n", row);
                col -= w[row - 1];
            }
            row--;
        }
    }
}

