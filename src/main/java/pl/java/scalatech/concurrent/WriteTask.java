package pl.java.scalatech.concurrent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteTask implements Runnable {

    private ReadWriteResource resource;

    public WriteTask(ReadWriteResource resource) {
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
        log.info("WriteTask begin to run..");
        resource.write();
        log.info("WriteTask finish run..");
    }
}