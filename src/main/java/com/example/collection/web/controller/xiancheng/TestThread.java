package com.example.collection.web.controller.xiancheng;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/test")
public class TestThread {


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @RequestMapping("/test")
    public String method() throws ParseException {
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse("2021-08-09 03:00:00");
        threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("just for test");
            }
        },date);*/

        AtomicInteger integer = new AtomicInteger(3);
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                integer.decrementAndGet();
                System.out.println(Thread.currentThread().getName()+":just for test  "+integer.get());
            }
        }, new Date(), 10 * 1000);

        while(true){
            if(integer.get()<=0){
                scheduledFuture.cancel(true);
                // 查看任务是否在正常执行之前结束,正常true
                boolean cancelled = scheduledFuture.isCancelled();
                while (!cancelled) {
                    scheduledFuture.cancel(true);
                }
                break;
            }
            try {
                Thread.sleep(4*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

}
