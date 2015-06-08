package pl.java.scalatech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.java.scalatech.notification.SampleSpringNotification;

@SpringBootApplication
public class ConcurrencyJavaApplication implements CommandLineRunner {
    @Autowired
    private SampleSpringNotification sampleSpringNotification;

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sampleSpringNotification.sendEvent();

    }
}
