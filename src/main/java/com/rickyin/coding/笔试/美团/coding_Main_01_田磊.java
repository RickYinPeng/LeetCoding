package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

public class coding_Main_01_田磊 {
    private static int newWeek, newH, newM;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int week = sc.nextInt();
        String inputString = sc.next();
        int h = Integer.parseInt(inputString.split(":")[0]);
        int m = Integer.parseInt(inputString.split(":")[1]);
        int n = sc.nextInt();
        sc.close();
        fun(week, h, m, n);
        System.out.println(newWeek);
        System.out.printf("%02d:%2d\n",newH,newM);
    }

    private static void fun(int week, int h, int m, int n) {
        if(n<=0){
            newWeek = week;
            newH = h;
            newM = m;
            return;
        }
        newM = n % 60;
        newH = (n / 60) % 24;
        newWeek = (n/1440) %7;
        if(newM<=m){
            newM = m-newM;
        }else {
            if(h>=1){
                h--;
            }else {
                week--;
                h = 23;
            }
            newM = m+60-newM;
        }
        if (newH <= h){
            newH = h-newH;
        }else {
            week--;
            newH = h+24-newH;
        }
//        newWeek = week-newWeek%7;
//        if(newWeek ==0 ){
//            newWeek = 7;
//        }
        if(newWeek <= week){
            newWeek = week - newWeek;
        }else {
            newWeek = week+7 -newWeek;
        }
        if(newWeek ==0){
            newWeek = 7;
        }
    }
}
