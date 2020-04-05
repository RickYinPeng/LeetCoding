package com.rickyin.coding.array;

public class coding_42 {
    public static void main(String[] args) {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(a));
    }

    /**
     * 按照行数求解雨水
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum = 0;
        int max = getMax(height);
        for (int i = 1; i <= max; i++) {
            boolean flag = false;
            int temp = 0;
            for (int j = 0; j < height.length; j++) {
                if (flag && height[j] < i) {
                    temp++;
                }
                if (height[j] >= i) {
                    sum += temp;
                    temp = 0;
                    flag = true; // 开始更新temp
                }
            }
        }
        return sum;
    }

    /**
     * 获取数组中的最大值
     *
     * @param height
     * @return
     */
    private static int getMax(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }
        return max;
    }

    /**
     * 按照列求解雨水
     *
     * @param height
     * @return
     */
    public static int trap_2(int[] height) {
        int sum = 0;
        //数组最两边不会蓄水，所以不考虑
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }

            int max_right = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            int min = Math.min(max_left, max_right);
            if (min > height[i]) {
                sum += (min - height[i]);
            }
        }
        return sum;
    }
}
