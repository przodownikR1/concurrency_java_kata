package pl.java.scalatech.old;

import org.junit.Test;

public class OldThreadApproachTest {
    @Test
    public void shouldThreadWork() {
        MySimpleThread mst = new MySimpleThread();
        new Thread(mst).start();

    }

}
