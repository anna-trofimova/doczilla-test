package com.example.fileservice;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileCleaner {
    private final String storageDir;
    private final long maxAgeDays;

    public FileCleaner(String storageDir, long maxAgeDays) {
        this.storageDir = storageDir;
        this.maxAgeDays = maxAgeDays;
    }

    public void start() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                this::clean,
                0,
                24,
                TimeUnit.HOURS
        );
    }

    private void clean() {
        File dir = new File(storageDir);
        if (!dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        Instant now = Instant.now();

        for (File file : files) {
            Instant fileTime = Instant.ofEpochMilli(file.lastModified());
            long ageDays = ChronoUnit.DAYS.between(fileTime, now);

            System.out.println(
                    "[Cleaner] Checking file: " + file.getName() +
                            ", ageDays=" + ageDays
            );

            if (ageDays >= maxAgeDays) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("Deleted old file: " + file.getName());
                }
            }
        }
    }
}
