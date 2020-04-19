package com.rickyin.coding.笔试.美团.面试;

import java.util.HashMap;
import java.util.Map;

public class coding_Main_01_跳台阶 {
    static Map<Integer, Integer> map = new HashMap<>();

    public static int taijie(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int res = Math.max(taijie(n - 1), taijie(n - 2));
        map.put(n, res);
        return res;
    }
}
