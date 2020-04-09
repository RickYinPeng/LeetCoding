package com.rickyin.coding.stack;

import java.util.Stack;

/**
 * 思路: 栈
 */
public class coding_20_有效的括号 {
    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        if (s.length() == 1) {
            return false;
        }
        if(s.trim().length() == 0){
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            //如果栈为空，那么进栈
            if (stack.isEmpty()) {
                stack.push(chars[i]);
                continue;
            }
            //如果栈不为空，那么判断栈顶元素与当前元素是否匹配
            char head = stack.peek();
            Character matchChar = matchChar(head);
            if(matchChar == null){
                return false;
            }
            if(chars[i] == matchChar){
                stack.pop();
            }else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

    public static Character matchChar(char head) {
        if (head == '(') {
            return ')';
        } else if (head == '{') {
            return '}';
        } else if (head == '[') {
            return ']';
        } else {
            return null;
        }
    }
}
