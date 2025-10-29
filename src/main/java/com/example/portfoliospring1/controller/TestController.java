package com.example.portfoliospring1.controller;

import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private Boolean isPrime(int n) {
        // 과제1. 에라토스테네스의 체 방식으로 구현
        if (n < 2) return false;
        boolean[] isPrimeArr = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrimeArr[i] = true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (isPrimeArr[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrimeArr[j] = false;
                }
            }
        }
        return isPrimeArr[n];
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    @GetMapping("/check-prime-number")
    public String checkPrimeNumber(@RequestParam Integer number) {
        if (isPrime(number)) { // 소수 체크 로직
            return number + "는 소수가 맞습니다.";
        }
        return number + "는 소수가 아닙니다.";
    }

    // O(n^2)
    @GetMapping("/bubble-sort")
    public String bubbleSort() {
        int[] array = { 5,3,8,4,2,9,7,1 };

        System.out.println("최초 함수");
        printArray(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println("");
        System.out.println("최종 정렬 함수");
        printArray(array);

        return Arrays.toString(array);
    }

    // O(N*logN)
    @GetMapping("/merge-sort")
    public String mergeSort() {
        int[] array = { 5,3,8,4,2,9,7,1 };

        System.out.println("최초 함수");
        printArray(array);

        // 과제 2. 머지 소트 로직
        mergeSortLogic(array, 0, array.length - 1);

        System.out.println("");
        System.out.println("최종 정렬 함수");
        printArray(array);

        return Arrays.toString(array);
    }

    private void mergeSortLogic(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortLogic(array, left, mid);
            mergeSortLogic(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++) leftArr[i] = array[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }
        while (i < n1) array[k++] = leftArr[i++];
        while (j < n2) array[k++] = rightArr[j++];
    }

    // O(N*logN)
    @GetMapping("/quick-sort")
    public String quickSort() {
        int[] array = { 5,3,8,4,2,9,7,1 };

        System.out.println("최초 함수");
        printArray(array);

        // 옵션 퀵 소트 로직
        quickSortLogic(array, 0, array.length - 1);

        System.out.println("");
        System.out.println("최종 정렬 함수");
        printArray(array);

        return Arrays.toString(array);
    }

    private void quickSortLogic(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortLogic(array, low, pivotIndex - 1);
            quickSortLogic(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

}