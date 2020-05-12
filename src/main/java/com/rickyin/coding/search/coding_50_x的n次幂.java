package com.rickyin.coding.search;

import org.omg.CORBA.INTERNAL;

public class coding_50_x的n次幂 {
    public static void main(String[] args) {
        System.out.println(myPow2(2.00000, 10));
    }

    /**
     * 快速幂算法
     */
    public static double myPow(double x, int n) {
        long pow = n;
        double res = 1;
        if (pow < 0) {
            x = 1 / x;
            pow = - pow;
        }
        while (pow > 0) {
            if (pow % 2 == 0) {
                //如果指数为偶数
                pow = pow / 2; //把指数缩小为一半
                x = x * x; //底数变大成原来的平方
            } else {
                //如果指数为奇数
                pow = pow - 1; //把指数减去1，使其变成一个偶数
                res = res * x;
                pow = pow / 2;
                x = x * x;
            }
        }
        return res;
    }

    /**
     * 快速幂算法优化
     */
    public static double myPow2(double x, int n) {
        long pow = n;
        double res = 1;
        if (pow < 0) {
            x = 1 / x;
            pow = - pow;
        }
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = res * x;
            }
            pow = pow >>> 1;
            x = x * x;
        }
        return res;
    }

    public long normalPower(long base, long power) {
        long result = 1;
        for (int i = 1; i <= power; i++) {
            result = result * base;
            result = result%1000;
        }
        return result % 1000;
    }
}
