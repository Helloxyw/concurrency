package com.imooc.xyw.examples.atomic;

import com.imooc.xyw.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IDEA
 * author:RicardoXu
 * Date:2019/3/30
 * Time:15:32
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    private static Logger log = LoggerFactory.getLogger(AtomicExample4.class);

    private static AtomicReference<Integer> count = new AtomicReference<>(0);
    public static void main(String []args){

        count.compareAndSet(0,2); //2
        count.compareAndSet(0,1);//no
        count.compareAndSet(1,3); //no
        count.compareAndSet(2,4); //4
        count.compareAndSet(3,5);//no

        log.info("count:{}",count);
    }
}
