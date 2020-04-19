package com.rickyin.coding.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class coding_56_合并区间_做的不好 {
    public static void main(String[] args) {
        int[][] a = {
                {1, 4},
                {4, 5}
        };
        int[][] merge = merge(a);
        for (int i = 0; i < merge.length; i++) {
            for (int j = 0; j < merge[i].length; j++) {
                System.out.print(merge[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(intervals[i]);
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] base = list.get(i);
            int baseStart = base[0];
            int baseEnd = base[1];
            for (int j = 0; j < list.size(); j++) {
                if (i == j) continue;
                int[] tar = list.get(j);
                int tarStart = tar[0];
                int tarEnd = tar[1];
                if (tarStart == -1 && tarEnd == -1) {
                    continue;
                }
                //证明重叠
                if ((baseStart >= tarStart && baseStart <= tarEnd) || (baseEnd >= tarStart && baseEnd <= tarEnd)) {
                    int newStart = Math.min(baseStart, tarStart);
                    int newEnd = Math.max(baseEnd, tarEnd);
                    list.set(j, new int[]{newStart, newEnd});
                    list.set(i, new int[]{-1, -1});
                    count++;
                    break;
                }
            }
        }
        List<int[]> resList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            int[] ints = list.get(i);
            if (ints[0] != -1 && ints[1] != -1){
                resList.add(ints);
            }
        }
        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            int[] ints = resList.get(i);
            res[i] = ints;
        }
        return res;
    }
}
