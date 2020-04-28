package com.rickyin.coding.search;

public class coding_702_二分查找 {
    public static void main(String[] args) {

    }

    /**
     * 二分查找算法
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 排除法(减治法)写的二分查找算法
     */
    public int binarySearch2(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (num[left] != target) {
            return -1;
        }
        return left;
    }
}
