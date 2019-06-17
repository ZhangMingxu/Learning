package com.xufree.learning.algorithm.tree;


import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 *
 * @author zhangmingxu ON 09:29 2019-06-17
 **/
public class TrieTree {
    //所有的头节点
    private final Map<Character, Node> heads = new HashMap<>();


    public void put(String value) {
        if (value == null) {
            return;
        }
        char[] chars = getChars(value);
        if (chars.length == 0) {
            return;
        }
        Node head;
        if (chars.length == 1) {//只有一个字符的话创建完头就可以返回了
            buildHead(chars[0], true);
            return;
        } else {
            head = buildHead(chars[0], false);
        }
        Node curFather = head;
        for (int i = 1; i < chars.length; i++) {
            char childChar = chars[i];
            boolean isEnd = i == chars.length - 1;
            curFather = getChild(curFather, childChar, isEnd);
        }
    }

    /**
     * 判断value是否存在于树中
     *
     * @param value 待判断的值
     * @return value是否存在于树中
     */
    public boolean contains(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        char[] chars = getChars(value);
        Node cur = heads.get(chars[0]);
        if (cur == null) {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            cur = cur.children.get(chars[i]);
            if (cur == null) {
                return false;
            }
        }
        return cur.isEnd;
    }

    /**
     * 获取某一个父节点的子节点
     * 如果之前不存在就创建一个
     * 如果存在并且之前isEnd是false并且新的是true会修改为true
     *
     * @param father 父节点
     * @param aChar  对应的char
     * @param isEnd  是否是最后一个节点
     * @return 子节点
     */
    private Node getChild(Node father, char aChar, boolean isEnd) {
        Node oldChild = father.children.get(aChar);
        if (oldChild == null) {
            Node newChild = new Node(aChar, isEnd);
            father.children.put(aChar, newChild);
            return newChild;
        } else {
            if (!oldChild.isEnd && isEnd) {//如果之前它不是结尾，现在是了需要修改为是结尾
                oldChild.isEnd = true;
            }
            return oldChild;
        }
    }

    /**
     * 创建一个头节点
     * 如果之前不存在就创建一个
     * 如果存在并且之前isEnd是false并且新的是true会修改为true
     *
     * @param head  头char
     * @param isEnd 是否结束
     * @return 头节点
     */
    private Node buildHead(char head, boolean isEnd) {
        Node oldHead = heads.get(head);
        if (oldHead == null) {
            Node newHead = new Node(head, isEnd);
            heads.put(head, newHead);
            return newHead;
        } else {
            if (!oldHead.isEnd && isEnd) {//如果之前它不是结尾，现在是了需要修改为是结尾
                oldHead.isEnd = true;
            }
            return oldHead;
        }
    }

    private char[] getChars(String value) {
        return value.toCharArray();
    }

    //节点
    private static class Node {
        private final char aChar; //对应的值
        private boolean isEnd; //是否有字符串在这里结束
        private final Map<Character, Node> children = new HashMap<>(); //子树

        public Node(char aChar, boolean isEnd) {
            this.aChar = aChar;
            this.isEnd = isEnd;
        }
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.put("asd");
        tree.put("张明旭");
        tree.put("asdasd");
        tree.put("张明旭1");
        tree.put("dsa");
        tree.put("kkk");
        tree.put("a");
        System.out.println(tree.contains("asdqqq"));
        System.out.println(tree.contains("张明旭"));
        System.out.println(tree.contains("张明旭1"));
        System.out.println(tree.contains("a"));
    }
}
