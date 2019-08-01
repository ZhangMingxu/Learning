package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_07_binarytree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class TreeToSequence {
    public int[][] convert(TreeNode root) {
        // write code here

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        pre(pre, root);
        in(in, root);
        post(post, root);
        int[][] result = new int[3][pre.size()];
        for (int i = 0; i < pre.size(); i++) {
            result[0][i] = pre.get(i);
            result[1][i] = in.get(i);
            result[2][i] = post.get(i);
        }
        return result;
    }

    private void pre(List pre, TreeNode root) {
        pre.add(root.val);
        if (root.left!=null){
            pre(pre, root.left);
        }
        if (root.right!=null){
            pre(pre, root.right);
        }
    }

    private void in(List in, TreeNode root) {
        if (root.left!=null) {
            in(in, root.left);
        }
        in.add(root.val);
        if (root.right!=null) {
            in(in, root.right);
        }
    }

    private void post(List post, TreeNode root) {
        if (root.left!=null) {
            post(post, root.left);
        }
        if (root.right!=null) {
            post(post, root.right);
        }
        post.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        new TreeToSequence().convert(root);
    }
}

