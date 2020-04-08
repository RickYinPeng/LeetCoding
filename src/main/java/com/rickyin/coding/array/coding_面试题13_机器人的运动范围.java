package com.rickyin.coding.array;

import java.util.*;

/**
 * 剑指offer13题，BFS
 */
public class coding_面试题13_机器人的运动范围 {
    public static void main(String[] args) {
        System.out.println(movingCount(3, 2, 17));
    }

    public static int movingCount(int m, int n, int k) {
        //定义四个方向: 上下左右
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int count = 1;

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visted = new HashSet<>();

        Node head = new Node(0, 0);
        queue.add(head);
        visted.add(head);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int curX = poll.getX();
            int curY = poll.getY();
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if(nextX <0 || nextY <0 || nextX >= m || nextY >= n){
                    continue;
                }
                int gi = (nextX / 1) % 10;
                int si = (nextX / 10) % 10;
                int bi = (nextX / 100) % 10;

                int gj = (nextY / 1) % 10;
                int sj = (nextY / 10) % 10;
                int bj = (nextY / 100) % 10;
                if ((gi + si + bi + gj + sj + bj) <= k) {
                    Node node = new Node(nextX, nextY);
                    if (!visted.contains(node)) {
                        System.out.println("x:"+nextX);
                        System.out.println("y:"+nextY);
                        System.out.println("------------------");
                        queue.add(node);
                        visted.add(node);
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}