package com.at.queue;

public class ArrayQueue {
    private int maxSize; // 数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 存放数据，模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean add(int val) {
        if (isFull()) {
            throw new RuntimeException("");
        }
        arr[rear] = val;
        rear = (rear + 1) % maxSize;
        return true;
    }
}
