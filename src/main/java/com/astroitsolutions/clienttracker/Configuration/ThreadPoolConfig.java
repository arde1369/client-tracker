package com.astroitsolutions.clienttracker.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfig {

    @Value("${threadpool.core-size:10}")
    private int coreSize;

    @Value("${threadpool.max-size:20}")
    private int maxSize;

    @Value("${threadpool.keep-alive-seconds:30}")
    private int keepAliveSeconds;

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolExecutor executor = new java.util.concurrent.ThreadPoolExecutor(
            coreSize, 
            maxSize, 
            keepAliveSeconds, 
            TimeUnit.SECONDS, 
            new java.util.concurrent.LinkedBlockingQueue<Runnable>()
        );
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }
    
}
