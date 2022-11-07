package cn.wangtk.algorithm;

public class 两两交换链表中的节点 {


    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3, 4};


        ListNode list = ListNode.getList(array);

        swapPairs(list);

    }


    static public ListNode swapPairs(ListNode head) {


        ListNode pre = new ListNode();
        pre.next = head;

        ListNode dump = pre;

        ListNode cur = head;

        while (cur != null && cur.next != null) {
//          pre -> 1 2

            ListNode next = cur.next;

            ListNode tmp = next.next;
            next.next = cur;
            cur.next = tmp;
            pre.next = next;
            pre = cur;
            cur = tmp;

        }

        return dump.next;

    }

}