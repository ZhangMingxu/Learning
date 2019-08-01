package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_03_string;

/**
 * Created by 张明旭 on 2017/6/10.
 * 对于两棵彼此独立的二叉树A和B，请编写一个高效算法，检查A中是否存在一棵子树与B树的拓扑结构完全相同。
 * <p>
 * 给定两棵二叉树的头结点A和B，请返回一个bool值，代表A中是否存在一棵同构于B的子树
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class IdenticalTree {
    public boolean chkIdentical(TreeNode A, TreeNode B) {
        // write code here
        StringBuffer As = new StringBuffer();
        StringBuffer Bs = new StringBuffer();
        inOrder(As, A);
        inOrder(Bs, B);

        return As.toString().contains(Bs.toString());
    }

    private void inOrder(StringBuffer s, TreeNode root) {
        if (root.left != null) {
            inOrder(s, root.left);
        }
        s.append(root.val);
        if (root.right != null) {
            inOrder(s, root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);
        TreeNode B = new TreeNode(1);
        new IdenticalTree().chkIdentical(A,B);
    }
}
