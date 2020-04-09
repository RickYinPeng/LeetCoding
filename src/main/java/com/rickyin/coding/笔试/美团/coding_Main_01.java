package com.rickyin.coding.笔试.美团;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class coding_Main_01 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int day = sc.nextInt();
        String time = sc.next();
        int miniter = sc.nextInt();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date dateTime = format.parse(time);
        int day1 = dateTime.getDay();

        //System.out.println(dateTime);
        dateTime.setMinutes(dateTime.getMinutes()-miniter);
        //System.out.println(dateTime);
        int day2 = dateTime.getDay();

        if (day2 < day1) {
            System.out.println((day-(day1-day2))%7);
        }else {
            System.out.println(day);
        }
        System.out.println(format.format(dateTime));
    }
}
