package com.wangtk.mybatis.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * *
 */
public class ZigzagLevelOrder {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Map<Integer, List<Integer>> res = new HashMap<>();
        travelTree(root, res, 0);
        return new ArrayList<>(res.values());
    }


    /**
     *      *
     *    *  *
     *  * * * *
     */

    void travelTree(TreeNode root, List<List<Integer>> res) {
        int i = 0;
        while (root != null) {
            if (i < res.size()) {
                res.add(new ArrayList<>());
            }
            List<Integer> integers = res.get(i);
            integers.add(root.val);
            root.left=root;
        }
    }


    void travelTree(TreeNode root, Map<Integer, List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        List<Integer> integers = res.get(level);
        if (integers == null) {
            integers = new ArrayList();
            res.put(level, integers);
        }

        if (level % 2 == 0) {
            integers.add(root.val);
        } else {
            integers.add(0, root.val);

        }

        travelTree(root.left, res, level + 1);
        travelTree(root.right, res, level + 1);

    }
}
