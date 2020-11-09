package com.wangtk.mybatis.algorithm;

public class TreeList {


    private static TreeNode root;

    public static void main(String[] args) {
        TreeList treeList = new TreeList();
        for (int i = 0; i < 10; i++) {
            treeList.add(i);
        }

        root.postOrder(root);

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public void postOrder(TreeNode node) {
            if (node != null) {
                if (node.left != null) {
                    node.left.postOrder(node.left);
                }
                if (node.right != null) {
                    node.right.postOrder(node.right);
                }
                System.out.println(node.val);
            }
        }
    }


    public void add(int value) {
        if (root == null) {
            root = new TreeNode(value);
            return;
        }
        TreeNode cur = root;
        TreeNode parent = null;
        boolean more = false;
        while (cur != null) {
            parent = cur;
            more = value > cur.val;
            if (more) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (more) {
            parent.right = new TreeNode(value);
        } else {
            parent.left = new TreeNode(value);
        }

    }


}
