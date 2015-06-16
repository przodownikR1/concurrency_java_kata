package pl.java.scalatech;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
