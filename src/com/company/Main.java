package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Array size\t| Selection Sort \t Bubble Sort \t Merge Sort \t Quick Sort");
        Test(50_000);
        Test(100_000);
        Test(150_000);
        Test(200_000);
        Test(250_000);
        Test(300_000);

    }

    public static void Test(int n){
        long [] times = new long[4];
        int [] arr = new int[n];
        for(int i=0;i<n;++i){
            arr[i] = (int)(Math.random()*2000000000);
        }

        {
            int[] k = Arrays.copyOf(arr, n);

            long start = System.currentTimeMillis();
            bubbleSort(k);
            times[0] = System.currentTimeMillis() - start;
        }

        {
            int[] k = Arrays.copyOf(arr, n);

            long start = System.currentTimeMillis();
            selectionSort(k);
            times[1] = System.currentTimeMillis() - start;
        }

        {
            int[] k = Arrays.copyOf(arr, n);

            long start = System.currentTimeMillis();
            mergeSort(k);
            times[2] = System.currentTimeMillis() - start;
        }

        {
            int[] k = Arrays.copyOf(arr, n);

            long start = System.currentTimeMillis();
            quickSort(k);
            times[3] = System.currentTimeMillis() - start;
        }

        System.out.printf("%d\t\t| \t %10d \t %10d \t %10d \t %10d\n", n, times[0], times[1], times[2], times[3]);
    }

    /**Bubble Sort */

    public static void bubbleSort(int[] list) {
        for (int k = 0; k < list.length - 1; k++) {
            for (int i = 0; i < list.length - 1 - k; i++) {
                if (list[i] > list[i + 1]) {
                    // Swap list[i] with list[i + 1]
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }
            }
        }
    }

    /**Selection Sort */

    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
// Find the minimum in the list[i..list.length-1]
            int currentMin = list[i];
            int currentMinIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
// Swap list[i] with list[currentMinIndex] if necessary
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    /**Merge Sort */

    public static void mergeSort(int[] list) {
        if (list.length > 1) {
// Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);
// Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
// Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }

    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }
        while (current1 < list1.length)
            temp[current3++] = list1[current1++];
        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /**Quick Sort */
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        while (high > low) {
// Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;
//Search backward from right
            while (low <= high && list[high] > pivot)
                high--;
//Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot)
            high--;
//Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }
}
