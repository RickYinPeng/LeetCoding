package com.rickyin.coding.string;

public class coding_6_Z字形变换 {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] list = new StringBuilder[1000];
        char[] chars = s.toCharArray();
        int curRow = 0;
        Boolean flag = false;
        for (char c : chars) {
            if (list[curRow] == null) {
                list[curRow] = new StringBuilder();
            }
            list[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1) {
                flag = !flag;
            }
            curRow += flag ? 1 : -1;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            stringBuilder.append(list[i] != null ? list[i] : "");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("A", 2));
    }
}
