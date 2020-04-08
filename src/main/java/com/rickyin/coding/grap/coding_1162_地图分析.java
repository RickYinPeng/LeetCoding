package com.rickyin.coding.grap;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 这个题一直不能理解题目意思
 */
public class coding_1162_地图分析 {
    public static void main(String[] args) {
        int[][] r = {{1,0,0},{0,0,0},{0,0,0}};
        int i = maxDistance(r);
        System.out.println(i);
    }

    /**
     * 暴力解法 --- 官方的解法是BFS
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
        if(landflag == false){
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
        if(waterflag == false){
            return -1;
        }
        Collections.sort(minList);
        return minList.get(minList.size()-1);
    }
}
