#include <stdio.h>

#include "InsertionSort.h"

int insertsort(int arr[], int size) {
    // Implementation of insertion_sort according to Cormen et al., Introduction to Algorithms,
    // Third Edition. Returns number of accesses

    int i, j, key;
    int count = 0;

    for (j = 1; j < size; j++) {
        key = arr[j]; count++;

        // insert arr[j] into the sorted sequence arr[0:j)
        for (i = j-1; (i >= 0) && (arr[i] > key); i--) {
            arr[i+1] = arr[i]; count += 2;
        }
        arr[i+1] = key; count++;
    }

    return count;

}

JNIEXPORT jint JNICALL Java_InsertionSort_insertsort (JNIEnv * env, jclass class, jintArray arr) {
    int size = (*env)->GetArrayLength(env, arr);
    int * body = (*env)->GetIntArrayElements(env, arr, 0);

    jint result = insertsort(body, size);
    (*env)->ReleaseIntArrayElements(env, arr, body, 0);

    return result;
}

