package com.explore.galaxy.basic.utils.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPool {
    private final int MIN_POOL_SIZE = 5;
    private final int MAX_POOL_SIZE = 100;

    /**
     * keepAliveTime:
     * 如果当前线程的数量如果大于线程池设定值，闲置的线程就会等待线程池中任务终止，此为最长等待时间
     *
     * @return
     */
    @Bean(value = "mailThreadPool")
    public ExecutorService executorService() {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(MIN_POOL_SIZE, MAX_POOL_SIZE,
                60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3000));
        //拒绝策略
//        //拒绝当前任务
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//        //拒绝队列中最老的任务
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//        //异常
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //将任务分给调用线程来执行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        ExecutorService executor = pool;
        return executor;
    }
}
