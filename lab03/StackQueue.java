package lab03;

public interface StackQueue {
    public void push(int value);
    public void pop();
    public int top();
    public int[] getAll();
    public boolean isEmpty();
    public boolean isFull();

}
