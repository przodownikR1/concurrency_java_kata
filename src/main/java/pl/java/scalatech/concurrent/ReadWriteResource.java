package pl.java.scalatech.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadWriteResource {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void read() {
        readLock.lock();
        log.info("begin to read, add lock...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            log.info("finish read, release lock...");
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        log.info("begin to write, add lock...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            log.info("finish write, release lock...");
            writeLock.unlock();
        }
    }
}