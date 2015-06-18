package pl.java.scalatech.old_now;

import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterCallable implements Callable<String> {

    private final int i;

    public CounterCallable(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        Thread.currentThread().setName("p:" + i);
        return "p:" + i;

    }

}
