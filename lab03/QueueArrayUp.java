package lab03;

public class QueueArrayUp implements StackQueue{
    private int[] queue;
    private int capacity;
    private int count;
    private int frontIndex;
    QueueArrayUp(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
        this.frontIndex = 0;
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
    public void push(int value) {
        if (isFull()) {
            System.out.println("Queue full!");
            return;
        }
        queue[(frontIndex + count) % capacity] = value;
        count++;
    }
    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("Queue empty!");
            return;
        }
        frontIndex = (frontIndex + 1) % capacity;
        count--;
    }
    @Override
    public int top() {
        if (isEmpty()) return 0;
        return queue[frontIndex];
    }
    @Override
    public int[] getAll() {
        int[] returnQueue = new int[count];
        for (int i = 0; i < count; i++) {
            returnQueue[i] = queue[(frontIndex + i) % capacity];
        }
        return returnQueue;
    }
}
