package pl.java.scalatech.old_new;

import static com.google.common.util.concurrent.Futures.addCallback;
import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import pl.java.scalatech.bean.HelperService;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

@Slf4j
public class NewFuture {
    HelperService hs = new HelperService();
    ListeningExecutorService executor = listeningDecorator(Executors.newFixedThreadPool(10));

    public void listenableFutureWithCallback() {

        Callable<String> asyncTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return hs.computeResult();
            }
        };
        ListenableFuture<String> listenableFuture = executor.submit(asyncTask);
        addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                doMoreWithTheResultImmediately(result);
            }

            private void doMoreWithTheResultImmediately(String result) {
                log.info("+++  -> result immediately now  {} ", result);
            }

            @Override
            public void onFailure(Throwable thrown) {
                handleFailure(thrown);
            }

            private void handleFailure(Throwable thrown) {
                log.error("+++ -> {}", thrown);
            }
        });

        hs.doSomethingElse();

        try {
            String result = listenableFuture.get();
            hs.useResult(result);
        } catch (ExecutionException | InterruptedException e) {
            log.error("{}", e);
        }

        executor.shutdown();
    }

    @Test
    public void shouldGuavaFutureWork() {
        new NewFuture().listenableFutureWithCallback();
    }
}
