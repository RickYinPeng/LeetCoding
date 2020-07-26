package com.rickyin.coding.array;

public class coding_209_长度最小的子数组 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int s = 11;
        System.out.println(minSubArrayLen1(s, arr));
    }

    /**
     * 暴力方法
     * 低能选手
     */
    public static int minSubArrayLen1(int s, int[] nums) {
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            if (sum >= s) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    count = Math.min(count, j - i + 1);
                    break;
                }
            }
        }
        return count == Integer.MAX_VALUE ? 0 : count;
    }

    public static int minSubArrayLen2(int s, int[] nums) {


        return 0;
    }
}
