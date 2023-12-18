package lab03;

public class QueueNode implements StackQueue{
    private Node frontNode;
    private Node lastNode;
    private int capacity;
    private int count;
    //private int frontIndex;
    QueueNode(int capacity) {
        this.frontNode = null;
        this.capacity = capacity;
        this.count = 0;
        //this.frontIndex = 0;
    }
    @Override
    public boolean isEmpty() {
        return frontNode == null;
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
        Node newNode = new Node(value);
        if (frontNode == null) {
            frontNode = newNode;
            lastNode = newNode;
        }
        else {
            lastNode.previousNode = newNode;
            lastNode = newNode;
        }
        count++;
    }
    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("Queue empty!");
            return;
        }
        frontNode = frontNode.previousNode;
        count--;
    }
    @Override
    public int top() {
        if (isEmpty()) return 0;
        return frontNode.getValue();
    }
    @Override
    public int[] getAll() {
        int[] returnQueue = new int[count];
        Node currentNode = frontNode;
        for (int i = 0; i < count; i++) {
            returnQueue[i] = currentNode.getValue();
            currentNode = currentNode.previousNode;
        }
        return returnQueue;
    }
}
