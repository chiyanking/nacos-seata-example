package cn.wangtk.algorithm;

/**
 * 300.最长递增子序列
 * 674.最长连续递增序列
 * 718.最长重复子数组
 * 1143.最长公共子序列
 * 1035.不相交的线
 * 53.最大子序和
 * 392.判断子序列
 * 115.不同的子序列
 * 583.两个字符串的删除操作
 * 72.编辑距离
 * 为了绝杀编辑距离，我做了三步铺垫，你都知道么？
 * 647.回文子串
 * 516.最长回文子序列
 * 动态规划总结篇
 */


public class 反转链表_II {

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
//        reverseBetween(dump.next, 2, 4);

        ListNode listNode = reverseBetween(dump.next, 2, 4);

        System.out.println(listNode);

    }

    static public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dumy = new ListNode();
        dumy.next = head;
        ListNode pre = dumy;
        ListNode cur = head;

        int i = 1;
        while (i < left) {
            pre = cur;
            cur = cur.next;
            i++;
        }

        ListNode reverseHead = reverseN(cur, new ListNode(), right - left);
        pre.next = reverseHead;

        return dumy.next;
    }

//        cur=1               last
//         ↓                   ↓
//    1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
//                                  ↑
//                               successor

    static ListNode reverseN(ListNode cur, ListNode successor, int n) {
        if (n == 0) {
            successor.next = cur.next;
            return cur;
        }
        ListNode last = reverseN(cur.next, successor, n - 1);
        cur.next.next = cur;
        cur.next = successor.next;
        return last;
    }

    static public ListNode reverseBetween_pointer(ListNode head, int left, int right) {
        ListNode dump = new ListNode();
        dump.next = head;
        ListNode pre = dump;
        ListNode cur = dump.next;
        int i = 0;

        while (left != i + 1) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        ListNode leftPre = pre;
        ListNode rightP = cur;

        while (cur != null && right != i) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            i++;
        }
        rightP.next = cur;
        leftPre.next = pre;

        return dump.next;
    }
}