package com.rickyin.coding.BFS_DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 解题思路: https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class coding_22_括号生成 {
    public static void main(String[] args) {
    }

    /**
     * 借助DFS:深度优先遍历
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        //剪枝
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right < left) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 使用BFS解决
     */
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        Node head = new Node("", n, n);
        queue.add(head);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.right == 0 && poll.left == 0) {
                res.add(poll.curStr);
            }
            if (poll.left > 0) {
                queue.add(new Node(poll.curStr + "(", poll.left - 1, poll.right));
            }
            if (poll.right > 0) {
                queue.add(new Node(poll.curStr + ")", poll.left, poll.right - 1));
            }
        }
        return res;
    }

    static class Node {
        //当前得到的字符串
        private String curStr;
        //左边剩余的括号数
        private int left;
        //右边剩余的括号数
        private int right;

        public Node(String curStr, int left, int right) {
            this.curStr = curStr;
            this.left = left;
            this.right = right;
        }
    }
}
