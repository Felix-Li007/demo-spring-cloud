package demo.sport.zones.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 异步线程池配置
 *
 * @author koushenglong
 * @since 2022-06-26
 */
@Configuration
public class ThreadPoolConfig
{
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    private static final int WORK_QUEUE_SIZE = 500;
    private static final int KEEP_ALIVE_TIME = 5;

    final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            // -%d不要少
            .setNameFormat("ASYNC-SPORT-ZONE-%d")
            .setDaemon(true)
            .build();

    @Bean("ThreadPoolTask")
    public Executor ThreadPoolExecutor()
    {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, 2 * CORE_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(WORK_QUEUE_SIZE),
                threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}