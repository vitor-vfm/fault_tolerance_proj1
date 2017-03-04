import java.util.*;

public class Adjudicator {

    Set<Integer> originalElements;

    public Adjudicator(int[] original) {
        originalElements = new HashSet<Integer>();
        for (Integer i : original)
            originalElements.add(i);
    }

    public boolean acceptanceTest(int[] sorted_array) {
        if (sorted_array.length == 0) return true;

        Set<Integer> elements = new HashSet<Integer>(sorted_array.length);
        elements.add(sorted_array[0]);

        for (int i = 1; i < sorted_array.length; i++) {

            // verify monotonicity
            if (sorted_array[i] < sorted_array[i-1])
                return false;

            elements.add(sorted_array[i]);
        }

        return originalElements.equals(elements);
    }
}
