import java.util.Random;

public class Heapsort {

    // Implementation of heapsort according to Cormen et al., Introduction to Algorithms,
    // Third Edition

    private static class Heap {
        // a heap that counts
        // the number of memory accesses
        int count = 0;
        int[] arr;

        Heap(int[] arr) {
            this.arr = arr;
            this.count = 0;
        }

        public int get(int index) {
            count++;
            return arr[index];
        }

        public void set(int index, int value) {
            count++;
            arr[index] = value;
        }

        public void swap(int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
            count += 4;
        }

        public int size() {
            return arr.length;
        }
    }



    private static int parent(int i) {
        return (i-1) / 2;
    }

    private static int left(int i) {
        return 2*i + 1;
    }

    private static int right(int i) {
        return 2*i + 2;
    }

    private static void maxHeapify(Heap heap, int heapSize, int i) {
        int l = left(i);
        int r = right(i);

        int indexOfLargest = i;

        if (l < heapSize && heap.get(l) > heap.get(indexOfLargest))
            indexOfLargest = l;

        if (r < heapSize && heap.get(r) > heap.get(indexOfLargest))
            indexOfLargest = r;

        if (indexOfLargest != i) {
            heap.swap(indexOfLargest, i);

            maxHeapify(heap, heapSize, indexOfLargest);
        }

    }

    private static void buildMaxHeap(Heap array) {
        for (int i = (array.size()/2 - 1); i >= 0; i--) {
            maxHeapify(array, array.size(), i);
        }
    }

    public static void sort(int[] originalNumbers, double failureProbability) {
        int tmp;
        Heap numbers = new Heap(originalNumbers);
        buildMaxHeap(numbers);
        int heapSize = numbers.size();
        for (int i = (numbers.size() - 1); i >= 1; i--) {
            numbers.swap(0, i);
            maxHeapify(numbers, --heapSize, 0);
        }

        runFailureSimulation(numbers.count, failureProbability);
    }

    private static void runFailureSimulation(double count, double failureProbability) {
        double hazard = count * failureProbability;
        double result = new Random().nextDouble();
        if (0.5 <= result && result <= (0.5 + hazard)) {
            throw new RuntimeException("Memory access error in Heapsort");
        }
    }
}
