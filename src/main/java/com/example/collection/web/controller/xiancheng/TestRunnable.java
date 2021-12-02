package com.example.collection.web.controller.xiancheng;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunnable implements Runnable{

    private AtomicInteger atomicInteger;

    private Map<String,List<String>> userMap;

    private Map<String,Object> contentMap;

    public TestRunnable(AtomicInteger atomicInteger,Map<String,List<String>> userMap,Map<String,Object> contentMap){
        this.atomicInteger = atomicInteger;
        this.userMap = userMap;
        this.contentMap = contentMap;
    }


    @Override
    public void run() {
        List<String> userName = new ArrayList<>();
        if (atomicInteger.get() == 3) {//第一次30分钟
            userName = userMap.get("first");
        }
        if (atomicInteger.get() == 2) {//第二次20分钟
            userName = userMap.get("second");
        }
        if (atomicInteger.get() == 1) {//第一次10分钟
            userName = userMap.get("third");
        }
        if (!CollectionUtils.isEmpty(userName)) {
            userName.forEach(System.out::println);
            System.out.println(contentMap.get("content"));
            System.out.println("just for test!" + atomicInteger.get());
            System.out.println(new Date());
        }
        atomicInteger.decrementAndGet();
    }
}
