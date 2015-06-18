package pl.java.scalatech.old_new;

import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerExecutor implements Executor {

    @Override
    public void execute(Runnable r) {
        new Thread(() -> {
            log.info("{}", Thread.currentThread().getName());
        }).start();
    }

}
