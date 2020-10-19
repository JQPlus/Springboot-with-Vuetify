package com.explore.galaxy.basic.utils.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class MailThreadPool {
    private final int MIN = 4;
    private final int MAX = 10;
    @Bean
    public ExecutorService executorService() {
        ExecutorService executor = new ThreadPoolExecutor(MIN, MAX,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        return executor;
    }
}
