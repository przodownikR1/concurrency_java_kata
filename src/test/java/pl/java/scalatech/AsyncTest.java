package pl.java.scalatech;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import pl.java.scalatech.config.AppConfig;
import pl.java.scalatech.service.SampleService;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Slf4j
public class AsyncTest {
    @Autowired
    private SampleService sampleService;

    @Test
    public void shouldAsyncWork() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Future<Long>> result = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            result.add(sampleService.callAsync(i));
        }
        result.stream().forEach(t -> {
            try {
                log.info("task : {} ", t.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("{}", e);
            }

        });

        log.info("tasks took : {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }

    @Test
    public void shouldSimpleAsyncWork() throws InterruptedException {
        log.info("+++  {}", sampleService.asyncGetSimpleVAlue());
        Assert.assertEquals(null, sampleService.asyncGetSimpleVAlue());
    }

    @Test
    public void shouldSimpleListenableAsyncWork() throws InterruptedException, ExecutionException {
        ListenableFuture<String> listenableFuture = sampleService.asyncGetSimpleValueUseListenable();
        SuccessCallback<String> successCallback = str -> log.info("success : {}", str);
        FailureCallback failureCallback = throwable -> log.error("error :  {}", throwable);

        listenableFuture.addCallback(successCallback, failureCallback);
        Assert.assertEquals("przodownik", listenableFuture.get());
    }

}
