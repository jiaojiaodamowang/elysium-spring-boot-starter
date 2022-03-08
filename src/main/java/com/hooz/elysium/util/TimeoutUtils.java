package com.hooz.elysium.util;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * TimeoutUtils
 *
 * @author Kidd Zhou
 * @date 2022-03-08
 */
public class TimeoutUtils {

    private static final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(scheduledExecutor::shutdown));
    }

    public static <T> CompletableFuture<T> execute(CompletableFuture<T> cf, long delay, TimeUnit timeUnit) {
        CompletableFuture<T> timeoutResult = new CompletableFuture<>();
        scheduledExecutor.schedule(() -> timeoutResult.completeExceptionally(new TimeoutException()), delay, timeUnit);
        return cf.applyToEither(timeoutResult, Function.identity());
    }
}
