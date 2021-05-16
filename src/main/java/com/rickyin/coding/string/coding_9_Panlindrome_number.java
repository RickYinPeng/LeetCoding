package com.rickyin.coding.string;

public class coding_9_Panlindrome_number {
    public static void main(String[] args) {
        System.out.println(isPalindrome(9));
    }

    public static boolean isPalindrome(int x){
        if(x < 0){
            return false;
        }
        int tmp = x;
        int result = 0;
        while (x != 0){
            result =  result * 10 + x % 10;
            x = x / 10;
        }
        System.out.println(x);
        System.out.println(result);
        return result == tmp;
    }
}
