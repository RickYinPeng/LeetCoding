package com.rickyin.coding.array;

import java.util.Arrays;
import java.util.Stack;

public class coding_912 {
    public static void main(String[] args) {
        int[] a = {5, 4, 7, 8, 9, 1, 2};
        int[] ints = sortArray_2(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 快排
     */
    public static int[] sortArray_2(int[] nums, int loww, int highh) {
        System.out.println("loww:"+loww);
        System.out.println("highh:"+highh);
        System.out.println("-----------------");
        if (loww < highh) {
            int flag = nums[loww];
            int low = loww;
            int high = highh;
            while (low < high) {
                //high指针跑
                while (high>low && nums[high]>flag){
                    high--;
                }
                nums[low] = nums[high];

                while (low < high && nums[low]<flag) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = flag;
            sortArray_2(nums, loww, low-1);
            sortArray_2(nums, low, highh);
        }
        return nums;
    }
}
