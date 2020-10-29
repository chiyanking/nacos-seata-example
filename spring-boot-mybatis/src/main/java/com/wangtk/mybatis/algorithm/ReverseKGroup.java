package com.wangtk.mybatis.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
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
            for (int i = 0; i < k && p1.next != null; i++) {
                p1 = p1.next;
                stack.push(p1);
            }
            //另外一个数组第一个
            ListNode nextHead = p1.next;
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
            p2.next = nextHead;
            lastP.next = p1;
            lastP = p2;
        }
        return h.next;
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        Stack<ListNode> stack = new Stack();
        while (true) {
            ListNode p1 = head;
            for (int i = 0; i < k && p1.next != null; i++) {
                stack.push(p1);
                p1 = p1.next;
            }
            //至此 p1 是另外一个数组的第一个
            if (stack.size() != k) {
                p.next = head;
                break;
            }
            while (stack.size() > 0) {
                ListNode pop = stack.pop();
                p.next = pop;
                p = pop;
            }
            p.next = p1;
            head = p1;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (true) {
            int count = 0;
            ListNode tmp = head;
            while (tmp != null && count < k) {
                stack.add(tmp);
                tmp = tmp.next;
                count++;
            }
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                p.next = stack.pollLast();
                p = p.next;
            }
            p.next = tmp;
            head = tmp;
        }
        return dummy.next;
    }

}
