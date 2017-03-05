import java.util.*;

public class Executive {

    private class SuccessFlag {
        boolean flag = false;

        public boolean success() {return flag;}
        public void setTrue() {flag = true;}
    }

    int[] originalData;
    double primaryFailRate;
    double backupFailRate;
    long timeout;
    Adjudicator adjudicator;

    public Executive(int[] originalData, double primaryFailRate, double backupFailRate, long timeout) {
        this.originalData = originalData;
        this.primaryFailRate = primaryFailRate;
        this.backupFailRate = backupFailRate;
        this.timeout = timeout;

        this.adjudicator = new Adjudicator(originalData);
    }

    private int[] runPrimary() {
        final int[] primaryInput = Arrays.copyOf(originalData, originalData.length);
        final SuccessFlag successFlag = new SuccessFlag();
        Thread primaryThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Heapsort.sort(primaryInput, primaryFailRate);
                    successFlag.setTrue();
                } catch (RuntimeException re) {
                    System.out.println(re);
                }
            }
        });

        Timer timer = new Timer();
        timer.schedule(new Watchdog(primaryThread), timeout);
        primaryThread.start();
        try {
            primaryThread.join();
            timer.cancel();
            if (successFlag.success() && adjudicator.acceptanceTest(primaryInput))
                return primaryInput;
            else
                return runBackup();
        } catch (InterruptedException ie) {
            System.out.println("Primary thread was interrupted: " + ie.getMessage());
            return runBackup();
        }
    }

    private int[] runBackup() {
        final int[] backupInput = Arrays.copyOf(originalData, originalData.length);
        final SuccessFlag successFlag = new SuccessFlag();
        Thread backupThread = new Thread(new Runnable() {
            public void run() {
                try {
                    InsertionSort.sort(backupInput, backupFailRate);
                    successFlag.setTrue();
                } catch (RuntimeException re) {
                    System.out.println(re);
                }
            }
        });

        FailureException fe = new FailureException("Both the primary variant and the backup failed");

        Timer timer = new Timer();
        timer.schedule(new Watchdog(backupThread), timeout);
        backupThread.start();

        try {
            backupThread.join();
            timer.cancel();
            if (successFlag.success() && adjudicator.acceptanceTest(backupInput)) {
                return backupInput;
            } else {
                throw fe;
            }
        } catch (InterruptedException ie) {
            System.out.println("Backup thread was interrupted: " + ie.getMessage());
            throw fe;
        }

    }

    public int[] runSort() {

        return runPrimary();
    }
}
