package com.rickyin.coding.string;

import java.util.*;

public class coding_409_最长回文串 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }

    /**
     * 有点像对拼消耗
     *
     * @param s
     * @return
     */
    public static int longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int count = 0;
        /**
         * 之前使用List把人能做死，List做不出来事因为，你删除元素之后，它的后续元素会向前移动，而你的下标没有变
         */
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                count += 2;
                set.remove(chars[i]);
            } else {
                set.add(chars[i]);
            }
        }
        return set.size() > 0 ? count + 1 : count;
    }
}
