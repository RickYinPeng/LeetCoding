package com.rickyin.coding.dtgh;

/**
 * 按摩师,题号: 17.16(跟之前第198道题解法一样，代码都没有变)
 */
public class coding_17_16_按摩师 {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] d = new int[nums.length + 1];
        d[0] = 0;
        d[1] = nums[0];
        for (int i = 2; i < d.length; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + nums[i - 1]);
        }
        return d[d.length - 1];
    }
}
