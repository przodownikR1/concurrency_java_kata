package pl.java.scalatech.old_now;

import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyNewSimpleThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        log.info("+++ start ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        log.info("+++ end");
        return "my new simple thread action ...";
    }

}
