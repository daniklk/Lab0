package lab02;

public class InputThread extends Thread{
    private Application app;
    public InputThread(Application app) {
        this.app = app;
    }
    @Override
    public void run() {
        while (!this.app.shouldClose) {
            String cmd = this.app.getScanner().nextLine();
            this.app.processOperations(cmd);
        }
    }
}