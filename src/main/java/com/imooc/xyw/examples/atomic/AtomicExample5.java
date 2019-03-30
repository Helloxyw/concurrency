package com.imooc.xyw.examples.atomic;

import com.imooc.xyw.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created with IDEA
 * author:RicardoXu
 * Date:2019/3/30
 * Time:15:32
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static Logger log = LoggerFactory.getLogger(AtomicExample5.class);

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String []args){

        AtomicExample5 atomicExample5 = new AtomicExample5();

        if(updater.compareAndSet(atomicExample5,100,120)){
            log.info("update success 1,{}",atomicExample5.getCount());
        }

    }
}
