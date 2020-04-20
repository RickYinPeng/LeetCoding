package com.rickyin.coding.grap;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 这个题一直不能理解题目意思
 */
public class coding_1162_地图分析 {
    public static void main(String[] args) {
        int[][] r = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int i = maxDistance(r);
        System.out.println(i);
    }

    /**
     * 暴力解法 --- 官方的解法是BFS
     *
     * @param grid
     * @return
     */
    public static int maxDistance(int[][] grid) {
        /**
         * 保存陆地的下标
         * list = {[1,0],[0,1]}
         */
        boolean landflag = false;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //如果是陆地区域则保存下标
                if (grid[i][j] == 1) {
                    int[] land = {i, j};
                    list.add(land);
                    landflag = true;
                }
            }
        }
        if (landflag == false) {
            return -1;
        }
        boolean waterflag = false;
        List<Integer> minList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //如果是海洋区域,计算它与陆地区域的距离，并且取最小值放入List中
                if (grid[i][j] == 0) {
                    int initDistance = 0;
                    for (int[] ints : list) {
                        int land_X = ints[0];
                        int land_Y = ints[1];
                        //计算当前海洋区域到陆地区域的距离
                        int distance = Math.abs(land_X - i) + Math.abs(land_Y - j);
                        if (initDistance == 0) {
                            initDistance = distance;
                        } else {
                            initDistance = Math.min(initDistance, distance);
                        }
                    }
                    //initDistance就是当前海洋区域距离最近的陆地的距离
                    minList.add(initDistance);
                    waterflag = true;
                }
            }
        }
        if (waterflag == false) {
            return -1;
        }
        Collections.sort(minList);
        return minList.get(minList.size() - 1);
    }

    /**
     * 多源广度优先搜索(多源BFS):添加超级源点可以使多源BFS退化成单源BFS。
     */
    public static int maxDistance_2(int[][] grid) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;
        // 先把所有的陆地下标都入队。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0];
            int y = point[1];
            // 取出队列的元素，将其四周的海洋入队。
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1;
                hasOcean = true;
                queue.add(new int[]{newX, newY});
            }
        }
        //如果没有陆地或者没有海洋，返回-1
        if (point == null || !hasOcean) {
            return -1;
        }
        //返回最后一次遍历到的海洋的距离
        return grid[point[0]][point[1]] - 1;
    }
}
