package com.at.graph;

import java.util.*;

/**
 * 图的邻接矩阵存储表示
 */
public class AMGraph {
    private List<String> vertex; //顶点表
    int[][] arcs; // 临界矩阵
    int vexNum, arcNum; //图的当前点数和边数

    public AMGraph(int n) {
        vertex = new ArrayList<>();
        arcs = new int[n][n];
        vexNum = 0;
        arcNum = 0;
    }

    /**
     * 返回顶点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertex.size();
    }

    /**
     * 获取边的数目
     *
     * @return
     */
    public int getNumOfEdge() {
        return arcNum;
    }

    /**
     * 获取下标对应的数据
     *
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertex.get(i);
    }

    /**
     * 获取权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return arcs[v1][v2];
    }

    public void DFS(int i) {
        boolean[] visited = new boolean[arcs.length];
        DFS(i, visited);
    }

    private void DFS(int i, boolean[] visited) {
        System.out.print(getValueByIndex(i) + "->");
        visited[i] = true;
        //查找结点i的第一个邻接结点
        int w = firstAdjVex(i);
        while (w != -1) {
            if (!visited[w]) {
                DFS(w, visited);
            }
            //如果结点已经访问
            w = nextAdjVex(i, w);
        }
    }

    public void BFS(int i) {
        boolean[] visited = new boolean[arcs.length];
        BFS(i, visited);
    }

    private void BFS(int i, boolean[] visited) {
        System.out.print(getValueByIndex(i) + "->");
        visited[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        int u, w;
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = queue.poll();
            w = firstAdjVex(u);
            while (w >= 0) {
                if (!visited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    visited[w] = true;
                    queue.add(w);
                }
                w = nextAdjVex(u, w);
            }
        }
    }

    /**
     * 插入结点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        this.vertex.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     点的下标
     * @param v2     第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        arcs[v1][v2] = weight;
        arcs[v2][v1] = weight;
        arcNum++;
    }

    public int firstAdjVex(int v) {
        for (int i = 0; i < vertex.size(); i++) {
            if (arcs[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取v相对于w的下一个邻接点
     *
     * @param v
     * @param w
     * @return
     */
    public int nextAdjVex(int v, int w) {
        for (int i = w + 1; i < vertex.size(); i++) {
            if (arcs[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        for (int[] arc : arcs) {
            System.out.println(Arrays.toString(arc));
        }
    }

    public static void main(String[] args) {
        AMGraph graph = new AMGraph(5);
        String[] vertex = {"A", "B", "C", "D", "E"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.print();
        System.out.println("深度优先遍历");
        graph.DFS(0);
        System.out.println();
        System.out.println("广度优先遍历");
        graph.BFS(0);
    }
}
