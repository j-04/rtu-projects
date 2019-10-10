package com.threadmanager;

public class Process extends Thread {
    private int counter = 0;
    private int seconds = 0;

    public Process(String processName) {
        super(processName);
    }

    @Override
    public void run() {
        int begin = 1;
        int end = 25;
        this.seconds = begin + (int) (Math.random() * end);
        int step = 1;
        int i = 0;
        try {
            do {
                printProgress();
                i += step;
                counter += step;
                sleep(1000);
            } while (i <= seconds);
            sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printCompleted();
    }

    private void printProgress() {
        String processing = "|Processing|" + this.getName() + "|[" +
                counter * 100 / seconds +
                "%]" +
                " Seconds passed - " +
                counter +
                " -> " +
                "Seconds - " +
                seconds;
        System.out.println(processing);
    }

    private void printCompleted() {
        String stopped = "|Completed|" + this.getName() + "|[" +
                counter * 100 / seconds +
                "%]" +
                " Seconds passed - " +
                counter +
                " ->" +
                " Seconds - " +
                seconds;
        System.err.println(stopped);
    }
}
