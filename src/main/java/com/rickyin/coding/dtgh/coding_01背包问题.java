package com.rickyin.coding.dtgh;

import java.util.Scanner;

/**
 * 本题在LeetCode中没有
 * 题目描述:
 *  有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
 *  第 i 件物品的体积是 vi，价值是 wi。
 *  求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。
 * 输入格式:
 *  第一行两个整数，N，V，用空格隔开，分别表示物品数量和背包容积。
 *  接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 件物品的体积和价值。
 * 输出格式:
 *  输出一个整数，表示最大价值。
 *
 * 思路: (背包问题是动态规划的经典问题)
 *  f[i][j] 表示只看前 i 个物品,这前i个物品恰好装入容量是 j 的背包的情况下,总价值最大是多少(例:前10个物品,这前10个物品中有的被装了进来,有的没有被装进来)
 *  结果: result = Max{f[n][0~V]} 表示前 n 个物品，体积是 0~V 的情况下最大的总价值
 *
 *  假设:我们已经计算完了前 i-1 个物品的所有状态
 *   1.如果第 i 个物品不选择,就是不放入背包中,那么前 i 个背包的最大价值就是前 i-1 个物品的价值 即 f[i][j] = f[i-j][j]
 *   2.如果选择第 i 个物品,前 i-1 个物品的体积就是 j-v[i],状态方程就是 f[i-1][j-v[i]],此时的价值是前 i-1 个物品的价值,少了第 i 个物品的价
 *     值,所以 f[i][j] = f[i-1][j-v[i]] + w[i];
 *  最终:
 *    f[i][j] = Max{f[i-j][j],f[i-1][j-v[i]] + w[i]}
 *
 *  初始化:
 *    f[0][0] = 0;
 *    f[0][1] = 0;
 *    f[1][0] = Max{f[0][0],f[0][0-v[1]] + w[1]}
 */
public class coding_01背包问题 {
    public static void main(String[] args) {
        //输入
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int[] v = new int[N + 1];
        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        //BackPack01(N,V,v,w);
        BackPack01_2(N, V, v, w);
    }

    /**
     * 01背包问题常规动态规划解法
     */
    public static int BackPack01(int N, int V, int[] v, int[] w) {

        int[][] f = new int[N + 1][V + 1];
        //初始化
        for (int i = 0; i <= V; i++) {
            f[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                //不选第 i 个物品
                f[i][j] = f[i - 1][j];

                //如果 j>=v[i]才可以选择第 i 个物品
                //why? 因为当 j>=v[i] 时 f[i - 1][j - v[i]] 这个表达式才能成立,如果 j<v[i],那么 [j - v[i]] 就是负数了,不满足题意了)
                if (j >= v[i]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - v[i]] + w[i]);
                }
            }
        }

        int res = 0;
        for (int i = 0; i <= V; i++) {
            res = Math.max(res, f[N][i]);
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/coin-lcci/solution/bei-bao-jiu-jiang-ge-ren-yi-jian-da-jia-fen-xiang-/
     * 优化: 滚动数组的方式,很有意思可以下去研究一下
     * 使用一维数组来优化,这个方法很有意思
     */
    public static int BackPack01_2(int N, int V, int[] v, int[] w) {
        /**
         * 换成一维数组后 f[i] 表示物品体积之和最大为 i 时的最大价值，而非恰好为 i 时的最大价值。
         */
        int[] f = new int[V + 1];
        //初始化
        f[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
            }
            //倒序之前的for循环
//            for (int j = 0; j <= V; j++) {
//                if (j >= v[i]) {
//                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
//                }
//            }
        }
        System.out.println(f[V]);
        /**
         * 这里为什么直接返回 f[V] 很有意思
         */
        return f[V];
    }
}
