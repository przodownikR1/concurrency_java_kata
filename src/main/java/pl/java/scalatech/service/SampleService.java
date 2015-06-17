package pl.java.scalatech.service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.google.common.base.Stopwatch;

@Slf4j
@Service
public class SampleService {

    @Async
    public Future<Long> callAsync(int taskCall) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info("task " + taskCall + " starting");
        Thread.sleep(500);
        stopwatch.elapsed(TimeUnit.MILLISECONDS);
        log.info("task " + taskCall + " completed in " + stopwatch);
        return new AsyncResult<>(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Async
    public String asyncGetSimpleVAlue() throws InterruptedException {
        log.info("+++ start task");
        Thread.sleep(500);
        log.info("+++ finish task");

        return "przodownik";
    }

    @Async
    public ListenableFuture<String> asyncGetSimpleValueUseListenable() throws InterruptedException {
        log.info("+++ start task");
        Thread.sleep(500);
        log.info("+++ finish task");
        return new AsyncResult<>("przodownik");
    }
}
