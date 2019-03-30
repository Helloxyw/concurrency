package com.imooc.xyw.examples.count;

import com.imooc.xyw.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IDEA
 * author:RicardoXu
 * Date:2019/3/26
 * Time:23:36
 */
@Slf4j
@ThreadSafe
public class CountExample2 {

    private static Logger log = LoggerFactory.getLogger(CountExample2.class);

    //请求总数
    private static int clientTotal = 5000;


    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count.get());


    }

    private static void add(){

        count.incrementAndGet();
//        count++;
    }

}
