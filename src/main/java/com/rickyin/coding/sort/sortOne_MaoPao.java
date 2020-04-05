package com.rickyin.coding.sort;

import java.util.Arrays;

public class sortOne_MaoPao {

    public static void main(String[] args) {
        int[] a = {3,6,1,5,76,7,1,3,6,8,2,4,6,78};
        int[] b = {1,2,3,4,5};
        sort(b);
    }

    /**
     * 冒泡排序
     */
    public static void sort(int[] a) {
        boolean flag = false;

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
            if(flag == false){
                System.out.println("。。。");
                break;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
