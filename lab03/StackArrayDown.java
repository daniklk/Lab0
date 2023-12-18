package lab03;

import java.util.Arrays;

public class StackArrayDown implements StackQueue {

    private int[] stack;
    private int capacity;
    private int free;
    StackArrayDown(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.free = capacity;
    }
    @Override
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack full!");
            return;
        }
        stack[free - 1] = value;
        free--;
    }
    @Override
    public void pop() {
        if(isEmpty()) {
            System.out.println("Stack empty!");
            return;
        }
        free++;
    }
    @Override
    public int top() {
        if(isEmpty()) {
            System.out.println("Stack empty!");
            return 0;
        }
        return stack[free];
    }
    @Override
    public boolean isEmpty() {
        return free == capacity;
    }
    @Override
    public boolean isFull() {
        return free == 0;
    }
    @Override
    public int[] getAll() {
        return Arrays.copyOfRange(stack, free, capacity);
    }
}
