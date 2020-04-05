package com.rickyin.coding.dtgh;

/**
 * 198.打家劫舍(简单)
 */
public class coding_198 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 1};
        int rob = rob_1(a);
        System.out.println(rob);
    }

    /**
     * 思路：两种方式，从第一个开始计算，从第二个开始计算，两个结果比较，取大的
     * TODO: 垃圾
     *
     * @param nums
     * @return
     */
    public static int rob_1(int[] nums) {
        //从第1个开始
        int sum1 = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            sum1 += nums[i];
        }

        //从2开始
        int sum2 = 0;
        for (int i = 1; i < nums.length; i = i + 2) {
            sum2 += nums[i];
        }
        return sum1 > sum2 ? sum1 : sum2;
    }

    /**
     * 动态规划：对于每家其实都是偷与不偷两种结果
     * 问题：不知道如何去建立子结构公式？？？？---》没有分析出每个房子的结果是偷与不偷
     *
     * @param nums
     * @return
     */
    public static int rob_2(int[] nums) {
        //d[i]:表示偷取前i家获得最大的金钱树
        //d[i] = Max(d[i-1],d[i-2]+num[i-1])
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //建立子结构数组
        //d[0]:表示偷取前0家获得最大 d[0] = 0
        //d[1]:表示偷取前1家获得最大 d[1] = num[0]
        //d[2] = Max(d[1],d[0]+nums[1])

        int[] d = new int[nums.length + 1];
        d[0] = 0;
        d[1] = nums[0];
        for (int i = 2; i < d.length; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + nums[i - 1]);
        }
        return d[d.length - 1];
    }


}
