package com.example.collection.web.controller.queue;

import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        //add和offer同为添加元素
        queue.add("123");
        queue.add("234");
        queue.add("345");
        queue.offer("456");
        queue.forEach(System.out::println);
        System.out.println("=======");
        //poll获取首个元素,做删除操作
        System.out.println("poll="+queue.poll());
        //poll执行后队列中没有数据了
        queue.forEach(System.out::println);
        System.out.println("=======");
        //element方法为展示第一个元素
        System.out.println("element="+queue.element());
        queue.forEach(System.out::println);
        System.out.println("=======");
        //peek为获取第一个元素，不做删除操作
        System.out.println("peek="+queue.peek());
        queue.forEach(System.out::println);
        System.out.println("=======");
        //remove为删除首个元素
        queue.remove();
        queue.forEach(System.out::println);
        System.out.println("=======");
        //删除不存在的元素时，返回为false
        boolean remove = queue.remove("2354352346");
        System.out.println(remove);
        queue.forEach(System.out::println);
    }
}
