package pl.java.scalatech.old;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySimpleThread implements Runnable {

    @Override
    public void run() {
        log.info("+++ start ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        log.info("+++ end");
        log.info("+++ run my simple thread action ");

    }

}
