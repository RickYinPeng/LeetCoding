package com.rickyin.coding.string;

import java.util.HashMap;
import java.util.Map;

public class coding_13_Roman_to_Integer {
    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
    }

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<String, Integer> romanMap = new HashMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        char[] chars = s.toCharArray();
        Integer result = 0;
        Integer flag = 0;
        if(chars.length == 1){
            Character aChar = chars[0];
            return romanMap.get(aChar.toString());
        }
        for (int i = 0; i < chars.length - 1; i++) {
            Character one = chars[i];
            Character two = chars[i + 1];
            if (romanMap.get(one.toString()) >= romanMap.get(two.toString())) {
                result += romanMap.get(one.toString());
            } else {
                result += romanMap.get(two.toString()) - romanMap.get(one.toString());
                i = i + 1;
            }
            flag = i;
        }
        if (flag == chars.length - 2) {
            Character c = chars[flag + 1];
            result += romanMap.get(c.toString());
        }
        return result;
    }

}
