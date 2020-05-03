package com.rickyin.coding.array;

public class coding_53_最大子序和 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2};
        System.out.println(maxSubArray_3(nums));
    }

    /**
     * 最大子序和问题:
     * 思路：暴力解法,找到以每个元素为开始位置的最大子序和(思路简单)
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        //从0位置的元素开始
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            //循环找以i位置作为子序开始元素的最大子序和
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    /**
     * 动态规划:
     * 示例: [a, b , c, d , e]
     *    解答这类题目, 省略不掉遍历, 因此我们先从遍历方式说起
     *    通常我们遍历子串或者子序列有三种遍历方式
     *      - 以某个节点为开头的所有子序列: 如 [a]，[a, b]，[ a, b, c] ... 再从以 b 为开头的子序列开始遍历 [b] [b, c]。
     *      - 根据子序列的长度为标杆，如先遍历出子序列长度为 1 的子序列，在遍历出长度为 2 的 等等。
     *      - 以子序列的结束节点为基准，先遍历出以某个节点为结束的所有子序列，因为每个节点都可能会是子序列的结束节点，因此要遍历下整个序列，
     *        如: 以 b 为结束点的所有子序列: [a , b] [b] 以 c 为结束点的所有子序列: [a, b, c] [b, c] [ c ]。
     *     第一种遍历方式通常用于暴力解法, 第二种遍历方式 leetcode (5. 最长回文子串 ) 中的解法就用到了。
     *todo 第三种遍历方式因为可以产生递推关系,采用动态规划时,经常通过此种遍历方式,如:背包问题,最大公共子串,这里的动态规划解法也是以先遍历出以某个
     *     节点为结束节点的所有子序列的思路
     *
     *     对于刚接触动态规划的, 我感觉熟悉第三种遍历方式是需要抓住的核心
     *     因为我们通常的惯性思维是以子序列的开头为基准，先遍历出以 a 为开头的所有子序列，再遍历出以 b 为开头的...
     *     todo 但是动态规划为了找到不同子序列之间的递推关系，恰恰是以子序列的结束点为基准的，这点开阔了我们的思路。
     * 递推公式:
     * f[i] : 表示以 i 位置的元素为子序结尾的子序列的最大子序和
     *        todo 对于 i 位置来说要么它自己本身作为一个子序列( f[i] = num[i] ),要么跟它之前的最大子序和加起来( f[i] = num[i]+f[i-1] )
     *        因为我们求最大的所以 f[i] = Max(f[i-1]+num[i],num[i])
     */
    public static int maxSubArray_2(int[] nums) {
        // f[i]: 表示以 i 位置的元素为子序结尾的子序列的最大子序和
        int[] f = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                f[i] = nums[i];
            } else {
                // f[i] = Max(f[i-1]+num[i],num[i])
                f[i] = Math.max(f[i - 1] + nums[i], nums[i]);
            }
            if (f[i] > max) {
                max = f[i];
            }
        }
        return max;
    }

    /**
     * 动态规划解法优化
     * 我们发现我们的状态转移方程中只要的是 f[i-1] 上一个状态的值,所以我们可以不用数组 f[],而实简单的只用一个变量代表 f[i-1]就ok了
     */
    public static int maxSubArray_3(int[] nums) {
        int max = Integer.MIN_VALUE;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            if (pre > max) {
                max = pre;
            }
        }
        return max;
    }
}
