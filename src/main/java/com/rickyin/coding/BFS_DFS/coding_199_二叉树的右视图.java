package com.rickyin.coding.BFS_DFS;

import java.util.*;

public class coding_199_二叉树的右视图 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);
        root.left = rootLeft;
        root.right = rootRight;
        TreeNode rootLeftRight = new TreeNode(5);
        rootLeft.right = rootLeftRight;
        TreeNode rootRightRight = new TreeNode(4);
        rootRight.right = rootRightRight;
        rightSideView(root);
    }

    /**
     * 思路:BFS
     * 每次记录下每一层BFS后最后一个元素,这里需要注意,要在外面保存队列的Size,因为队列的Size随着队列的变化在变化
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                if (i == size-1) {
                    //list.add(treeNode.val);
                    System.out.println(treeNode.val);
                }
            }
        }
        return list;
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
