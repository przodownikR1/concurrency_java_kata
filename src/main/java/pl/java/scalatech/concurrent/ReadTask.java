package pl.java.scalatech.concurrent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadTask implements Runnable {

    private ReadWriteResource resource;

    public ReadTask(ReadWriteResource resource) {
        this.resource = resource;
    }

    public ReadWriteResource getResource() {
        return resource;
    }

    public void setResource(ReadWriteResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        log.info("ReadTask begin to run..");
        resource.read();
        log.info("ReadTask finish run..");
    }
}