package com.xufree.learning.java.test;

import com.xufree.learning.common.util.SleepUtil;
import javafx.util.converter.LongStringConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {
    private static List<Integer> myList = new ArrayList<>(100000000);

    @Test
    public void speedTest() {
        long loadBeginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++)
            myList.add(i);
        System.out.println("load data use " + ((System.currentTimeMillis() - loadBeginTime) / 1000) + " s");
        for (int i = 0; i < 5; i++) {
            long result = 0L;
            long loopStartTime = System.currentTimeMillis();
            for (int a : myList) {
                if (a % 2 == 0)
                    result += a;
            }
            long loopEndTime = System.currentTimeMillis();
            System.out.println(result);
            System.out.println("Loop total Time = " + (loopEndTime - loopStartTime));
            long streamStartTime = System.currentTimeMillis();
            System.out.println(myList.stream().filter(value -> value % 2 == 0).mapToLong(Integer::longValue).sum());
            long streamEndTime = System.currentTimeMillis();
            System.out.println("Stream total Time = " + (streamEndTime - streamStartTime));
            long parallelStreamStartTime = System.currentTimeMillis();
            System.out.println(myList.parallelStream().filter(value -> value % 2 == 0).mapToLong(Integer::longValue).sum());
            long parallelStreamEndTime = System.currentTimeMillis();
            System.out.println("Parallel Stream total Time = " + (parallelStreamEndTime - parallelStreamStartTime));
            System.out.println();
        }
    }

    @Test
    public void streamLazinessTest() {

        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i < 10000000; i++) {
            employees.add(new Employee(i, "name_" + i));
        }
        Stream<String> employeeNameStreams = employees.parallelStream().filter(employee -> employee.id % 2 == 0)
                .map(employee -> {
                    System.out.println("In Map - " + employee.name);
                    return employee.name;
                });
        SleepUtil.second(2);
        System.out.println("2 sec");
        employeeNameStreams.collect(Collectors.toList());
    }

    @Test
    public void streamShortCircuitTest() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i < 10000000; i++) {
            employees.add(new Employee(i, "name_" + i));
        }
        Stream<String> employeeNameStreams = employees.stream().filter(e -> e.id % 2 == 0)
                .map(employee -> {
                    System.out.println("In Map - " + employee.name);
                    return employee.name;
                });
        long streamStartTime = System.currentTimeMillis();
        employeeNameStreams.limit(100).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - streamStartTime);
    }

    @Test
    public void splitTest() {
        List<Employee> employees = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            employees.add(new Employee(i, "1-" + i));
        }
//        List<Long> ids = employees.stream().map(e-> Arrays.stream(e.name.split("-"))).mapToLong(value -> Long.parseLong(value.toString())).collect(Collectors.toList());
    }

    static class Employee {
        int id;
        String name;

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
