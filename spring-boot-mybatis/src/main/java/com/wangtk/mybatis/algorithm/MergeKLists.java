package com.wangtk.mybatis.algorithm;

public class MergeKLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * 示例 1：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     * 输入：lists = [[]]
     * 输出：[]
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = lists[0];

        for (int i = 1; i < lists.length; i++) {
            head = mergeList(head, lists[i]);
        }
        return head;
    }

    public ListNode mergeList(ListNode node1, ListNode node2) {

        ListNode head = new ListNode();
        ListNode cur = head;

        ListNode cur1 = node1;
        ListNode cur2 = node2;
        while (true) {
            if (cur1 == null) {
                if (cur2 != null) {
                    cur.next = cur2;
                }
                break;
            }
            if (cur2 == null) {
                if (cur1 != null) {
                    cur.next = cur1;
                }
                break;
            }
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur = cur.next;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur = cur.next;
                cur2 = cur2.next;
            }
        }

        return head.next;
    }
}
