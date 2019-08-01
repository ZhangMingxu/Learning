package com.xufree.learning.algorithm.list;

import java.util.Comparator;

/**
 * 跳表
 *
 * @author zhangmingxu ON 16:19 2019-07-31
 **/
public class SkipList<T> {
    private final Comparator<? super T> comparator;

    public SkipList() {
        comparator = null;
    }

    public SkipList(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int compare(Comparator comparator, Object x, Object y) {
        return comparator != null ? comparator.compare(x, y) : ((Comparable) x).compareTo(y);
    }
}
