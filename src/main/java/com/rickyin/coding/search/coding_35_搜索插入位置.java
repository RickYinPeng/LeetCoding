package com.rickyin.coding.search;

public class coding_35_搜索插入位置 {
    public static void main(String[] args) {
        //[1,3,5,7,9,11]
        int[] num = new int[]{1,3,5,7,9,11};
        int index = binarySearch3(num, 7); // 返回值应该为7的下标3
        System.out.println(index);

        int[] num2 = new int[]{1,7,7,7,7,7,7};
        int index2 = binarySearch4(num2, 7);
        System.out.println(index2);
    }

    /**
     * 思路: 二分查找算法解本题
     */
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if(target > nums[len-1]){
            return len;
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
        return left;
    }

    /**
     * 使用排除法(减治法)写的二分查找算法解决本题
     */
    public int searchInsert2(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (target > nums[len - 1]) {
            return len;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            // 当nums[mid] 严格小于目标元素时,不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间 [mid+1,right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }



    /**
     * 通常的二分查找算法
     */
    public int binarySearch(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (num[mid] == target) {
                return mid;
            }
            if (num[mid] < target) {
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
            int mid = (left + right) >>> 1;
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

    /**
     * 查找最后一个小于等于给定值的元素
     * 例: [1,3,5,7,9,11] target=8,最后一个小于等于给定值的元素就是 7(下标=3)
     */
    public static int binarySearch3(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;

            //思考 num[mid] 什么时候不是解
            //当 num[mid]<=target 时是解,所以当 num[mid]>target 时不是解
            if (num[mid] > target) {
                right = mid - 1;
            } else {
                //因为mid被分到了左边区间,所以需要向上取整 int mid = (left + right + 1) >>> 1;
                left = mid;
            }

            /**
             * 下面这种写法,是我自己尝试做的 查找最后一个小于给定值的元素(没有等于)
             */
            /*
            if(num[mid] >= target){
                right = mid-1;
            }else {
                left = mid;
            }
            */
        }
        return left;
    }

    /**
     * 查找第一个值等于给定值的元素(数组中有重复元素)
     * 例: [1,3,5,7,7,11] target=7,第一个值等于给定值的元素就是 7(下标=3)
     */
    public static int binarySearch4(int[] num, int target) {
        if (num.length == 0) {
            return -1;
        }
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;

            //思考 num[mid] 什么时候不是解
            //当 num[mid]=target && num[mid-1]=target时不是解
            if (num[mid] == target && num[mid - 1] == target) {
                right = mid - 1;
            } else if (num[mid] < target) {
                left = mid + 1;
            }else{
                right = mid;
            }

        }
        return left;
    }

}
