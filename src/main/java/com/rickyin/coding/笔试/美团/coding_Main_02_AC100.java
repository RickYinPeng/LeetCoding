package com.rickyin.coding.笔试.美团;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 作者：albao
 * 链接：https://www.nowcoder.com/discuss/404230?type=post&order=time&pos=&page=1
 * 来源：牛客网
 *
 * 有n个运动员，给2个长为n的数组，分别为出发时各个位置上运动员的编号和到达时运动员的编号，那么问到达时超过了别人的运动员有几个。
 * 例子：
 * 5
 * [5, 3, 1, 4, 2]
 * [2, 4, 5, 1, 3]
 * 答案：
 * 3
 * 第一行为出发时的编号，第二行为到达时的编号，上例中只有3和5没有超过别人。
 */
public class coding_Main_02_AC100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            map.put(a[i], i);
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        int min = 0;
        int ans = 0;
        //从末尾开始
        for (int i = n - 1; i >= 0; i--) {
            //如果i== n-1 那么 min = 最后一个到达的序号他的起始起跑位置
            if (i == n - 1) {
                min = map.get(b[i]);
                continue;
            }
            //如果当前序号的起始位置大于min，那么ans++
            if (map.get(b[i]) > min) {
                ans++;
            }
            min = Math.min(min, map.get(b[i]));
        }
        System.out.println(ans);
    }
}
