package com.rickyin.coding.list;

public class coding_21_合并两个有序链表 {
    public static void main(String[] args) {
        //1->2->4, 1->3->4
        //1->1->2->3->4->4
        ListNode One = new ListNode(-9);
        ListNode Two = new ListNode(3);
        //ListNode Four = new ListNode(4);
        One.next = Two;
        //Two.next = Four;

        ListNode One2 = new ListNode(5);
        ListNode Three = new ListNode(7);
        //ListNode Four2 = new ListNode(4);
        One2.next = Three;
        //Three.next = Four2;
        ListNode listNode = mergeTwoLists(One, One2);
        //ListNode listNode = mergeTwoLists(null, new ListNode(0));
        System.out.println(listNode.val);
    }

    /**
     * 思路: 循环判断两个链表的元素,小的往结果链表里面添加
     *     ①:获取链表1和链表2的两个值 v1 和 v2
     *     ②:如果 v1 > v2(v2小) 所以应该将 v2 加入结果链表,然后让 l2 向后移动 l2 = l2.next;
     *     ③:如果 v1 < v2(v1小) 所以应该将 v1 加入结果链表,然后让 l1 向后移动 l1 = l1.next;
     * 注意: 这里需要两个辅助链表变量,一个用于返回结果(head),一个用来当作结果链表(resList),其中head指向resList的头部,因为需要循环往结果链表
     *      后面添加元素( resList.next = new ListNode(v2); ),所以添加完成之后要让 resList = resList.next; 所以不能直接返回resList
     *      (因为resList指向的不是链表头部,因为它在循环中不断变化),所以需要head来指向resList的头部
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode resList = null;
        ListNode head = null;
        //结束的条件是l1或l2有一个为null
        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 > v2) {
                if(resList == null){
                    resList = new ListNode(v2);
                    head = resList;
                }else {
                    resList.next = new ListNode(v2);
                    resList = resList.next;
                }
                l2 = l2.next;
            } else {
                if(resList == null) {
                    resList = new ListNode(v1);
                    head = resList;
                }else {
                    resList.next = new ListNode(v1);
                    resList = resList.next;
                }
                l1 = l1.next;
            }
        }
        if(l1 != null){
            if(resList == null){
                resList = l1;
                head = resList;
            }else {
                resList.next = l1;
            }
        }else if(l2 != null){
            if(resList == null){
                resList = l2;
                head = resList;
            }else {
                resList.next = l2;
            }
        }
        return head;
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
