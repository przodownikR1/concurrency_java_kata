package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import pl.java.scalatech.concurrent.ReadWriteResource;

@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
    @Bean
    public ReadWriteResource readWriteResource() {
        return new ReadWriteResource();
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(2);
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    // SimpleAsyncTaskExecutor - > start a new theard for each invocation. Supports a concurrency limit,
    // with block any invocations that are over the limit until a slot is free.

}