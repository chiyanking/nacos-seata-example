package com.wangtk.mybatis.algorithm;


class ListNode {
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

public class TwoNumbers {
    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(9, new ListNode(9, new ListNode(1)));
        ListNode listNode2 = new ListNode(1);
        TwoNumbers twoNumbers = new TwoNumbers();
        ListNode listNode = twoNumbers.addTwoNumbers(listNode1, listNode2);
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode current = head;
        boolean carry = false;
        while (true) {
            if (l1 == null && l2 == null) {
                if (carry) {
                    current.next = new ListNode(1);
                }
                return head.next;
            }
            if (l1 == null && l2 != null) {
                int temp = l2.val + (carry ? 1 : 0);
                ListNode listNode = new ListNode(temp % 10);
                if (temp > 9) {
                    carry = true;
                } else {
                    carry = false;
                }
                current.next = listNode;
                current = current.next;
                l2 = l2.next;
                continue;
            }
            if (l1 != null && l2 == null) {
                int temp = l1.val + (carry ? 1 : 0);
                ListNode listNode = new ListNode(temp % 10);
                if (temp > 9) {
                    carry = true;
                } else {
                    carry = false;
                }
                current.next = listNode;
                current = current.next;
                l1 = l1.next;
                continue;
            }


            int temp = l1.val + l2.val + (carry ? 1 : 0);
            if (temp > 9) {
                ListNode listNode = new ListNode();
                listNode.val = temp % 10;
                current.next = listNode;
                current = current.next;
                carry = true;
            } else {
                ListNode listNode = new ListNode();
                listNode.val = temp;
                current.next = listNode;
                current = current.next;
                carry = false;
            }
            l1 = l1.next;
            l2 = l2.next;

        }


    }


}
