package com.rickyin.coding.array;

/**
 * TODO 将数组分成和相等的三个部分
 *  1.如果要三等分,那必须能被3整除(x+x+x=3x)
 *  2.双指针前后向中间逼近，不用考虑中间那段怎么分，只要左右两段累加和等于3等分的数值，中间剩的那段也就找到了
 */
public class coding_1013_将数组分成和相等的三个部分 {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int i : A) {
            sum += i;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int left = 1;
        int right = A.length - 2;
        int avg = sum / 3;
        int count = 0;
        int curSum = 0;
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            if(curSum == avg){
                //说明找到了一段
                count++;
                curSum = 0;
            }
        }
        return count == 3 || (count > 3 && avg == 0);
    }
}
