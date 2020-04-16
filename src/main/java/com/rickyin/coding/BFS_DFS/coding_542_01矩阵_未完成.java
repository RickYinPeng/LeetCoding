package com.rickyin.coding.BFS_DFS;

import javax.xml.stream.FactoryConfigurationError;
import java.util.LinkedList;
import java.util.Queue;

public class coding_542_01矩阵_未完成 {
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
}
