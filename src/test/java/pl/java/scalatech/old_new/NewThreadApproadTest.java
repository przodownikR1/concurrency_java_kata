package pl.java.scalatech.old_new;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import pl.java.scalatech.old_now.MyNewSimpleThread;

@Slf4j
public class NewThreadApproadTest {
    @Test
    public void shouldNewThreadApproachWork() throws Exception {
        MyNewSimpleThread nmst = new MyNewSimpleThread();

        log.info("+++  {}", nmst.call());
    }
}
