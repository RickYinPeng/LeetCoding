package com.rickyin.coding.设计问题;

import java.util.*;

public class coding_384 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        Solution solution = new Solution(a);
        int[] ints = solution.shuffle_2();
        System.out.println(Arrays.toString(a));
    }
}

class Solution {

    private int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int tmp[] = nums.clone();
        //打乱数组,概率相同
        Map<Integer, Integer> seed = seed(tmp);
        int[] tmp2 = new int[nums.length];
        for (Integer index : seed.keySet()) {
            tmp2[index] = seed.get(index);
    }
        return tmp2;
    }

    /**
     * 种子生成器
     */
    public Map<Integer, Integer> seed(int[] deg) {
        //key: 随机数 value: 数组下标
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Random random = new Random();
        int flag = 0;
        while (flag < deg.length) {
            //生成随机数
            int r = random.nextInt(deg.length);
            //判断随机数是否都存在map的key中
            if (!map.containsKey(r)) {
                map.put(r, deg[flag]);
                flag++;
            }
        }
        return map;
    }

    private static Random random = new Random();
    public int[] shuffle_2() {
        int length = nums.length;
        System.out.println("length:"+length);
        for (int i = 0; i < length; i++) {
            // 获取一个待交换的数组索引
            int swapIndex = i + random.nextInt(length - i);
            System.out.println("swapIndex:"+swapIndex);
            // 交换
            int tmp = nums[i];
            nums[i] = nums[swapIndex];
            nums[swapIndex] = tmp;
        }
        return  nums;
    }


}
