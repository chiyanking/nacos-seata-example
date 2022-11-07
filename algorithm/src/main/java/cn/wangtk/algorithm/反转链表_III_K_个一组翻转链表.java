package cn.wangtk.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class 反转链表_III_K_个一组翻转链表 {


    public static void main(String[] args) {

        int[] array = new int[]{1, 2};


        ListNode list = ListNode.getList(array);

        reverseKGroup(list, 2);

    }

    static public ListNode reverseKGroup(ListNode head, int k) {

        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        ListNode dump = new ListNode();
        ListNode c = dump;


        while (cur != null) {
            stack.push(cur);
            cur = cur.next;

            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    c.next = stack.pop();
                    c.next.next = null;
                    c = c.next;
                }
            }
        }
        while (!stack.isEmpty()) {
            c.next = stack.pollLast();
            c.next.next = null;
            c = c.next;
        }
        return dump.next;
    }

}