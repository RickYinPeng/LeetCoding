package com.rickyin.coding.string;

public class coding_01_06_字符串压缩 {
    public static void main(String[] args) {
        System.out.println(compressString("aabcccccaaa"));
    }

    public static String compressString(String S) {
        if (S == null || S.length() <= 2) {
            return S;
        }
        char[] c = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        char tmp = c[0];
        int count = 1;
        /**
         * 使用 char[] chars = s.toCharArray(); 来提升效率
         */
        for (int i = 1; i < c.length; i++) {
            if(c[i] == c[i-1]){
                count++;
            }else {
                sb.append(tmp).append(count);
                count = 1;
                tmp = c[i];
            }
        }
        sb.append(tmp).append(count);
        return sb.toString().length()>S.length()?S:sb.toString();
    }
}
