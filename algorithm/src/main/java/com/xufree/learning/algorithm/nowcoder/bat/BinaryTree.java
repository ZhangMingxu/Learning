package com.xufree.learning.algorithm.nowcoder.bat;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhangmingxu ON 11:14 2019-07-24
 **/
public class BinaryTree {
    public static void main(String[] args) {
        Node head = build();
//        List<Integer> layerErgodic = layerErgodic(head);
//        System.out.println(layerErgodic);

        printLayerErgodicWithNewLine(head);
    }

    /**
     * 构建一棵二叉树
     *                  1
     *          2               3
     *      4        5       6       7
     *
     * @return 头节点
     */
    private static Node build() {
        Node _4 = Node.buildLeaf(4);
        Node _5 = Node.buildLeaf(5);
        Node _6 = Node.buildLeaf(6);
        Node _7 = Node.buildLeaf(7);
        Node _2 = new Node(2, _4, _5);
        Node _3 = new Node(3, _6, _7);
        return new Node(1, _2, _3);
    }

    /**
     * 按层遍历
     *
     * @param head 头节点
     * @return 遍历结果
     */
    private static List<Integer> layerErgodic(Node head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return null;
        }
        result.add(head.data);
        layerErgodic(head, result);
        return result;
    }

    /**
     * 按层遍历 每一层换行
     *
     * @param head 头节点
     */
    private static void printLayerErgodicWithNewLine(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> q = new LinkedBlockingQueue<>();
        Node last = head;
        Node cur = head;
        Node nlast = last;
        while (cur != null) {
            if (cur.left != null) {
                q.add(cur.left);
                nlast = cur.left;
            }
            if (cur.right != null) {
                q.add(cur.right);
                nlast = cur.right;
            }
            if (cur == last) {
                System.out.println(cur.data); //换行
                last = nlast;
            } else {
                System.out.print(cur.data + " ");
            }
            cur = q.poll();
        }
    }

    private static void layerErgodic(Node cur, List<Integer> list) {
        if (cur == null) {
            return;
        }
        boolean leftIsNull = true;
        boolean rightIsNull = true;
        if (cur.left != null) {
            list.add(cur.left.data);
            leftIsNull = false;
        }
        if (cur.right != null) {
            list.add(cur.right.data);
            rightIsNull = false;
        }
        if (!leftIsNull) {
            layerErgodic(cur.left, list);
        }
        if (!rightIsNull) {
            layerErgodic(cur.right, list);
        }
    }

    private static class Node {
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private static Node buildLeaf(int data) {
            return new Node(data, null, null);
        }

        int data;
        Node left;
        Node right;
    }
}
