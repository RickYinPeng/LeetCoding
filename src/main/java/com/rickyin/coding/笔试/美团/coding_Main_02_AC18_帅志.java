package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

public class coding_Main_02_AC18_帅志 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n+1];
        int[] end = new int[n+1];
        int index = 1;
        for (int i = 0; i < n; i++) {
            start[sc.nextInt()] = index;
            index++;
        }
        index = 1;
        for (int i = 0; i < n; i++) {
            end[sc.nextInt()] = index;
            index++;
        }
        int res = 0;
        for (int i = 0; i < end.length; i++) {
//            if(i<start[end[i]])
//                res++;
            if(end[i]<start[i]) res++;
            else if(end[i] > start[i]){
                for (int j = 0; j <= n ; j++) {
                    if(j ==i) continue;
                    if(start[j] < start[i] && end[j] > end[i]) res++;
                }
            }
        }
        System.out.println(res);
    }
}
