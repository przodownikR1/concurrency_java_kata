package pl.java.scalatech.old_new;

import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DirectExecutor implements Executor {
    @Override
    public void execute(Runnable r) {
        r.run();
        log.info("{}", Thread.currentThread().getName());

    }
}