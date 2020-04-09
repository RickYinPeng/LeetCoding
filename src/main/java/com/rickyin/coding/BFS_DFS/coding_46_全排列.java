package com.rickyin.coding.BFS_DFS;

import java.util.ArrayList;
import java.util.List;

public class coding_46_全排列 {
    public static void main(String[] args) {
    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        return null;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        //递归结束条件
        if(depth == len){
            res.add(new ArrayList<Integer>(path));
            return;
        }
    }
}
