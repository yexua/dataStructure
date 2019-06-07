package com.at.sparseArray;

public class SparseArray {

    public static void main(String[] args) {
        int[][] arr = new int[10][10];
        arr[0][1] = 1;
        arr[1][4] = 5;
        arr[2][3] = 6;
        int[][] sparseArr = arrayToSparse(arr);
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        int[][] a = sparseToArr(sparseArr);
        for (int[] i : a) {
            for (int val : i) {
                System.out.printf("%d\t", val);
            }
            System.out.println();
        }
    }

    public static int[][] arrayToSparse(int[][] arr) {
        int valNum = 0;
        for (int[] i : arr) {
            for (int val : i) {
                if (val != 0) {
                    valNum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArr = new int[valNum + 1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = valNum;
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                    count++;
                }
            }
        }
        return sparseArr;
    }

    public static int[][] sparseToArr(int[][] sparseArr) {
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }
}
