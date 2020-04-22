package com.rickyin.coding.array;

import java.util.HashMap;
import java.util.Map;

public class coding_1_两数之和 {
    public static void main(String[] args) {

    }

    /**
     * 两数之和
     * 利用HashMap将时间复杂度优化到 O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
