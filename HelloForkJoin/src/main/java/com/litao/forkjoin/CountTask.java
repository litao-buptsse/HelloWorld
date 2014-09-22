package com.litao.forkjoin;

import java.util.concurrent.*;

/**
 * Created by Tao Li on 9/21/14.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        try {
            System.out.println("compute " + start + ", " + end);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            System.out.println("-- fork left task " + start + ", " + middle);
            rightTask.fork();
            System.out.println("-- fork right task " + (middle + 1) + ", " + end);


            int leftResult = leftTask.join();
            System.out.println("---- join left task " + start + ", " + middle);
            int rightResult = rightTask.join();
            System.out.println("---- join right task " + (middle + 1) + ", " + end);
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 8);
        Future<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());
    }
}
