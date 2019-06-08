package com.at.prim;

import com.at.graph.AMGraph;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class PrimAlgorithm {
    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        AMGraph graph = new AMGraph(6);
        String[] vertex = {"A", "B", "C", "D", "E", "F"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        //添加边
        // A-B-6 A-C-1 A-D-5
        // B-C-5 B-E-3
        // c-d-5 c-e-6 c-f-4
        // d-f-2
        // e-f-6
        graph.insertEdge(0, 1, 6);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(0, 3, 5);
        graph.insertEdge(1, 2, 5);
        graph.insertEdge(1, 4, 3);
        graph.insertEdge(2, 3, 5);
        graph.insertEdge(2, 4, 6);
        graph.insertEdge(2, 5, 4);
        graph.insertEdge(3, 5, 2);
        graph.insertEdge(4, 5, 6);
        for (int i = 0; i < graph.arcs.length; i++) {
            for (int j = 0; j < graph.arcs[i].length; j++) {
                if (graph.arcs[i][j] == 0) {
                    graph.arcs[i][j] = MAX;
                }
            }
        }
        long start = System.currentTimeMillis();
        miniSpanTree_Prim(graph, 0);
        //miniSpanTree_Prim2(graph, 0);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void miniSpanTree_Prim(AMGraph graph, int v) {
        //最小边在u中的那个顶点
        String[] adjvex = new String[graph.vexNum];
        // 最小边上的权值
        int[] lowcost = new int[graph.vexNum];
        for (int i = 0; i < graph.vexNum; i++) {
            if (i != v) {
                adjvex[i] = graph.getValueByIndex(v);
                lowcost[i] = graph.arcs[v][i];
            }
        }
        lowcost[v] = 0;
        for (int i = 1; i < graph.vexNum; i++) {
            //int k = IntStream.range(0, lowcost.length).reduce((p, q) -> lowcost[p] > lowcost[q] ? q : p).getAsInt();
            int k = getMinIndex(lowcost);
            String minVex = adjvex[k];
            String minVexOther = graph.vertex.get(k);
            System.out.printf("边<%s,%s>\n", minVex, minVexOther);
            lowcost[k] = 0;
            for (int j = 0; j < graph.vexNum; j++) {
                if (graph.arcs[k][j] < lowcost[j]) {
                    adjvex[j] = graph.vertex.get(k);
                    lowcost[j] = graph.arcs[k][j];
                }
            }
        }
    }

    public static void miniSpanTree_Prim2(AMGraph graph, int v) {
        //标记结点是否被访问
        boolean[] visited = new boolean[graph.vexNum];
        visited[v] = true;
        //记录两个顶点的下标
        int h1 = -1, h2 = -1;
        int minWeight = MAX;
        for (int i = 1; i < graph.vexNum; i++) {
            for (int j = 0; j < graph.vexNum; j++) {
                for (int k = 0; k < graph.vexNum; k++) {
                    if (visited[j] && !visited[k] && graph.arcs[j][k] < minWeight) {
                        minWeight = graph.arcs[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }

            }
            // 找到一条最短边
            System.out.printf("边<%s,%s>\n", graph.vertex.get(h1), graph.vertex.get(h2));
            visited[h2] = true;
            minWeight = MAX;
        }
    }

    public static int getMinIndex(int[] arr) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[minIndex] == 0) {
                minIndex++;
            }
            if (arr[i] < arr[minIndex] && arr[i] != 0) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
