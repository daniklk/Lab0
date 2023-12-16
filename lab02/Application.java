package lab02;
import java.util.Scanner;

public class Application {
    // ANSI escape code constants for text colors
    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String BLUE = "\u001B[34m";
    public static String YELLOW = "\u001B[33m";

    private InspectionThread inspectionThread = new InspectionThread(this);
    private InputThread inputThread = new InputThread(this);
    private Scanner scanner;
    private Snapshot lastSnapshot;
    private Snapshot currentSnapshot;
    boolean shouldClose = false;
    public Application() {
        this.scanner = new Scanner(System.in);
        this.lastSnapshot = null;
        this.currentSnapshot = null;



        inspectionThread.start();
        inputThread.start();
    }
    public void run() {
        while (!shouldClose) {
            //printOperations();
            //String cmd = scanner.nextLine();
            //processOperations(cmd);

        }
    }
    public void processOperations(String cmd) {
        String[] cmds = cmd.split(" ");

        switch (cmds[0]) {
            case "commit":
                commit();
                break;
            case "status":
                if (this.currentSnapshot == null) {
                    System.out.println("ERROR: No commit available!");
                    break;
                }
                this.currentSnapshot.printFilesStatus();
                break;
            case "info":
                if (cmds.length != 2 || cmds[1] == null)
                {
                    System.out.println("ERROR: Invalid command!");
                    break;
                }
                int index = this.currentSnapshot.searchFileByName(cmds[1]);
                if (index == -1)
                    System.out.println("WARNING: No such file present!");
                else
                    this.currentSnapshot.getFiles().get(index).printInformation();
                break;
            case "quit":
                System.out.println("Exiting program...");
                inspectionThread.interrupt();
                inputThread.interrupt();
                this.shouldClose = true;
                break;
            default:
                System.out.println("ERROR: Invalid command!");
                break;
        }
    }
    private void printOperations() {
        System.out.println("COMMANDS");
        System.out.println("-----------------------------------------");
        System.out.println("1.commit");
        System.out.println("2.status");
        System.out.println("3.info");
        System.out.println("4.quit");
        System.out.println("-->");
    }
    public void commit() {
        this.lastSnapshot = this.currentSnapshot;
        this.currentSnapshot = new Snapshot();
        if (this.lastSnapshot != null && this.currentSnapshot != null)
            compareAndUpdateFilesStatus();
    }
    private void compareAndUpdateFilesStatus() {
        for (int i = 0; i < lastSnapshot.getFiles().size(); i++) {
            boolean matchFound = false;
            for (int j = 0; j < currentSnapshot.getFiles().size(); j++) {
                if (lastSnapshot.getFiles().get(i).getStatus().equals("Deleted"))
                    return;
                if (lastSnapshot.getFiles().get(i).getFileName().equals(currentSnapshot.getFiles().get(j).getFileName())) {
                    //System.out.println("File match found!");
                    matchFound = true;
                    updateModifiedStatus(lastSnapshot.getFiles().get(i), currentSnapshot.getFiles().get(j));
                    break;
                }
            }
            if (!matchFound) {
                lastSnapshot.getFiles().get(i).setStatus("Deleted");
                currentSnapshot.getFiles().add(lastSnapshot.getFiles().get(i));
            }

        }
    }
    private void updateModifiedStatus(MyFile oldFile, MyFile newFile) {
        if (oldFile.getModifiedTime().equals(newFile.getModifiedTime()))
            newFile.setStatus("No Change");
        else
            newFile.setStatus("Changed");
    }
    public Scanner getScanner() { return this.scanner; }
    public Snapshot getCurrentSnapshot() { return this.currentSnapshot; }
}


