package com.xufree.learning.algorithm.tree;


import java.util.*;

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
            throw new IllegalArgumentException("value can not be null");
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

    public void match(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text can not be null");
        }
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            match(chars, i, chars.length);
        }
    }

    private void match(char[] chars, int begin, int end) {
        char first = chars[begin];
        Node cur = null;
        for (int i = begin; i < end; i++) {
            if (i == begin) {
                cur = heads.get(first);
            } else {
                cur = cur.children.get(chars[i]);
            }
            if (cur == null) {
                return;
            }
            if (cur.isEnd) {
//                System.out.println(true);
            }
        }
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
        int n = 10000000;
        String s1 = "张明旭阿克琉斯就到了卡觉得卡拉胶苏里科夫哈哈弗兰克和你分开见多识广分开就撒谎的逻辑卡收费的";
        String s2 = "a克赖斯基让他赶快来发手机看了好几份但是看了就赶快来健身房链接看动画kkk给 i 前后克里斯蒂哈来个合适的空间啊帅哥短裤家居服；看了哈空间的风格";
        TrieTree tree = new TrieTree();
        tree.put("asd");
        tree.put("张明旭");
        tree.put("asdasd");
        tree.put("张明旭1");
        tree.put("dsa");
        tree.put("kkk");
        tree.put("a");
//        System.out.println(tree.contains("asdqqq"));
//        System.out.println(tree.contains("张明旭"));
//        System.out.println(tree.contains("张明旭1"));
//        System.out.println(tree.contains("a"));
        long treeBegin = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            tree.match(s1);
            tree.match(s2);
        }
        System.out.println("tree use " + (System.currentTimeMillis() - treeBegin));
        Set<String> set = new HashSet<>();
        set.add("asd");
        set.add("张明旭");
        set.add("asdasd");
        set.add("张明旭1");
        set.add("dsa");
        set.add("kkk");
        set.add("a");
        long setBegin = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (String s : set) {
                s1.contains(s);
            }
            for (String s : set) {
                s2.contains(s);
            }
        }
        System.out.println("set use " + (System.currentTimeMillis() - setBegin));

    }
}
