package com.xufree.learning.algorithm.nowcoder.jianzhioffer;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * @author zhangmingxu ON 11:27 2019-02-01
 **/
public class ReConstructBinaryTree {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return build(pre, in, 0, pre.length, 0, in.length);
    }

    private TreeNode build(int[] pre, int[] in, int preBegin, int preEnd, int inBegin, int inEnd) {
        if (preBegin > preEnd) {
            return null;
        }

        if (preBegin == preEnd) {
            if (preBegin >= 0 && preBegin < pre.length) {
                return new TreeNode(pre[preBegin]);
            } else {
                return null;
            }
        }
        int rootVal = pre[preBegin];
        TreeNode root = new TreeNode(rootVal);
        int rootIndexOfIn = indexOf(in, rootVal);
        int leftCount = rootIndexOfIn - inBegin;
        if (leftCount >= 1) {
            root.left = build(pre, in, preBegin + 1, preBegin + leftCount, inBegin, rootIndexOfIn - 1);
        }
        int rightCount = inEnd - rootIndexOfIn;
        if (rightCount >= 1) {
            root.right = build(pre, in, preBegin + leftCount + 1, preEnd, rootIndexOfIn + 1, inEnd);
        }
        return root;
    }

    private int indexOf(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        ReConstructBinaryTree solution = new ReConstructBinaryTree();
        TreeNode root = solution.reConstructBinaryTree(pre, in);
        System.out.println();
    }

    //  Definition for binary tree
    private class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int x) {
            val = x;
        }
    }
}
