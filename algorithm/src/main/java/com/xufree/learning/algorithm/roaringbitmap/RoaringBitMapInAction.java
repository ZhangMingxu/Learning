package com.xufree.learning.algorithm.roaringbitmap;

import org.roaringbitmap.longlong.Roaring64NavigableMap;

public class RoaringBitMapInAction {
    public static void main(String[] args) {
        Roaring64NavigableMap roaringBitmap = new Roaring64NavigableMap();
        roaringBitmap.add(1000L);
        System.out.println(roaringBitmap.contains(1000L));
        for (int i = Integer.MAX_VALUE - 10; i < Integer.MAX_VALUE; i++) {
            short hith = highbits(i);
            short low = lowbits(i);
            int unsigned = toIntUnsigned(low);
            System.out.println("int:" + i + " high:" + hith + " low:" + low+"unsigned:");
        }
    }

    protected static short highbits(int x) {
        return (short) (x >>> 16);
    }

    protected static short lowbits(int x) {
        return (short) (x & 0xFFFF);
    }

    protected static int toIntUnsigned(short x) {
        return x & 0xFFFF;
    }
}
