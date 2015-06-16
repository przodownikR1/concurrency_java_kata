package pl.java.scalatech.service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

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
}
