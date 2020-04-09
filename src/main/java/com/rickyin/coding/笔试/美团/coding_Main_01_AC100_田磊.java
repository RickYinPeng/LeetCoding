package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

public class coding_Main_01_AC100_田磊 {
    private static int newWeek, newH, newM;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int week = sc.nextInt(); //周几
        String inputString = sc.next(); //时间
        int h = Integer.parseInt(inputString.split(":")[0]); // 小时
        int m = Integer.parseInt(inputString.split(":")[1]); // 分钟
        int n = sc.nextInt(); //多少分钟前
        sc.close();
        fun(week, h, m, n);
        System.out.println(newWeek);
        System.out.printf("%02d:%2d\n", newH, newM);
    }

    private static void fun(int week, int h, int m, int n) {
        if (n <= 0) {
            newWeek = week;
            newH = h;
            newM = m;
            return;
        }
        /**
         * newM = n % 60
         * 含义：题目中给的是多少分钟前，比如给定200，那么 200 % 60 = 20 往前推60分钟，小时就要+1 ，相当于求得是最后余下得分钟
         *      因为往前推60分钟，我们时间的分钟位是不变的，只是小时位要+1
         *      最终余下20分钟，如果我们当前时间的分钟是50分钟，那么最终我们的分钟位的值就是 50-20=30
         *                   如果我们当前的时间分钟时10分钟，那么最终我们的分钟位的值就是 60-20+10 = 50
         */
        newM = n % 60; // 算出新的分钟
        newH = (n / 60) % 24; // 算出新的小时
        newWeek = (n / 1440) % 7; // 算出新的周几
        //算出分钟
        if (newM <= m) {
            newM = m - newM;
        } else {
            if (h >= 1) { //如果小时 >= 1
                h--;
            } else {
                week--;
                h = 23;
            }
            newM = m + 60 - newM;
        }
        //算出小时
        if (newH <= h) {
            newH = h - newH;
        } else {
            week--;
            newH = h + 24 - newH;
        }
        //算出周几
        if (newWeek <= week) {
            newWeek = week - newWeek;
        } else {
            newWeek = week + 7 - newWeek;
        }
        if (newWeek == 0) {
            newWeek = 7;
        }
    }
}
