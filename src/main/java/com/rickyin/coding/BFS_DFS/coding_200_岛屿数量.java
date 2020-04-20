package com.rickyin.coding.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class coding_200_岛屿数量 {
    public static void main(String[] args) {
        //[[],[],[],[]]
        char[][] input = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(input));
    }

    /**
     * BFS广度优先搜索
     */
    public static int numIslands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] point = queue.poll();
                        int x = point[0];
                        int y = point[1];
                        if (grid[x][y] == 'a') {
                            continue;
                        }
                        for (int k = 0; k < 4; k++) {
                            int newX = x + dx[k];
                            int newY = y + dy[k];
                            if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] != 'a' && grid[newX][newY] != '0') {
                                queue.offer(new int[]{newX, newY});
                            }
                        }
                        grid[x][y] = 'a';
                    }
                }
            }
        }
        return count;
    }
}
