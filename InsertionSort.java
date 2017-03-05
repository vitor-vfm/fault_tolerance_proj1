import java.util.*;

public class InsertionSort {

    private static int insert_sort(int[] numbers, double failureRate) {
        // TODO: dummy; make native
        Arrays.sort(numbers);
        return (new Random().nextDouble() <= failureRate/100.0) ? 1 : 0;
    }

    public static void sort(int[] numbers, double failureRate) {

        int result = insert_sort(numbers, failureRate);
        if (result != 0) {
            throw new RuntimeException("Memory access error in InsertionSort");
        }
    }
}
