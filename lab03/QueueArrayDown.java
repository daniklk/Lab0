package lab03;

public class QueueArrayDown implements StackQueue {
    private int[] queue;
    private int capacity;
    private int free;
    private int frontIndex;
    QueueArrayDown(int capacity) {
        this.queue = new int[capacity];
        this.capacity = capacity;
        this.free = capacity;
        this.frontIndex = capacity - 1;
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
    public void push(int value) {
        if (isFull()) {
            System.out.println("Queue full!");
            return;
        }
        // in JAVA  % operator returns the remainder not the modulus, the [+ capacity] is there to ensure we get the modulus
        queue[(frontIndex - (capacity - free) + capacity) % capacity] = value;
        free--;
    }
    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("Queue empty!");
            return;
        }
        frontIndex = (frontIndex - 1 + capacity) % capacity;
        free++;
    }
    @Override
    public int top() {
        if (isEmpty()) return 0;
        return queue[frontIndex];
    }
    @Override
    public int[] getAll() {
        int[] returnQueue = new int[capacity - free];
        for (int i = 0; i < capacity - free; i++) {
            returnQueue[i] = queue[(frontIndex - i + capacity) % capacity];
        }
        return returnQueue;
    }
}
