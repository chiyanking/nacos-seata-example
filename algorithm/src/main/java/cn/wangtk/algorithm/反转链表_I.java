package cn.wangtk.algorithm;


public class 反转链表_I {


    // 备忘录，消除重叠子问题
    static int[][] memo;

    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 4, 5};

        ListNode dump = new ListNode();
        ListNode cur = dump;

        for (int i = 0; i < list.length; i++) {

            ListNode temp = new ListNode(list[i]);
            cur.next = temp;
            cur = temp;
        }
        System.out.println(dump.next);

        ListNode reverse = reverse(dump.next);
        System.out.println(reverse);

    }

    static public ListNode reverse(ListNode cur) {

        if (cur.next == null) {
            return cur;
        }
//     1 -> 2 -> 3 ->  4 -> 5 -> NULL
//                          ↑
//                          4
//                          ↑
//                          3
//                          ↑
//                          2
//                          ↑
//                          1
//
//                          5 -> 4 -> NULL
//                               ↑
//                               3
//                               ↑
//                               2
//                               ↑
//                               1

        ListNode head = reverse(cur.next);
        ListNode next = cur.next;
        next.next = cur;
        cur.next = null;

        return head;
    }
}