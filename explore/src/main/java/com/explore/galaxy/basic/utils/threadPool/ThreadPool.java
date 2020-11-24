package com.explore.galaxy.basic.utils.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPool {
    private final int CORE_POOL_SIZE = 5;
    private final int MAX_POOL_SIZE = 100;

    /**
     * keepAliveTime:
     * 如果当前线程的数量如果大于线程池设定值，闲置的线程就会等待线程池中任务终止，此为最长等待时间
     *
     * @return
     */
    @Bean(value = "mailThreadPool")
    public ExecutorService executorService() {
        ExecutorService executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        return executor;
    }
}
