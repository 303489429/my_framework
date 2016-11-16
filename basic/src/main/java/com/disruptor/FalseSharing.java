package com.disruptor;

/**
 * Created by wangzhilong on 2016/11/16.
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4 ;
    public final static long ITERATIONS = 500L * 1000 * 1000L ;
    private final int arrayIndex ;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }
    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t : threads)
            t.start();

        for (Thread t : threads)
            t.join();
    }
    @Override
    public void run() {
        long i = ITERATIONS + 1 ;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.nanoTime() ;
        runTest();
        System.out.println("耗时："+(System.nanoTime() - start));
    }
}
