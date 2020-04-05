package com.rickyin.coding.array;

import java.util.Arrays;

public class coding_40 {
    public static void main(String[] args) {
        coding_40 c = new coding_40();
        int[] a = {3,2,1,4};
        int k = 2;
        int[] leastNumbers_2 = c.getLeastNumbers_2(a, k);
        System.out.println(leastNumbers_2.length);
    }

    /**
     * TopK问题：求前K大/前K小/第K大/第K小
     * 初级解法，排序
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    /**
     * TODO 快排切分法(二切法),其实就是使用交换,在数据结构书上的那种快速排序是使用的挖坑方法(一边挖,一边填)
     */
    public int[] getLeastNumbers_2(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        return quickSearch(arr, 0, arr.length - 1,k-1);
    }

    private int[] quickSearch(int[] nums, int l, int h,int k) {
        int result = partiton(nums, l, h);
        if(result == k){
            return Arrays.copyOf(nums, result+1);
        }
        return result>k?quickSearch(nums, l, result-1,k):quickSearch(nums, result+1, h,k);
    }

    private int partiton(int[] nums,int l,int h){
        int v = nums[l];
        int low = l+1;
        int high = h;
        //结束条件是 low > high(high+1=low)
        while (low <= high) {
            while (low <= high && nums[low] <= v) {
                low++;
            }
            while (low <= high && nums[high] > v) {
                high--;
            }
            if (low < high) {
                int tmp = nums[low];
                nums[low] = nums[high];
                nums[high] = tmp;
                low++;
                high--;
            }
        }
        int tmp = nums[l];
        nums[l] = nums[high];
        nums[high] = tmp;
        return high;
    }
}
