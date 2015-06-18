package pl.java.scalatech.old;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleThread implements Runnable {

    @Override
    public void run() {
        log.info("+++ start {}", Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        log.info("+++ end  {} ", Thread.currentThread().getName());
        log.info("+++ run my simple thread action  {}", Thread.currentThread().getName());

    }

}
