package com.rickyin.coding.BFS_DFS;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class coding_542_01矩阵 {
    public static void main(String[] args) {
        int[][] m = {
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1}
        };
        int[][] ints = updateMatrix(m);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 广度优先搜索  垃圾
     */
    public static int[][] updateMatrix(int[][] matrix) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    continue;
                }
                Queue<int[]> queue = new LinkedList();
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                int count = 1;
                retry:
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    boolean flag = false;
                    for (int k = 0; k < 4; k++) {
                        int newX = poll[0] + dx[k];
                        int newY = poll[1] + dy[k];
                        if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[j].length) {
                            continue;
                        }
                        if (matrix[newX][newY] == 0) {
                            res[i][j] = count;
                            flag = true;
                            break retry;
                        } else {
                            if (visited[newX][newY] != true) {
                                queue.add(new int[]{newX, newY});
                                visited[newX][newY] = true;
                            }
                        }
                    }
                    if (!flag) {
                        count += 1;
                    }
                }

            }
        }
        return res;
    }

    /**
     * 采用BFS算法
     */
    public static int[][] updateMatrix_2(int[][] matrix) {
        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] points = queue.poll();
            int x = points[0];
            int y = points[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return matrix;
    }
}
