#include <stdio.h>

void insert_sort(int arr[], int size) {
    // Implementation of insertion_sort according to Cormen et al., Introduction to Algorithms,
    // Third Edition

    int i, j, key;

    for (j = 1; j < size; j++) {
        key = arr[j];

        // insert arr[j] into the sorted sequence arr[0:j)
        for (i = j-1; (i >= 0) && (arr[i] > key); i--) {
            arr[i+1] = arr[i];
        }
        arr[i+1] = key;
    }

}
