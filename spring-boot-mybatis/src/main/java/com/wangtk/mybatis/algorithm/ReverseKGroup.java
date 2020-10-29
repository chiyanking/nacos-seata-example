package com.wangtk.mybatis.algorithm;

import java.util.Stack;


public class ReverseKGroup {
    public static class ListNode {
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

    public static void main(String[] args) {
        new ReverseKGroup().reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))), 2);
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 给你这个链表：pre->1->2->3->4->5
     * c->2       pp->2
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * 说明：
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode h = new ListNode();
        h.next = head;
        ListNode lastP = new ListNode();
        lastP.next = head;
        boolean first = true;
        Stack<ListNode> stack = new Stack();
        while (true) {
            ListNode p1 = lastP;
            for (int i = 0; i < k; i++) {
                if (p1.next != null) {
                    p1 = p1.next;
                    stack.push(p1);
                }
            }
            //另外一个数组第一个
            ListNode nh = p1.next;
            if (stack.size() != k) {
                break;
            }
            if (first) {
                h.next = p1;
                first = false;
            }
            //lastP->1->2
            //lastP->2->1
            ListNode p2 = new ListNode();
            while (stack.size() > 0) {
                ListNode pop = stack.pop();
                p2.next = pop;
                p2 = pop;
            }
            p2.next = nh;
            lastP.next = p1;
            lastP = p2;
        }
        return h.next;
    }

}
