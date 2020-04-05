package com.rickyin.coding.设计问题;

import java.util.Arrays;
import java.util.Stack;

public class coding_155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        Stack<Integer> stack = minStack.getStack();
        System.out.println(stack.toString());
    }
}

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack = new Stack<Integer>();
    public MinStack() {
        stack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty()){
            minStack.push(x);
        }else {
            if(minStack.peek()>=x){
                minStack.push(x);
            }
        }
    }

    public void pop() {
        Integer pop = stack.pop();
        //TODO 在这犯了一个比较低级的错误: 类与类比较肯定要用 equals 啊,我之前使用了 == 导致这里一直是 false
        if(pop.equals(minStack.peek())){
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public Stack<Integer> getStack(){
        return minStack;
    }
}