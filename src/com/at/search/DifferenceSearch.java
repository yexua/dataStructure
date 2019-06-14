package com.at.search;

/**
 * 插值查找
 * 插值查找与二分查找的区别只在于mid的计算
 * 二分：middle = （low + hight）/2 = low + 1/2 * (hight - low)
 * 插值：middle = low + (a - array[low]) / (array[hight] - array[low]) * (hight - low)
 */
public class DifferenceSearch {
    public static int diffSearch(int[] arr, int target) {
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int count = 0;
        while (left <= right) {
//        while (arr[left] != arr[right] && target >= arr[left] && target <= arr[right]) {
            count++;
//            int mid = left + ((right - left) >> 1);
            long l = (long) (right - left) * (target - arr[left]);
            int mid = (int) (left + l / (arr[right] - arr[left]));
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                System.out.println("查找：" + count + "次");
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[999999];
        for (int i = 0; i < 999999; i++) {
            arr[i] = i + 1;
        }
        long l = System.currentTimeMillis();
        System.out.println(diffSearch(arr, 666666));
        System.out.println(System.currentTimeMillis() - l);
    }
}
