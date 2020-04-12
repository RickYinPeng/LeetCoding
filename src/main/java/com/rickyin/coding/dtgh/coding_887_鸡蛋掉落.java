package com.rickyin.coding.dtgh;

public class coding_887_鸡蛋掉落 {
    public static void main(String[] args) {
        System.out.println(superEggDrop(3, 14));
    }

    /**
     * 思路:二分搜索
     * 不能解
     */
    public static int superEggDrop(int K, int N) {
        return binarySearch(K, N, 0, 1, N);
    }

    private static int binarySearch(int k, int n, int count, int low, int high) {
        if (k == 1) {
            return n;
        }
        if (low >= high) {
            return count + 1;
        }
        int mid = (low + high) >> 1;
        count++;
        //我们需要找的是F，从F包括F往下扔鸡蛋都不会碎，从F往上扔鸡蛋都会碎
        //if(mid处的鸡蛋没有碎，我们就需要在往上找，直到找到碎的为止)
        int a = binarySearch(k, n, count, mid + 1, high);
        int b = binarySearch(k, n, count, low, mid - 1);
        return Math.max(a, b);
    }
}
