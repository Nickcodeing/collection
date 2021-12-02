package com.example.collection.web.controller.xiancheng;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class GetTestThread111 {


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private static ExecutorService executorService = Executors.newCachedThreadPool();


    @RequestMapping("/test/test/{id}/{pid}")
    public String getTestMethod(@PathVariable("id") Long id, Long pid){
        System.out.println(pid);
        return "success";
    }






    @RequestMapping("/time/getSchedule")
    public void getSchedule(){

        Map<String, List<String>> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("wjb");
        map.put("first",list1);

        List<String> list2 = new ArrayList<>();
        list2.add("kt001");
        list2.add("kt002");
        map.put("second",list2);

        List<String> list3 = new ArrayList<>();
        list3.add("kt003");
        map.put("third",list3);

        Map<String,Object> contentMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date startTime = calendar.getTime();
        calendar.add(Calendar.HOUR,2);
        Date endTime = calendar.getTime();

        contentMap.put("content", String.format("作业票%s 在时间%s 至%s之间完成","ceshi",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime)));
        method(map,contentMap);

        List<String> list = new ArrayList<>();
        list.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));

        for(int i=0;i<11;i++){
            startTime = calendar.getTime();
            calendar.add(Calendar.HOUR,2*(i+1));
            list.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
        }

/*        for (int i = 0; i < 11; i++) {
            contentMap.put("content", MessageFormat.format("作业票{0} 在时间{1} 至{2}之间完成","ceshi",
                    list.get(i), list.get(i+1)));
            method(map,contentMap);
        }*/
        contentMap = new HashMap<>();
        contentMap.put("content", String.format("作业票%s 在时间%s 至%s之间完成","ceshi",
                "2024-12-16 00:00:00", "2024-12-16 02:00:00"));
        method(map,contentMap);


    }


    public void method(final Map<String,List<String>> userMap,final Map<String,Object> contentMap){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse("2021-08-11 10:50:00");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND,10);
            Date time = calendar.getTime();
            int startInteger = 3;
            long startTime = time.getTime();
            long endTime = System.currentTimeMillis();
            if(startTime >= endTime){//第一次

            }else if(startTime >= endTime-20*1000){//第二次
                startInteger = 2;
            }else if(startTime >= endTime-2*20*1000){//第三次
                startInteger = 1;
            }else{
                startInteger = 0;
            }
            AtomicInteger atomicInteger = new AtomicInteger(startInteger);
            System.out.println(time);
            ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleWithFixedDelay(new TestRunnable(atomicInteger,userMap,contentMap), time, 10 * 1000);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (atomicInteger.get() == 0) {
                            scheduledFuture.cancel(true);
                            boolean cancelled = scheduledFuture.isCancelled();
                            if (!cancelled) {
                                scheduledFuture.cancel(true);
                            }
                            break;
                        }
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException e) {
                            log.error("sleep error:", e);
                        }
                    }
                }
            });
            executorService.submit(thread);
        } catch (Exception e) {
            log.error("message:" + e.getMessage());
        } finally {
            //executorService.shutdown();
        }
    }

}
