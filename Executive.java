import java.util.*;

public class Executive {

    int[] originalData;
    double primaryFailRate;
    double backupFailRate;

    public Executive(int[] originalData, double primaryFailRate, double backupFailRate) {
        this.originalData = originalData;
        this.primaryFailRate = primaryFailRate;
        this.backupFailRate = backupFailRate;
    }

    public int[] runSort() {

        // run primary
        final int[] primaryInput = Arrays.copyOf(originalData, originalData.length);
        Thread primaryThread = new Thread(() -> Heapsort.sort(primaryInput, primaryFailRate));
        primaryThread.start();

        try {
            primaryThread.join();
        } catch (InterruptedException ie) {
            throw new RuntimeException("Thread was interrupted: " + ie.getMessage());
        }


        return primaryInput;
    }
}
