package com.example.collection.web.controller.list;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class TestArrayList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.forEach(System.out::println);
        //容量大小  和初始化容量大小是两码事  初始化容量是10 每次扩容都是原先的大小+原先大小>>1
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.contains("c"));
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            String next = listIterator.next();
            if(next.equals("c")){
                listIterator.remove();
            }else
            System.out.print(next+"--");
        }
        Object[] objects = list.toArray(new String[6]);
        System.out.println(Arrays.toString(objects));
        System.out.println(list.size());
        list.forEach(System.out::println);
        List<String> objects1 = new ArrayList<>();
        objects1.add("a");
        objects1.add("b");
        objects1.add("d");
        objects1.add("g");
        //addAll从指标n开始，不超过list的长度
        //list.addAll(3,objects1);
        System.out.println("======");
        list.forEach(System.out::println);
        //计算子集，返回的boolean是判断是否和原list大小相等
        boolean b = list.retainAll(objects1);
        System.out.println(b);
        list.forEach(System.out::println);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("======");
        list.forEach(System.out::println);
        //clear是将元素全部置为null size为0
        //set方法为替换
        list.set(2,"sdfg");
        System.out.println("======");
        list.forEach(System.out::println);
        System.out.println(list.indexOf("d"));
        list.subList(0,1).forEach(System.out::println);
    }
}
