package fr.ladder.core;

import fr.ladder.api.Task;
import fr.ladder.wirer.annotation.Inject;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class LadderTask implements Task.Implementation {

    private final LadderEngine _engine;

    private final LadderExecutor _executor;

    public LadderTask(LadderEngine engine, LadderExecutor executor) {
        _engine = engine;
        _executor = executor;
    }

    private final AtomicInteger lastId = new AtomicInteger(0);

    private final Map<Integer, Future<?>> asyncTaskMap = new ConcurrentHashMap<>();

    @Override
    public void run(Runnable runnable) {
        Bukkit.getScheduler().runTask(_engine, runnable);
    }

    @Override
    public void runAsync(Runnable runnable) {
        _executor.schedule(runnable, 5, TimeUnit.MILLISECONDS);
    }

    @Override
    public synchronized int run(Runnable runnable, long tick) {
        return Bukkit.getScheduler().runTaskLater(_engine, runnable, tick).getTaskId();
    }

    @Override
    public synchronized int runAsync(Runnable runnable, long tick) {
        ScheduledFuture<?> future = _executor.schedule(runnable, tick * 50, TimeUnit.MILLISECONDS);
        int taskId = lastId.decrementAndGet();
        this.asyncTaskMap.put(taskId, future);

        return taskId;
    }

    @Override
    public synchronized void runAsync(Supplier<Boolean> runnable, long delay, long period) {
        int taskId = lastId.decrementAndGet();

        this.asyncTaskMap.put(taskId, _executor.scheduleAtFixedRate(
                () -> this.runTick(runnable, taskId),
                delay * 50,
                period * 50,
                TimeUnit.MILLISECONDS
        ));
    }

    private synchronized void runTick(Supplier<Boolean> runnable, int taskId) {
        if(!runnable.get()) {
            this.cancel(taskId);
        }
    }

    private synchronized void cancel(int taskId) {
        Future<?> task = this.asyncTaskMap.remove(taskId);
        if(task == null)
            return;
        task.cancel(true);
    }

}