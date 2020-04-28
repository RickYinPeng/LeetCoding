package com.rickyin.coding.search;

public class coding_33_搜索旋转排序数组 {
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int search = search(nums, 0);
        System.out.println(search);
    }

    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        int left = 0;
        int right = len - 1;
        /**
         * 旋转数组: 将数组分成了两个有序区间(这里我们叫它左区间和右区间) 例: [4,5,6,7,8,1,2,3]
         * 其中mid的位置也会有所不同
         * 1.mid落在左区间
         *   如: [4,5,6,7(mid),8,1,2,3] 7所在位置是初始mid的位置
         *   如果mid落在左区间,并且 target < num[mid] 那么这个时候就需要在 [left,mid-1]区间中进行二分搜索
         *   如果mid落在左区间,并且 target > num[mid] 那么这个时候就需要在 [mid+1,right]区间内再判断新的mid落在哪个区间,进而判断新的区间中target落在哪个区间
         * 2.mid落在右区间
         *   如: [5,6,7,1(mid),2,3,4] 1所在位置是初始mid的位置
         *   如果mid落在右区间,并且 target > num[mid] 那么这个时候就需要在 [mid+1,right]区间进行二分搜索
         *   如果mid落在右区间,并且 target < num[mid] 那么这个时候就需要在 [left,mid-1]区间中再判断新的mid落在哪个区间,进而判断新的区间中target落在哪个区间
         */
        while (left < right) {
            //int mid = (left + right) >>> 1;
            int mid = left + ((right - left) >>> 1);
            if(nums[mid] == target){
                return mid;
            }
            //首先看mid落在哪个区间
            //mid落在左区间
            if(nums[mid] >= nums[left]){
                if(target < nums[mid] && target >= nums[left]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            //mid落在右区间
            }else {
                if(target > nums[mid] && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        if(nums[left] != target){
            left = -1;
        }
        return left;
    }
}
