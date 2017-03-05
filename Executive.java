import java.util.*;

public class Executive {

    private class SuccessFlag {
        boolean flag = true;

        public boolean success() {return flag;}
        public void setFalse() {flag = false;}
    }

    int[] originalData;
    double primaryFailRate;
    double backupFailRate;
    Adjudicator adjudicator;

    public Executive(int[] originalData, double primaryFailRate, double backupFailRate) {
        this.originalData = originalData;
        this.primaryFailRate = primaryFailRate;
        this.backupFailRate = backupFailRate;
        this.adjudicator = new Adjudicator(originalData);
    }

    private int[] runPrimary() {
        final int[] primaryInput = Arrays.copyOf(originalData, originalData.length);
        final SuccessFlag successFlag = new SuccessFlag();
        Thread primaryThread = new Thread(new Runnable() {
            public void run() {
                try {
                    Heapsort.sort(primaryInput, primaryFailRate);
                } catch (RuntimeException re) {
                    System.out.println(re);
                    successFlag.setFalse();
                }
            }
        });

        try {
            primaryThread.start();
            primaryThread.join();
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
                } catch (RuntimeException re) {
                    System.out.println(re);
                    successFlag.setFalse();
                }
            }
        });

        FailureException fe = new FailureException("Both the primary variant and the backup failed");

        try {
            backupThread.start();
            backupThread.join();
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
