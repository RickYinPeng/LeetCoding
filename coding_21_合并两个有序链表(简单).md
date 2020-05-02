[toc]

### 题目描述

将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

- 示例：
```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

### 题目分析

1. 很简单就是将两个有序的链表合并成一个有序的链表
2. 暴力法(本人就是这样)
3. 递归法
    1. 终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
    2. 返回值：<font size = 3 color = red>每一层调用都返回排序好的链表头</font>
    3. 本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
    4. O(m+n)，mm 为 l1的长度，nn 为 l2 的长度
4. 迭代法

### 题目解法

#### 暴力解法

```java
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
                if (resList == null) {
                    resList = new ListNode(v2);
                    head = resList;
                } else {
                    resList.next = new ListNode(v2);
                    resList = resList.next;
                }
                l2 = l2.next;
            } else {
                if (resList == null) {
                    resList = new ListNode(v1);
                    head = resList;
                } else {
                    resList.next = new ListNode(v1);
                    resList = resList.next;
                }
                l1 = l1.next;
            }
        }
        if (l1 != null) {
            if (resList == null) {
                resList = l1;
                head = resList;
            } else {
                resList.next = l1;
            }
        } else if (l2 != null) {
            if (resList == null) {
                resList = l2;
                head = resList;
            } else {
                resList.next = l2;
            }
        }
        return head;
    }
```

#### 递归法

图解:https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/hua-jie-suan-fa-21-he-bing-liang-ge-you-xu-lian-bi/

```java
/**
 * 递归解法
 * 终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
 * 返回值：每一层调用都返回排序好的链表头
 * 本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
 * O(m+n)，mm 为 l1的长度，nn 为 l2 的长度
 */
public static ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
    if (l1 == null) {
        return l2;
    } else if (l2 == null) {
        return l1;
    } else if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```
#### 递归程序图解

![image](http://tie.027cgb.com/606599/LeetCoding/3.png)

