package com.rickyin.coding.array;

import java.util.ArrayList;
import java.util.List;

public class coding_836_矩形重叠 {
    public static void main(String[] args) {
        int[] a = {7,8,13,15};
        int[] b = {10,8,12,20};
        //List<String> intersect = isIntersect(a, b);
        System.out.println(isRectangleOverlap(a, b));
    }

    /**
     * 思路:(垃圾思路,你TMD的题目要求都没有看清楚写锤子题，你能吃屎!!!)
     * 1.第一步判断两个矩形是否相交
     *  a.如果两个矩形相交,必定至少有一个矩形的一个点(x,y)在另外一个矩形内部[a,b,c,d],即 a<x<c && b<y<d
     *  b.如果相交返回相交的点List<String> 1,2
     * 2.第二步判断相交的面积是否是正方形
     *  a.如果相交一个点,找出另外一个矩形相交的点,算出面积是否为正方形
     *  b.如果相交两个点,找出另外一个矩形中的那个点的x的值,算出面积判断是否为正方形
     *  c.如果相交四个点,判断面积小的矩形是否为正方形
     */
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        List<String> intersectList = isIntersect(rec1, rec2);
        List<String> intersectList2 = isIntersect(rec2, rec1);
        if (intersectList.size() == 0) {
            return false;
        } else if (intersectList.size() == 1) {
            String s = intersectList.get(0);
            String[] split = s.split(",");
            int a1 = Integer.valueOf(split[0]);
            int b1 = Integer.valueOf(split[1]);

            String s2 = intersectList2.get(0);
            String[] split2 = s.split(",");
            int a2 = Integer.valueOf(split2[0]);
            int b2 = Integer.valueOf(split2[1]);

            if (Math.abs(a2 - a1) == Math.abs(b2 - b1)) {
                return true;
            }
        } else if ((intersectList.size() == 2 && intersectList2.size() == 0) || (intersectList.size() == 0 && intersectList2.size() == 2)) {
            if (intersectList.size() == 2) {
                if (rec2[0] <= rec1[0] && rec1[0] <= rec2[2]) {
                    int x = Math.abs(rec2[3] - rec2[1]);
                    int y = Math.abs(rec2[2] - rec1[0]);
                    if (x == y) {
                        return true;
                    }
                } else {
                    int x = Math.abs(rec2[3] - rec2[1]);
                    int y = Math.abs(rec1[2] - rec2[0]);
                    if (x == y) {
                        return true;
                    }
                }
            } else {
                int x = Math.abs(rec2[3] - rec2[1]);
                int y = Math.abs(rec2[2] - rec1[0]);
                if (x == y) {
                    return true;
                }
            }
        } else if (intersectList.size() == 4 || intersectList2.size() == 4) {
            if (intersectList.size() == 4) {
                int x = Math.abs(rec2[2] - rec2[0]);
                int y = Math.abs(rec2[3] - rec2[1]);
                if (x == y) {
                    return true;
                }
            }else {
                int x = Math.abs(rec1[2] - rec1[0]);
                int y = Math.abs(rec1[3] - rec1[1]);
                if (x == y) {
                    return true;
                }
            }
        }
        return false;
    }

    //判断是否有交集
    public static List<String> isIntersect(int[] rec1, int[] rec2) {
        List<String> list = new ArrayList<String>();
        //固定rec1
        int a = rec1[0];
        int b = rec1[1];
        int c = rec1[2];
        int d = rec1[3];

        //算出rec2的四个点 (a1,b1)、(a2,b2)、(a3,b3)、(a4,b4)
        int a1 = rec2[0];
        int b1 = rec2[1];
        if (a <= a1 && a1 <= c && b <= b1 && b1 <= d) {
            list.add(a1 + "," + b1);
        }

        int a2 = rec2[0];
        int b2 = rec2[3];
        if (a <= a2 && a2 <= c && b <= b2 && b2 <= d) {
            list.add(a2 + "," + b2);
        }

        int a3 = rec2[2];
        int b3 = rec2[3];
        if (a <= a3 && a3 <= c && b <= b3 && b3 <= d) {
            list.add(a3 + "," + b3);
        }

        int a4 = rec2[2];
        int b4 = rec2[1];
        if (a <= a4 && a4 <= c && b <= b4 && b4 <= d) {
            list.add(a4 + "," + b4);
        }
        return list;
    }

}
