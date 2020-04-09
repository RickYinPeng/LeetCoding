package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

public class coding_Main_03_AC100_帅志 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int k = sc.nextInt();
        long x = n;
        boolean rtFlag = false;
        while (true){
            int kp = 0;
            long sum = 0;
            double item = Math.floor(x/(Math.pow(k,kp)));
            while (item>0){
                sum += item;
                kp++;
                item = Math.floor(x/(Math.pow(k,kp)));
            }
            if(sum >= n){
                if(rtFlag){
                    System.out.println(x);
                    return;
                }
                x -= k;
            }else {
                rtFlag = true;
                x++;
            }
        }
    }
}
