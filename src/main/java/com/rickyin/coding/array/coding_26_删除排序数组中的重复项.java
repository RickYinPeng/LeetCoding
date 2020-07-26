package com.rickyin.coding.array;

public class coding_26_删除排序数组中的重复项 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        removeDuplicates(nums);
    }

    public static int removeDuplicates(int[] nums) {
        //边界条件处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            /*
            TODO 这是刚刚开始写的，然后简化了代码，如下所示--rick
            if (nums[i] == nums[index]) {
                nums[i] = nums[index];
            }else {
                index++;
                nums[i] = nums[index];
            }
            */
            if (nums[i] != nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    /**
     * 双指针法，刚刚开始自己也是这种思路，但是最后不知道为什么放弃了
     */
    public static int removeDuplicates2(int[] nums) {
        //边界条件处理
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int low = 0;
        int high = 0;
        while (high < nums.length) {
            /*
            TODO 刚开始分析写的代码，简化如下
            if(nums[low] == nums[high]){
                high++;
            }else {
                nums[low+1] = nums[high];
                low++;
                high++;
            }
            */
            if (nums[low] != nums[high]) {
                nums[low + 1] = nums[high];
                low++;

            }
            high++;
        }
        return low + 1;
    }


}
