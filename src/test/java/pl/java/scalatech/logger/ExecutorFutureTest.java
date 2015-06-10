package pl.java.scalatech.logger;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.google.common.collect.Lists;

@Slf4j
public class ExecutorFutureTest {
    final ExecutorService executor = Executors.newCachedThreadPool();
    final Collection<Future<Void>> futures = Lists.newArrayList();
    final Random random = new Random();

    @Test
    public void shouldFuturesWork() {
        for (int i = 0; i < 5; ++i) {
            futures.add(executor.submit(new Callable<Void>() {
                public Void call() throws Exception {
                    int sleep = Math.abs(random.nextInt(10000) % 10000);
                    log.warn("SLEEP {} ms", sleep);
                    Thread.sleep(sleep);
                    return null;
                }
            }));
        }

        for (final Future<Void> future : futures) {
            try {
                Void result = future.get(3, TimeUnit.SECONDS);
                log.info("Result " + result);
            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

}
