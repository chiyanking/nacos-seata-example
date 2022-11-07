package cn.wangtk.algorithm;

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

    static ListNode getList(int[] list) {

        ListNode dump = new ListNode();
        ListNode cur = dump;

        for (int i = 0; i < list.length; i++) {
            ListNode temp = new ListNode(list[i]);
            cur.next = temp;
            cur = temp;
        }
        return dump.next;
    }

}
