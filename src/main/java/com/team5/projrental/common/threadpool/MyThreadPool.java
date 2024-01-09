package com.team5.projrental.common.threadpool;

import com.team5.projrental.common.Const;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Getter
public class MyThreadPool {

    public final ExecutorService threadPool;

    public MyThreadPool(@Value("${server.tomcat.threads.max}") Integer originalThreadPoolSize) {
        this.threadPool = Executors.newFixedThreadPool(originalThreadPoolSize / 2);
    }
}
