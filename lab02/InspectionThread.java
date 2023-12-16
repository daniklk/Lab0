package lab02;

public class InspectionThread extends Thread{
    private Application app;
    public InspectionThread(Application app) {
        this.app = app;
    }
    @Override
    public void run() {
        while (!this.app.shouldClose) {
            app.commit();
            app.getCurrentSnapshot().printFilesStatus();
            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) { }
        }
    }
}
