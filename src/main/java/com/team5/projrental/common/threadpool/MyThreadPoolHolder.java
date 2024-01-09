package com.team5.projrental.common.threadpool;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Getter
public class MyThreadPoolHolder {

    public final ExecutorService threadPool;

    public MyThreadPoolHolder(@Value("${server.tomcat.threads.max}") Integer originalThreadPoolSize) {
        this.threadPool = Executors.newFixedThreadPool(originalThreadPoolSize / 2);
    }
}
