import java.util.*;

public class InsertionSort {

    static native int insertsort(int[] numbers);

    public static void sort(int[] numbers, double failureRate) {

        System.loadLibrary("insertionsort");
        int accesses = insertsort(numbers);

        runMemoryFailureSimulation(accesses, failureRate);
    }

    private static void runMemoryFailureSimulation(int accesses, double failureRate) {
        double hazard = accesses * failureRate;
        double result = new Random().nextDouble();
        if (0.5 <= result && result <= (0.5 + hazard)) {
            throw new RuntimeException("Memory access error in InsertionSort");
        }
    }
}
