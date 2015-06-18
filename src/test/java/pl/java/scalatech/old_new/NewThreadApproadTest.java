package pl.java.scalatech.old_new;

import static com.jayway.awaitility.Awaitility.await;
import static java.lang.Thread.getAllStackTraces;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

import org.hamcrest.Matchers;
import org.junit.Test;

import pl.java.scalatech.old.MySimpleThread;
import pl.java.scalatech.old_now.CounterCallable;
import pl.java.scalatech.old_now.MyNewSimpleThread;
import pl.java.scalatech.old_now.MySimpleCallable;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

@Slf4j
public class NewThreadApproadTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.getDefault());
    DateTimeFormatter formatter2 = new DateTimeFormatterBuilder().appendPattern("HHmmss.SSSZ").parseCaseInsensitive().toFormatter();
    private static DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private AtomicInteger ai = new AtomicInteger();

    @Test
    public void shouldNewThreadApproachWork() throws Exception {
        MyNewSimpleThread nmst = new MyNewSimpleThread();

        log.info("+++  {}", nmst.call());
    }

    @Test
    public void shouldAwaitFutureWork() throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(7);
        List<Future<String>> futures = Lists.newArrayList();
        for (int i : ContiguousSet.create(Range.open(1, 40), DiscreteDomain.integers())) {
            futures.add(es.submit(new CounterCallable(ai.getAndIncrement())));
        }
        new Thread(() -> {
            for (Future<String> f : futures) {
                try {
                    log.info("+++  f = {}", f.get());
                } catch (InterruptedException | ExecutionException e) {
                    log.error("{}", e);
                }
            }
        }).start();

        log.info("non-blocking flow .......");
        getAllStackTraces().keySet().stream().forEach(t -> log.info("thread name = {}", t.getName()));
        Callable<Boolean> threadWithNameExists = () -> getAllStackTraces().keySet().stream().anyMatch(t -> t.getName().equals("p:37"));
        await().until(threadWithNameExists, Matchers.is(true));

    }

    @Test
    public void shouldInkoveThreadUseExecutorsPool() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> list = Lists.newArrayList();

        Callable<String> callable = new MyNewSimpleThread();
        for (int i = 0; i < 20; i++) {

            Future<String> future = executor.submit(callable);

            list.add(future);
        }
        list.stream().forEach(fut -> {
            try {
                log.info("{}  , {}", LocalTime.now().format(formatter3), fut.get());
            } catch (Exception e) {
                log.error("{}", e);
            }
        });

        executor.shutdown();
    }

    @Test
    public void shouldFutureWork() {

        MySimpleCallable callable1 = new MySimpleCallable(1000);
        MySimpleCallable callable2 = new MySimpleCallable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(callable1);
        FutureTask<String> futureTask2 = new FutureTask<>(callable2);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {
                    log.info("+++  Done");
                    executor.shutdown();
                    return;
                }

                if (!futureTask1.isDone()) {
                    log.info("FutureTask1 : {}", futureTask1.get());
                }

                if (!futureTask2.isDone()) {
                    log.info("FutureTask2 : {}", futureTask2.get());
                }

            } catch (InterruptedException | ExecutionException e) {

            }
        }

    }

    @Test
    public void shouldExecutorWork() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);

        for (int i : ContiguousSet.create(Range.open(1, 5), DiscreteDomain.integers())) {
            es.execute(new MySimpleThread());
        }
        getAllStackTraces().keySet().stream().forEach(t -> log.info(" ~ ->  {}", t));

        await().atMost(5, TimeUnit.SECONDS).until((Callable<Boolean>) () -> es.isTerminated());
    }

}
