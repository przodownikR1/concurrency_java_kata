package pl.java.scalatech.guava;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Monitor;

public class TestMonitor {

    private List<String> list = Lists.newArrayList();
    private static final int MAX_SIZE = 10;

    private Monitor monitor = new Monitor();
    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity);
        try {
            list.add(item);
            System.err.println("put");
        } finally {
            monitor.leave();
        }
    }

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();
        final TestMonitor testMonitor = new TestMonitor();

        for (int i = 0; i < 5; i++) {
            final int a = i;
            new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        ai.incrementAndGet();
                        System.out.println("thread" + a + " before add");
                        try {
                            testMonitor.addToList("one");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("thread" + a + " after add");
                    }
                }
            }.start();
            System.err.println(ai.get());
        }

    }
}