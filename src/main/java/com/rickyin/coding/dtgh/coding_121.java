package com.rickyin.coding.dtgh;

public class coding_121 {
    public static void main(String[] args) {
        int[] a = {};
        System.out.println(maxProfit(a));
    }

    /**
     * 买卖股票的最佳时机---动态规划
     * 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     */
    public static int maxProfit(int[] prices) {
        //防止传入空数组
        if(prices.length <= 1)
            return 0;

        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(prices[i],min);
        }
        return max;
    }
}
