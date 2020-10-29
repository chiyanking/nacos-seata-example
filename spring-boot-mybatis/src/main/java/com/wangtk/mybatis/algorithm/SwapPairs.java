package com.wangtk.mybatis.algorithm;

import java.util.List;

public class SwapPairs {


    public static void main(String[] args) {


        SwapPairs sp = new SwapPairs();
        sp.swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))));
    }

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

    /**
     * [  1, 2, 3, 4]
     * p->c->n->nn
     * p->n->c->nn
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = head.next;

        ListNode p = new ListNode();
        p.next = head;

        while (p.next != null) {
            ListNode c = p.next;
            ListNode n = c.next;
            if (n == null) {
                break;
            }
            ListNode nnt = n.next;
            c.next = nnt;
            n.next = c;
            p.next = n;
            p = c;
        }
        return h;
    }
}
