package fr.ladder.api;

import java.util.function.Supplier;

public final class Task {

    private static Implementation _implementation;

    private Task() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void setImplementation(Implementation implementation) {
        _implementation = implementation;
    }

    public static void run(Runnable runnable) {
        _implementation.run(runnable);
    }

    public static void runAsync(Runnable runnable) {
        _implementation.runAsync(runnable);
    }

    public static int run(Runnable runnable, long tick) {
        return _implementation.run(runnable, tick);
    }

    public static int runAsync(Runnable runnable, long tick) {
        return _implementation.runAsync(runnable, tick);
    }

    public static void runAsync(Supplier<Boolean> runnable, long delay, long period) {
        _implementation.runAsync(runnable, delay, period);
    }

    public interface Implementation {

        void run(Runnable runnable);

        void runAsync(Runnable runnable);

        int run(Runnable runnable, long tick);

        int runAsync(Runnable runnable, long tick);

        void runAsync(Supplier<Boolean> runnable, long delay, long period);

    }
}