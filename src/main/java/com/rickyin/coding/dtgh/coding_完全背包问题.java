package com.rickyin.coding.dtgh;

import java.util.Scanner;

/**
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。(注意：无限件可用！！！)
 * 第 i 种物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。
 *
 */
public class coding_完全背包问题 {
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
        System.out.println(AllBackPack(N, V, v, w));
    }

    public static int AllBackPack(int N, int V, int[] v, int[] w) {
        int[][] f = new int[N + 1][V + 1];
        //初始化
        for (int i = 0; i <= V; i++) {
            f[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                //对于完全背包问题，对于一个物品要么不选，要么选一次，要么选两次，要么选 n 次
                for (int k = 0; k * v[i] <= j; k++) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - k * v[i]] + k * w[i]);
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= V; i++) {
            res = Math.max(res, f[N][i]);
        }
        //System.out.println(res);
        return res;
    }
}
