package pl.java.scalatech.old_now;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleCallable implements Callable<String> {

    private long waitTime;

    public MySimpleCallable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {
        log.info("+++  start {}", Thread.currentThread().getName());
        TimeUnit.MILLISECONDS.sleep(waitTime);
        log.info("+++  end {}", Thread.currentThread().getName());
        return "callable stop : " + Thread.currentThread().getName();
    }

}