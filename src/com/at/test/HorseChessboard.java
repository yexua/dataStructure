package com.at.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 马走棋盘问题
 * 游戏演示：http://www.4399.com/flash/146267_2.htm
 */
public class HorseChessboard {

    private static int N = 8;
    private static boolean[] visited = new boolean[N * N];
    private static boolean finished = false;

    public static void main(String[] args) {
        final long l = System.currentTimeMillis();
        int[][] chessBoard = new int[N][N];
        chess(chessBoard, 0, 0);
        for (int[] ints : chessBoard) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void chess(int[][] chessBoard, int row, int col) {
        final int N = chessBoard.length;
        boolean[] visited = new boolean[N * N];
        chess(chessBoard, 0, 0, 1);
    }

    /**
     * @param chessBoard 棋盘
     * @param row        当前位置，行
     * @param col        当前位置，列
     * @param step       第几步
     */
    public static void chess(int[][] chessBoard, int row, int col, int step) {
        chessBoard[row][col] = step;
        visited[row * N + col] = true;
        List<Point> next = next(new Point(col, row));
        sort(next);
        while (!next.isEmpty()) {
            Point p = next.remove(0);
            if (!visited[p.y * N + p.x]) {
                chess(chessBoard, p.y, p.x, step + 1);
            }
        }
        //判断是否走完全部棋盘
        if (step < N * N && !finished) {
            chessBoard[row][col] = 0;
            visited[row * N + col] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 获取当前坐标的可移动坐标
     *
     * @param point
     * @return
     */
    public static List<Point> next(Point point) {
        List<Point> list = new ArrayList<>();
        Point p = new Point();

        if ((p.y = point.y - 1) >= 0 && (p.x = point.x - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.y = point.y - 2) >= 0 && ((p.x = point.x - 1)) >= 0) {
            list.add(new Point(p));
        }
        if ((p.y = point.y - 2) >= 0 && (p.x = point.x + 1) < N) {
            list.add(new Point(p));
        }
        if ((p.y = point.y - 1) >= 0 && (p.x = point.x + 2) < N) {
            list.add(new Point(p));
        }
        if ((p.y = point.y + 1) < N && (p.x = point.x + 2) < N) {
            list.add(new Point(p));
        }
        if ((p.y = point.y + 2) < N && (p.x = point.x + 1) < N) {
            list.add(new Point(p));
        }
        if ((p.y = point.y + 2) < N && (p.x = point.x - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.y = point.y + 1) < N && (p.x = point.x - 2) >= 0) {
            list.add(new Point(p));
        }

        return list;
    }

    /**
     * 在所有的下一步可移动坐标中选择一个位置，进行非递减排序
     * <p>
     * 减少回溯的次数
     *
     * @param list
     */
    public static void sort(List<Point> list) {
        list.sort((Point o1, Point o2) -> {
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            if (count1 < count2) {
                return -1;
            } else if (count1 == count2) {
                return 0;
            } else {
                return 1;
            }
        });
    }
}
