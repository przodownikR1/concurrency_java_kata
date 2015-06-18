package pl.java.scalatech.guava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.util.concurrent.MoreExecutors;

public class MoreExecutorsTest {

    @Test
    public void shouldMoreExecutorBootstrap() {
        ExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    }
}
