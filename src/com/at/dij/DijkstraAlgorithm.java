package com.at.dij;

import com.at.graph.AMGraph;

import java.util.Arrays;

public class DijkstraAlgorithm {
    //private static final int MAX = Integer.MAX_VALUE;
    private static final int MAX = 999999;

    public static void main(String[] args) {
        AMGraph graph = new AMGraph(7);
//        AMGraph graph = new AMGraph(6);
        graph.arcs[0] = new int[]{MAX, 5, 7, MAX, MAX, MAX, 2};
        graph.arcs[1] = new int[]{5, MAX, MAX, 9, MAX, MAX, 3};
        graph.arcs[2] = new int[]{7, MAX, MAX, MAX, 8, MAX, MAX,};
        graph.arcs[3] = new int[]{MAX, 9, MAX, MAX, MAX, 4, MAX};
        graph.arcs[4] = new int[]{MAX, MAX, 8, MAX, MAX, 5, 4};
        graph.arcs[5] = new int[]{MAX, MAX, MAX, 4, 5, MAX, MAX};
        graph.arcs[6] = new int[]{2, 3, MAX, MAX, 4, 6, MAX};
//        graph.arcs[0] = new int[]{MAX, MAX, 10, MAX, 30, 100};
//        graph.arcs[1] = new int[]{MAX, MAX, 5, MAX, MAX, MAX};
//        graph.arcs[2] = new int[]{MAX, MAX, MAX, 50, MAX, MAX};
//        graph.arcs[3] = new int[]{MAX, MAX, MAX, MAX, MAX, 10};
//        graph.arcs[4] = new int[]{MAX, MAX, MAX, 20, MAX, 60};
//        graph.arcs[5] = new int[]{MAX, MAX, MAX, MAX, MAX, MAX};
        //graph.print();
        shortestPath_DIJ(graph, 6);
    }

    /**
     * 获取图的v顶点到其余顶点的最短路径
     *
     * @param graph
     * @param v
     */
    public static void shortestPath_DIJ(AMGraph graph, int v) {
        //顶点个数
        final int vexNum = graph.arcs.length;
        // 记录从源点v到终点vi是否已被确定最短路径长度
        boolean[] isShort = new boolean[vexNum];
        // 记录从源点v到终点vi的当前最短路径上vi的直接前驱顶点序号,如果从v到vi有弧，则path[i]为v，否则为-1
        int[] path = new int[vexNum];
        // 记录从源点v到vi的当前最短路径长度，如果从v到vi有弧，则shortWeight[i]为弧上的权值，否则为∞
        int[] shortWeight = new int[vexNum];
        //初始化
        for (int i = 0; i < vexNum; i++) {
            shortWeight[i] = graph.arcs[v][i];
            if (shortWeight[i] < MAX) {
                path[i] = v;
            } else {
                path[i] = -1;
            }
        }
        isShort[v] = true;
        shortWeight[v] = 0;
        int destination = -1, min;
        //开始主循环,每次求得v到每个顶点vi的最短路径，将vi加入到isShort数组
        for (int i = 1; i < vexNum; i++) {// 对其余的n-1个顶点，依次计算
            min = MAX;
            for (int j = 0; j < vexNum; j++) {
                if (!isShort[j] && shortWeight[j] < min) {
                    // 选择一条当前最短路径，终点为destination
                    destination = j;
                    min = shortWeight[j];
                }
            }
            isShort[destination] = true; // 将destination加入isShort
            // 更新从v出发到V-S(尚未求出的最短路径的顶点集合)
            for (int j = 0; j < vexNum; j++) {
                if (!isShort[j] && (shortWeight[destination] + graph.arcs[destination][j] < shortWeight[j])) {
                    shortWeight[j] = shortWeight[destination] + graph.arcs[destination][j]; //更新最短路径的权值
                    path[j] = destination; //更改vi的前驱为destination
                }
            }
        }
        System.out.println(Arrays.toString(shortWeight));
    }


}
