package com.at;

import java.util.*;

/**
 * 你被请来给一个要举办高尔夫比赛的树林砍树. 树林由一个非负的二维数组表示， 在这个数组中：
     0 表示障碍，无法触碰到.
     1 表示可以行走的地面.
     比1大的数 表示一颗允许走过的树的高度.
     你被要求按照树的高度从低向高砍掉所有的树，每砍过一颗树，树的高度变为1。
     你将从（0，0）点开始工作，你应该返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
     可以保证的是，没有两棵树的高度是相同的，并且至少有一颗树需要你砍。
     示例 1:
     输入:
     [
      [1,2,3],
      [0,0,4],
      [7,6,5]
     ]
     输出: 6
      
     示例 2:
     输入:
     [
      [1,2,3],
      [0,0,0],
      [7,6,5]
     ]
     输出: -1
      
     示例 3:
     输入:
     [
      [2,3,4],
      [0,0,5],
      [8,7,6]
     ]
     输出: 6
     解释: (0,0) 位置的树，你可以直接砍去，不用算步数
 */
public class Demo {
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) {
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 4),
                Arrays.asList(7, 6, 5));
        Demo demo = new Demo();
        System.out.println(demo.cutOffTree(lists));
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return -1;
        int m = forest.size(), n = forest.get(0).size(), res = 0;
        //first step: sort the tree position based on its height
        List<int[]> heights = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) heights.add(new int[]{forest.get(i).get(j), i, j});
            }
        }
//        Collections.sort(heights, new Comparator<int[]>() {
//            public int compare(int[] arr1, int[] arr2) {
//                return Integer.compare(arr1[0], arr2[0]);
//            }
//        });

        Collections.sort(heights, Comparator.comparingInt(arr -> arr[0]));

        //second step: accumulate the shortest steps between each two adajacent points in heights[].
        int start_x = 0, start_y = 0;
        for (int i = 0; i < heights.size(); i++) {
            int cnt_steps = BFS(forest, m, n, start_x, start_y, heights.get(i)[1], heights.get(i)[2]);
            if (cnt_steps == -1) return -1;
            res += cnt_steps;
            start_x = heights.get(i)[1];
            start_y = heights.get(i)[2];
        }
        return res;
    }

    public int BFS(List<List<Integer>> forest, int m, int n, int start_x, int start_y, int des_x, int des_y) {
        if (start_x == des_x && start_y == des_y) return 0;
        int steps = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start_x, start_y});
        int[][] visited = new int[m][n];
        visited[start_x][start_y] = 1;
        while (!q.isEmpty()) {
            int qsize = q.size();
            steps++;
            while (qsize-- > 0) {
                int[] cur = q.poll();
                int cur_x = cur[0], cur_y = cur[1];
                for (int k = 0; k < 4; k++) {
                    int x = cur_x + dir[k][0], y = cur_y + dir[k][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && forest.get(x).get(y) > 0 && visited[x][y] == 0) {
                        if (x == des_x && y == des_y) return steps;
                        visited[x][y] = 1;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return -1;

    }
}
