package com.rickyin.coding.string;

import java.util.*;

public class coding_151_翻转字符串里的单词 {
    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }

    /**
     * 暴力解法
     */
    public static String reverseWords(String s) {
        String s2 = s.trim();
        List<String> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s2.length(); i++) {
            if(s2.charAt(i) != ' '){
                sb.append(s2.charAt(i));
            }else {
                if(sb.length() != 0){
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        if(sb.length() != 0){
            list.add(sb.toString());
        }
        Collections.reverse(list);
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            if(i == list.size()-1){
                res += list.get(i);
            }else {
                res += list.get(i) + " ";
            }
        }
        return res;
    }
    /**
     * 技巧解法
     */
    public static String reverseWords_2(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
