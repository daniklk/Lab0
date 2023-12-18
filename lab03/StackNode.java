package lab03;

public class StackNode implements StackQueue {
    Node top;
    int capacity;
    int count;
    StackNode(int capacity) {
        this.top = null;
        this.capacity = capacity;
        this.count = 0;
    }
    @Override
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack full!");
            return;
        }
        Node node = new Node(value);
        node.previousNode = top;
        top = node;
        count++;
    }
    @Override
    public void pop() {
        if (isEmpty()) {
            System.out.println("Stack empty!");
            return;
        }
        top = top.previousNode;
        count--;
    }
    @Override
    public int top() {
        if (isEmpty()) {
            System.out.println("Stack empty!");
            return 0;
        }
        return top.getValue();
    }
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    @Override
    public boolean isFull() {
        return count == capacity;
    }
    @Override
    public int[] getAll() {
        int[] stack = new int[count];
        Node currentNode = top;
        for (int i = count - 1; i >= 0; i--) {
            stack[i] = currentNode.getValue();
            currentNode = currentNode.previousNode;
        }
        return stack;
    }
}
