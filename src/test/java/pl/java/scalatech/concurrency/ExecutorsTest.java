package pl.java.scalatech.concurrency;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

@Slf4j
public class ExecutorsTest {
    final static AtomicInteger ai = new AtomicInteger();

    @Test
    public void shouldExecutorsTest() {
        new Thread(() -> threadPool()).start();
        new Thread(() -> singleThreadExecutor()).start();
        Awaitility.await().atMost(Duration.TEN_SECONDS).untilAtomic(ai, Matchers.is(4));
    }

    private static void threadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            ai.incrementAndGet();
            log.info("+++ threadPool {}   , {} ", Thread.currentThread().getName(), LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));

            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        };

        executor.execute(runnable);
        executor.execute(runnable);
        executor.shutdown();
    }

    private static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            ai.incrementAndGet();
            log.info("+++ singleThread {}   , {}", Thread.currentThread().getName(), LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        };

        executor.execute(runnable);
        executor.execute(runnable);
        executor.shutdown();
    }
}
