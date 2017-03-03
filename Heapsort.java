public class Heapsort {

    // Implementation of heapsort according to Cormen et al., Introduction to Algorithms,
    // Third Edition

    private static int parent(int i) {
        return (i-1) / 2;
    }

    private static int left(int i) {
        return 2*i + 1;
    }

    private static int right(int i) {
        return 2*i + 2;
    }

    private static void maxHeapify(int[] heap, int heapSize, int i) {
        int l = left(i);
        int r = right(i);

        int indexOfLargest = i;

        if (l < heapSize && heap[l] > heap[indexOfLargest])
            indexOfLargest = l;

        if (r < heapSize && heap[r] > heap[indexOfLargest])
            indexOfLargest = r;

        if (indexOfLargest != i) {
            //swap
            int tmp = heap[indexOfLargest];
            heap[indexOfLargest] = heap[i];
            heap[i] = tmp;

            maxHeapify(heap, heapSize, indexOfLargest);
        }

    }

    private static void buildMaxHeap(int[] array) {
        for (int i = (array.length/2 - 1); i >= 0; i--) {
            maxHeapify(array, array.length, i);
        }
    }

    public static void sort(int[] numbers) {
        int tmp;
        buildMaxHeap(numbers);
        int heapSize = numbers.length;
        for (int i = (numbers.length - 1); i >= 1; i--) {
            // swap
            tmp = numbers[i];
            numbers[i] = numbers[0];
            numbers[0] = tmp;

            maxHeapify(numbers, --heapSize, 0);
        }

    }

}
