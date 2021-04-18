package com.rickyin.coding.array;

import java.util.concurrent.locks.LockSupport;

public class coding_2_reverse_integer {
    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    public static int reverse(int x) {
        String result = "";
        String symbol = "";
        String num = String.valueOf(x);
        char[] chars = num.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if ("0".equals(chars[i]) && i == chars.length - 1) {
                continue;
            }
            if ((!Character.isDigit(chars[i])) && i == 0) {
                symbol = String.valueOf(chars[i]);
                continue;
            }
            result += chars[i];
        }
        Long resultLong = Long.valueOf(symbol + result);
        if (resultLong > Integer.MAX_VALUE || resultLong < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.valueOf(symbol + result);
    }

    /**
     * 手写下评论区大佬的代码
     */
    public static int reverse2(int x) {
        // 用long类型来接收结果
        long result = 0;
        while (x != 0) {
            // 每次从结果中取一位出来
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return (int) result == result ? (int) result : 0;
    }
}
