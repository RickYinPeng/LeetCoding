package com.rickyin.coding.array;

/**
 * 求最大公约数
 */
public class coding_914_卡牌分组 {
    public static void main(String[] args) {
        int[] a = {1, 1};
        System.out.println(hasGroupsSizeX(a));
    }

    /**
     * 垃圾
     */
    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck.length == 1) {
            return false;
        }
        int[] bitMap = new int[10001];
        for (int i = 0; i < deck.length; i++) {
            int res = deck[i];
            bitMap[res] = ++bitMap[res];
        }
        int cur = 0;
        int count = 0;
        int ress = 0;
        for (int i = 0; i < bitMap.length; i++) {
            int res = bitMap[i];
            if (res != 0) {
                if (cur == 0) {
                    cur = res;
                    count++;
                } else {
                    if (cur == res) {
                        count++;
                    } else {
                        if (res % cur == 0) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
                if (count == 1) {
                    ress = res;
                }
            }
        }
        if (count >= 2) {
            return true;
        }
        if (count == 1) {
            return ress >= 2 ? true : false;
        }
        return count >= 2 ? true : false;
    }
}
