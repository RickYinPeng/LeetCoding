package com.rickyin.coding.string;


import java.math.BigDecimal;
import java.math.BigInteger;

public class coding_8 {
    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
    }

    public static int myAtoi(String str) {
        if (str.trim().equals("") || (str.length() == 1 && !Character.isDigit(str.charAt(0)))) {
            return 0;
        }
        char[] chars = str.trim().toCharArray();
        //如果第一个字符是正负号或数字的时候的情况
        //false: 第一个字符为负
        //true: 第一个字符为正
        int ans = 0;
        boolean flag = false;
        if (chars[0] == '+') {
            flag = true;
        } else if (chars[0] == '-') {
            flag = false;
        } else if (!Character.isDigit(chars[0])) {
            return 0;
        } else if (Character.isDigit(chars[0])) {
            flag = true;
            ans = ans * 10 + (chars[0] - '0');
        }
        //找出后续连续的数字

        for (int i = 1; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                int dight = chars[i] - '0';
                if (ans > (Integer.MAX_VALUE - dight) / 10) {
                    return flag ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                ans = ans * 10 + dight;
            }
            if (!Character.isDigit(chars[i])) {
                break;
            }
        }
        return flag ? ans : -ans;
    }
}
