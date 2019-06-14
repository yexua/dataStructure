package com.at.search;

/**
 * 二分查找
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(arr, 4));
    }
}
