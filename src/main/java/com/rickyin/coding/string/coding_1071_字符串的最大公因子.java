package com.rickyin.coding.string;

public class coding_1071_字符串的最大公因子 {
    public static void main(String[] args) {
        String s1 = "ABABAB";
        String s2 = "ABAB";
        System.out.println(gcdOfStrings(s1, s2));
    }

    public static String gcdOfStrings(String str1, String str2) {
        if(str1.equals(str2)){
            return str1;
        }
        //1.找出字符串中长度短的
        String MinStr = null;
        String MaxStr = null;
        if (str1.length() > str2.length()) {
            MinStr = str2;
            MaxStr = str1;
        } else {
            MinStr = str1;
            MaxStr = str2;
        }
        //2.字符串长度从1-str.length
        for (int i = 1; i <= MinStr.length(); i++) {
            for (int j = 0; j < MinStr.length()-i+1; j++) {
                String str = MinStr.substring(j, j + i);
                System.out.println(str);
                //判断是否能够除尽长度长的字符串s1
                if (MaxStr.split(str).length == 0 && MinStr.split(str).length ==0) {
                    System.out.println("=============");
                    if(i==1){
                        int number = getNumber(MaxStr.length(), MinStr.length());
                        return MinStr.substring(0, number);
                    }
                    int i1 = MinStr.length() / str.length();
                    System.out.println(i1);
                    if(i1 == 1){
                        return str;
                    }
                    String s = str;
                    if(i1 % 2==0){
                        int i2 = i1/2;
                        for (int k = 1; k <= i2; k++) {
                           String tmp = s+str;
                           if(MaxStr.split(tmp).length == 0 && MinStr.split(tmp).length ==0){
                               s = tmp;
                           }
                        }
                    }
                    return s;
                }
            }
        }
        return "";
    }
    private static int getNumber(int x, int y) {
        int big=x>y?x:y;
        int small=x<y?x:y;
        if (big%small==0){
            return small;
        }
        return getNumber(big%small,small);
    }
}
