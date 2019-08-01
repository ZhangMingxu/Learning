package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_06_binaryserch;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}

public class CountNodes {
    public int count(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public int bs(TreeNode node, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, l + 1) == h) {
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }

    public int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
