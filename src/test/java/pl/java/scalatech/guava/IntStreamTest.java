package pl.java.scalatech.guava;

import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

@Slf4j
public class IntStreamTest {

    @Test
    public void shouldIntStreamWork() {
        IntStream.range(0, 100).forEach(i -> log.info("{}", i));
    }
}
