package com.rickyin.coding.array;

import java.util.HashMap;
import java.util.Map;

public class coding_169_多数元素 {
    public static void main(String[] args) {
        int[] x = {3, 2, 3};
        System.out.println(majorityElement(x));
    }

    /**
     * 自己的做法
     */
    public static int majorityElement_1(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        int length = nums.length / 2;
        System.out.println(length);
        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
            if (map.get(key) > length) {
                return key;
            }
        }
        return 0;
    }

    /**
     * 摩尔投票法: 对拼消耗
     */
    public static int majorityElement(int[] nums) {

        int cnt = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cnt == 0) {
                res = nums[i];
                cnt++;
            } else {
                if(res==nums[i]){
                    cnt++;
                }else {
                    cnt--;
                    if(cnt ==0){
                        res = nums[i];
                    }
                }
            }
        }
        return res;
    }
}
