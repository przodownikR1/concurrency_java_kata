package pl.java.scalatech.concurrency;

import static com.jayway.awaitility.Awaitility.await;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import com.google.common.collect.Lists;

@Slf4j
public class CountDownTest {
    final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Test
    public void shouldCountDownWork() {
        int testTasks = 30;

        CountDownLatch isExecuted = new CountDownLatch(testTasks);

        List<Runnable> tasks = Lists.newArrayList();

        for (int i = 0; i < testTasks; i++) {
            tasks.add(() -> {
                log.info("Runs on thread " + Thread.currentThread().getName());
                isExecuted.countDown();
                log.info("+++ count :  {}", isExecuted.getCount());

            });
        }

        for (Runnable task : tasks) {
            executorService.execute(task);
        }

        await().until(() -> isExecuted.getCount() == 0);
    }
}
