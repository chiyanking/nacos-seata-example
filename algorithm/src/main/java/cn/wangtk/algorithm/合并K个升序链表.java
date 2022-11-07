package cn.wangtk.algorithm;

public class 合并K个升序链表 {

    public static void main(String[] args) {

        int[][] lists = new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] rest = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            rest[i] = ListNode.getList(lists[i]);
        }
        ListNode merge = mergeKLists(rest);

    }


    static public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null) {
            return null;
        }
        if (lists.length < 1) {
            return null;
        }


        ListNode dumy = lists[0];


        for (int i = 1; i < lists.length; i++) {

            dumy = merge(dumy, lists[i]);
        }
        return dumy;
    }

    static public ListNode merge(ListNode n1, ListNode n2) {

        ListNode dumy = new ListNode();
        ListNode cur = dumy;

        while (n1 != null && n2 != null) {

            if (n1.val <= n2.val) {
                cur.next = n1;
                cur = cur.next;
                n1 = n1.next;
            } else {
                cur.next = n2;
                cur = cur.next;
                n2 = n2.next;

            }
        }
        if (n1 != null) {
            cur.next = n1;
        }
        if (n2 != null) {
            cur.next = n2;
        }

        return dumy.next;
    }

}