package com.at.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 马走棋盘问题
 * 游戏演示：http://www.4399.com/flash/146267_2.htm
 */
public class HorseChessboard {


    public static void chess(int[][] chessBoard) {
        final int N = chessBoard.length;
        boolean[] visited = new boolean[N * N];
        chessBoard[row][col] = step;

    }

    public static void chess(int[][] chessBoard, int row, int col, int step) {
        chessBoard[row][col] = step;

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

        if ((p.x = point.x - 2) >= 0 && (p.y = point.y - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 1) >= 0 && (p.y = point.y - 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 2) >= 0 && (p.y = point.y - 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 2) >= 0 && (p.y = point.y + 1) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x + 1) >= 0 && (p.y = point.y + 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y + 2) >= 0) {
            list.add(new Point(p));
        }
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y + 1) >= 0) {
            list.add(new Point(p));
        }

        return list;
    }
}
