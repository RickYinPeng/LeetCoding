package com.rickyin.coding.笔试.美团;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 给定一个时间，包括星期、时、分，然后再给一个分钟数n，输出给定时间往前n分钟的星期、时、分，时分都是2位，不足首位补0。
 */
public class coding_Main_01_AC100 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int day = sc.nextInt();
        String time = sc.next();
        int miniter = sc.nextInt();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date dateTime = format.parse(time);
        int day1 = dateTime.getDay();

        dateTime.setMinutes(dateTime.getMinutes() - miniter);
        int day2 = dateTime.getDay();

        if (day2 < day1) {
            if(((day-day1+day2)%7) == 0){
                System.out.println(7);
            }else {
                System.out.println((day - (day1 - day2)) % 7);
            }
        } else {
            System.out.println(day);
        }
        System.out.println(format.format(dateTime));
    }
}
