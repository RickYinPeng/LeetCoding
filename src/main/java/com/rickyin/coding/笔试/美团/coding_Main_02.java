package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

public class coding_Main_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n + 1];
        int[] end = new int[n + 1];
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
            if (end[i] < start[i]) res++;
            else if (end[i] > start[i]) {
                //判断当前这个节点结束时，是否超过了开始的时候在他前面的节点
                int x = end[i];
                int s = start[i];
                for (int j = 0; j < start.length; j++) {
                    if(start[j] < s && end[j] > x){
                        res++;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
