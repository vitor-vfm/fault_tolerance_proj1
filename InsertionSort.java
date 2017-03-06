import java.util.*;

public class InsertionSort {

    static native int insertsort(int[] numbers, double failureRate);

    public static void sort(int[] numbers, double failureRate) {

        System.loadLibrary("insertionsort");
        int result = insertsort(numbers, failureRate);
        System.out.println(result);
        if (result != 0) {
            throw new RuntimeException("Memory access error in InsertionSort");
        }
    }
}
