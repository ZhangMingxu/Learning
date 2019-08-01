package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_07_binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeToSequenceNotRecursion {
    public int[][] convert(TreeNode root) {
        // write code here
        List<Integer> pre = pre(root);
        List<Integer> in = in(root);
        List<Integer> post = post(root);
        int[][] result = new int[3][pre.size()];
        for (int i = 0; i < pre.size(); i++) {
            result[0][i] = pre.get(i);
            result[1][i] = in.get(i);
            result[2][i] = post.get(i);
        }
        return result;

    }
    private List pre(TreeNode root){
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            result.add(root.val);
            if (root.right!=null){
                stack.push(root.right);
            }
            if (root.left!=null){
                stack.push(root.left);
            }
        }
        return result;
    }
    private List<Integer> in(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root!=null){
            if (root!=null){
                stack.push(root);
                root=root.left;
            }else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }
    private List<Integer> post(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur = null;
        stack1.push(root);
        while(!stack1.isEmpty()){
            cur = stack1.pop();
            stack2.push(cur);
            if (cur.left!=null){
                stack1.push(cur.left);
            }
            if (cur.right!=null){
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()){
            result.add(stack2.pop().val);
        }
        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        new TreeToSequenceNotRecursion().convert(root);
    }
}
