package com.rickyin.coding.string;

import javax.xml.stream.FactoryConfigurationError;
import java.util.HashMap;

public class coding_5_longest_Palindromic_Substring_暴力解法完成_待优化 {
    public static void main(String[] args) {
        String longestPalindrome = longestPalindrome3("ac");
        System.out.println(longestPalindrome);
    }

    /**
     * 过了81个用例，实在不知道哪些步骤没有考虑到，接下来使用暴力解法尝试
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        /**
         * 思路：我们从第一个字符开始往后找到与这个字符相等的字符，然后判断这两个字符串和中间组成的字符串是否是一个回文串，如果是，那么
         * 我们保存它的长度并且保存其开始位置，然后继续遍历往后找，直到找完整个字符串
         */
        if ("".equals(s) || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int maxLength = 0;
        HashMap<Integer, String> resultMap = new HashMap<>();
        //每次从哪个字符开始
        for (int i = 0; i < s.length() - 1; i++) {
            //获取首个字符
            char start = s.charAt(i);
            //每次从哪开始
            //|| !s.substring(j + 1,s.length()).contains(Character.toString(start))
            StringBuilder sb = new StringBuilder();
            for (int j = i + 1; j < s.length(); j++) {
                char end = s.charAt(j);
                if (start == end) {
                    //判断中间的是否是回文串
                    if ("".equals(sb.toString())) {
                        if (sb.length() + 2 > maxLength) {
                            maxLength = sb.length() + 2;
                            resultMap.put(maxLength, start + sb.toString() + end + "");
                        }
                    }
                    String tmp = sb.toString();
                    StringBuilder reverse = sb.reverse();
                    if (tmp.equals(reverse.toString())) {
                        if (sb.length() + 2 > maxLength) {
                            maxLength = sb.length() + 2;
                            resultMap.put(maxLength, start + sb.toString() + end + "");
                        }
                    }

                } else {
                    sb.append(end);
                }
            }
        }
        return resultMap.get(maxLength) == null ? String.valueOf(s.charAt(0)) : resultMap.get(maxLength);
    }

    /**
     * 暴力解法：枚举所有长度大于2的字串，然后判断该串是否是回文串，找出最大的那一个
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int maxLength = 0;
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = new StringBuilder(String.valueOf(s.charAt(i)));
            for (int j = i; j < s.length(); j++) {
                if (i != j) {
                    sb = sb.append(s.charAt(j));
                }
                //如果是回文串，那么和maxLength比较，如果大那么
                if (validatePalindrome(sb.toString())) {
                    if (sb.toString().length() > maxLength) {
                        maxLength = sb.toString().length();
                        startIndex = i;
                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    /**
     * 判断是否是回文子串
     * todo 之前判断是否是回文串的逻辑，实在太蠢了
     *
     * @param s
     * @return
     */
    public static boolean validatePalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        if (sb.reverse().toString().equals(s)) {
            return true;
        }
        return false;
    }

    /**
     * 上面的暴力解法超时了，cao，所以优化下
     * 优化点：
     * 1. s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
     * 2. 如果目前子串的长度没有maxLength长度大的化，那么就没必要比较它了，因为比较了也是白比较（这里是最主要的优化点，因为避免了很多次重复比较）
     * 3. 判断是否是回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        /**
         * 注意：最小的回文子串的长度就是1，所以我们可以直接从长度等于2的子串开始比较
         */
        int maxLength = 1;
        int startIndex = 0;
        int len = s.length();
        /**
         * todo s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
         */
        char[] chars = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                /**
                 * todo 注意：这里也做了优化，如果目前子串的长度没有maxLength长度大的化，那么就没必要比较它了，因为比较了也是白比较
                 */
                if (j - i + 1 > maxLength && validatePalindrome2(chars, i, j)) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    /**
     * 判断是否是回文子串
     *
     * @param chars
     * @param left
     * @param right
     * @return
     */
    public static boolean validatePalindrome2(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome4(String s) {
        //特别条件判断
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLength = 1;
        int startIndex = 0;

        //定义状态转移数组
        int[][] dp = new int[len][len];

        //将字符串转换位CharArray，这样利于操作
        char[] chars = s.toCharArray();

        //对状态转移数组做初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        /**
         * 这里要特别注意填表的顺序
         */
        //开始填表
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = 0;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                //如果 dp[i][j]=1,就表示子串s[i...j]是回文串，此时记录回文串的长度和起始地址
                if (dp[i][j] == 1 && j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }
}
