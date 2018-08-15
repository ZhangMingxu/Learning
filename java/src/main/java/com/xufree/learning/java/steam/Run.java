package com.xufree.learning.java.steam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {

    public static void main(String[] args) {
        List<TestVO> vos = createTestVOs();
//        List<String> names = vos.stream().filter(vo -> {
//            String name = vo.getName();
//            if (name.startsWith("333")) {
//                String numStr = name.substring(name.indexOf("#") + 1);
//                Double num = Double.parseDouble(numStr);
//                return num > 0.5;
//            } else {
//                return false;
//            }
//
//        }).map(TestVO::getName).collect(Collectors.toList());
        Map<Long,TestVO> map = vos.stream().collect(Collectors.toMap(TestVO::getId,a->a));
        map.forEach((k,v)->{
            System.out.println(k+"\t"+v);
        });
    }

    private static List<TestVO> createTestVOs() {
        List<TestVO> vos = new ArrayList<>(1000);
        for (long i = 0L; i < 1000; i++) {
            TestVO vo = new TestVO();
            vo.setId(i);
            if (i % 3 == 0) {
                vo.setName("333#" + Math.random());
            } else {
                vo.setName("else");
            }
            vos.add(vo);
        }
        return vos;
    }
}
