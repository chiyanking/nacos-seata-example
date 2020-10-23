package com.wangtk.mybatis.algorithm;

public class RemoveNthFromEnd {


    static class ListNode {
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

        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        removeNthFromEnd.removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))), 6);
        removeNthFromEnd.removeNthFromEnd(new ListNode(1), 1);


    }

    /***
     *        i j
     *  * * * * *
     *  0 1 2 3 4
     *
     * *
     * s f
     *
     * * *
     * s f
     *
     *  1 2 3 4 5 6
     *      i   i
     *
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }


}
