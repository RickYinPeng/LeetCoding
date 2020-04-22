package com.rickyin.coding.array;

public class coding_1248_统计优美子数组 {
    public static void main(String[] args) {
        int[] a = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        System.out.println(numberOfSubarrays(a, 2));
    }

    /**
     * 暴力循环解决，但是超时了
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        int count = 0;
        for (int i = k; i <= nums.length; i++) {
            int range = i;
            for (int j = 0; j <= nums.length - range; j++) {
                int start = j;
                int end = j + range - 1;
                int jishuNum = 0;
                for (int l = start; l <= end; l++) {
                    if (nums[l] % 2 != 0) {
                        jishuNum++;
                    }
                }
                if (jishuNum == k) {
                    count++;
                } else if (k - jishuNum > 1) {
                    j = end;
                }
            }
        }
        return count;
    }

    /**
     * https://leetcode-cn.com/problems/count-number-of-nice-subarrays/solution/java-hua-dong-chuang-kou-xiang-jie-zhi-xing-yong-s/
     * 思路:滑动窗口
     * 第一步：普遍规律
     * 由于只需要关注奇数的个数，故：
     * 1.找到每种情况下，最短的符合要求子数组（此时子数组的左右边界一定都是奇数）
     * 2.向左向右延展，直至碰到奇数停止
     * 例: 数组: [0,1,1,0,1,0,0,1,0] 其中0代表偶数,1代表奇数, k = 3
     * 1.找到每种情况下，最短的符合要求子数组
     * [1,1,0,1]对应下标【1,4】 1下标至4下标处数组中的元素正好有 k 个奇数
     * [1,0,1,0,0,1]对应下标【2,7】 2下标至7下标处数组中的元素正好有 k 个奇数
     * 2.向左向右延展，直至碰到奇数停止
     * 对于[1,1,0,1]这个子数组来说,我们向左右延申,此时向左延展有2种可能(不延展，延展1位),向右有3种可能，故总共有2*3=6种可能；
     * 对于[1,0,1,0,0,1]这个子数组来说,我们向左边不能延申了,因为左边紧接着就是一个奇数,所以向左只能是不延伸这一种可能,向右有2种可能,故总共有1*2=2种可能；
     */
    public static int numberOfSubarrays2(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int oddCnt = 0;
        int res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++; 
            }
            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;

                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }
        }
        return res;
    }

    /**
     * 思路:前缀和(这个不是很理解 prefixCnt[0] = 1;)
     * 1.计算前缀和数组 arr: 遍历原数组，每遍历一个元素，计算当前的前缀和（即到当前元素为止，数组中有多少个奇数）;
     * 2.对上述前缀和数组，双重循环统计 arr[j] - arr[i] == k 的个数，这样做是 O(N^2)会超时
     * 3.优化:
     *      因此，我们可以像「1. 两数之和」那样使用 HashMap 优化到 O(N),键是「前缀和」,值是「前缀和的个数」
     *      （下面代码中具体使用的是 int[] prefixCnt 数组，下标是「前缀和」，值是「前缀和的个数」）,
     *      因此我们可以遍历原数组，每遍历到一个元素，计算当前的前缀和 sum，就在 res 中累加上前缀和为 sum - k 的个数。
     */
    public static int numberOfSubarrays3(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数。
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到 prefixCnt 数组中，
        // 并且在 res 中累加上与当前前缀和差值为 k 的前缀和的个数。
        int res = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num & 1;
            prefixCnt[sum]++;
            if(sum >= k){
                res += prefixCnt[sum -k];
            }
        }
        return res;
    }
}
