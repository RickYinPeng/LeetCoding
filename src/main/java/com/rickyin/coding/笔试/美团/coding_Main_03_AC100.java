package com.rickyin.coding.笔试.美团;

import java.util.Scanner;

/**
 * 作者：albao
 * 链接：https://www.nowcoder.com/discuss/404230?type=post&order=time&pos=&page=1
 * 来源：牛客网
 *
 * 给n和k，输出最小的x使得
 * [x] + [x/k] + [x/k^2] + ...
 * [x]为向下取整，总有一个时刻[x/k^m]==0，因此求使得前面m项和大于n的最小x。
 * 思路：x从1开始会超时，我的解法比较暴力，只是在x的开始位置求了一下，从[n/k]*(k-1)开始，直到n,然后就AC了。。
 */
public class coding_Main_03_AC100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n=sc.nextLong();
        long k=sc.nextLong();
        long l=1;
        long r=n+1;
        while(l<r){
            long mid=(l+r)/2;

            if(judge(mid,n,k))r=mid;
            else l=mid+1;
        }
        System.out.println(l);
    }

    private static boolean judge(long x,long n,long k){
        long tmp=x;
        while(n>0){
            n-=x;
            x/=k;
            if(x<=0)break;
        }

        return n<=0;
    }
}
