package pl.java.scalatech.old;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import pl.java.scalatech.bean.HelperService;

@Slf4j
public class OldFuture {
    private HelperService hs = new HelperService();

    ExecutorService executor = Executors.newFixedThreadPool(10);

    public void futureExample() {

        Callable<String> asyncTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return hs.computeResult();
            }

        };
        Future<String> future = executor.submit(asyncTask);
        hs.doSomethingElse();
        try {
            String result = future.get();
            hs.useResult(result);
        } catch (ExecutionException | InterruptedException e) {
            log.error("{}", e);
        }
        executor.shutdown();
    }

    @Test
    public void shouldFutureWork() {
        new OldFuture().futureExample();
    }

}
