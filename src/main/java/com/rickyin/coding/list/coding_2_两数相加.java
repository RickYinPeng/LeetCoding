package com.rickyin.coding.list;

import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.Queue;

public class coding_2_两数相加 {
    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);

        ListNode l2_9 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l2_9.next = l2;

        ListNode listNode = addTwoNumbers(l1_1, l2_9);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;

        //利用两个队列来实现
        Queue<Integer> queueL1 = new LinkedList<>();
        Queue<Integer> queueL2 = new LinkedList<>();
        while (l1 != null) {
            queueL1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            queueL2.add(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!queueL1.isEmpty() || !queueL2.isEmpty()) {
            int q1 = 0, q2 = 0;
            if (!queueL1.isEmpty() && !queueL2.isEmpty()) {
                q1 = queueL1.poll();
                q2 = queueL2.poll();
            } else if (!queueL1.isEmpty() && queueL2.isEmpty()) {
                q1 = queueL1.poll();
                q2 = 0;
            } else if (queueL1.isEmpty() && !queueL2.isEmpty()) {
                q1 = 0;
                q2 = queueL2.poll();
            }
            int result = (q1 + q2 + carry) % 10;
            carry = (q1 + q2 + carry) >= 10 ? 1 : 0;
            //将result的值放入链表中，如果链表为空，则当前result为head，若不为空，则添加到链表后面
            ListNode listNode = new ListNode(result);
            if (head == null && tail == null) {
                head = listNode;
                tail = listNode;
            } else if (tail != null) {
                tail.next = listNode;
                tail = listNode;
            }
        }
        if(carry == 1){
            ListNode listNode = new ListNode(carry);
            tail.next = listNode;
            tail = listNode;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
