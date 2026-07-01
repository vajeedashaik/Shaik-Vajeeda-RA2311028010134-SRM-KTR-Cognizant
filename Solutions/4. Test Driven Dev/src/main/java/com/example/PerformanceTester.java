package com.example;

public class PerformanceTester {

    public String performTask() {
        // Fast task that completes within milliseconds
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        return "Result: " + sum;
    }

    public String slowTask() throws InterruptedException {
        Thread.sleep(5000);
        return "Slow task done";
    }
}
