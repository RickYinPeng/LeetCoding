package com.rickyin.coding.list;

public class coding_876 {
    /**
     * 双指针法(也叫快慢指针)
     *
     * @param head (头节点是存储数据的)
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode i = head;
        ListNode j = head;
        ListNode tmp = head;
        while (j != null && j.next != null) {
            i = tmp.next;
            j = j.next.next;
            tmp = tmp.next;
        }
        return i;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
