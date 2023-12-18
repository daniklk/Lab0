package lab03;
import java.util.Arrays;

public class StackArrayUp implements StackQueue {
    private int[] stack;
    private int capacity;
    private int count;
    StackArrayUp(int capacity) {
        this.capacity = capacity;
        this.stack = new int[capacity];
        this.count = 0;
    }
    @Override
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack full!");
            return;
        }
        stack[count] = value;
        count++;
    }
    @Override
    public void pop() {
        if(isEmpty()) {
            System.out.println("Stack empty!");
            return;
        }
        count--;
    }
    @Override
    public int top() {
        if(isEmpty()) {
            System.out.println("Stack empty!");
            return 0;
        }
        return stack[count - 1];
    }
    @Override
    public boolean isEmpty() {
        return count == 0;
    }
    @Override
    public boolean isFull() {
        return count == capacity;
    }
    @Override
    public int[] getAll() {
        return Arrays.copyOfRange(stack, 0, count);
    }
}
