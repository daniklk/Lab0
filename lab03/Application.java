package lab03;
import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private boolean shouldClose;
    Application() {
        this.scanner = new Scanner(System.in);
        shouldClose = false;
    }
    public void run() {
        while(!shouldClose) {
            printGeneralCommands();
            System.out.println("->");
            int commandIndex = scanner.nextInt();

            int capacity = 0;
            StackQueue structure;
            if (commandIndex >= 0 && commandIndex < 6) {
                System.out.println("Enter capacity:");
                capacity = scanner.nextInt();
            }
            switch (commandIndex) {
                case 0:
                    structure = new StackArrayUp(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 1:
                    structure = new StackArrayDown(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 2:
                    structure = new StackNode(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 3:
                    structure = new QueueArrayUp(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 4:
                    structure = new QueueArrayDown(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 5:
                    structure = new QueueNode(capacity);
                    stackQueueCommandsLoop(structure);
                    break;
                case 6:
                    quit();
                    break;
                default:
                    System.out.println("Invalid command index!");
                    break;
            }
        }
    }
    private void quit() {
        shouldClose = true;
    }
    private void printGeneralCommands() {
        System.out.println("0 - StackArrayUp\n" +
                           "1 - StackArrayDown\n" +
                           "2 - StackNode\n" +
                           "3 - QueueArrayUp\n" +
                           "4 - QueueArrayDown\n" +
                           "5 - QueueNode\n" +
                           "6 - Quit\n" +
                "-----------------------------------------");
    }
    private void printStackQueueCommands() {
        System.out.println("0 - Push\n" +
                "1 - Pop\n" +
                "2 - Top\n" +
                "3 - PrintAll\n" +
                "4 - isEmpty\n" +
                "5 - isFull\n" +
                "6 - Back\n" +
                "-----------------------------------------");
    }
    private void stackQueueCommandsLoop(StackQueue structure) {
        boolean goBack = false;
        while (!goBack) {
            printStackQueueCommands();
            System.out.println("->");
            int commandIndex = scanner.nextInt();
            switch (commandIndex) {
                case 0:
                    System.out.println("Enter element:");
                    int value = scanner.nextInt();
                    structure.push(value);
                    break;
                case 1:
                    structure.pop();
                    break;
                case 2:
                    System.out.println(structure.top());
                    break;
                case 3:
                    printStackQueueElements(structure);
                    break;
                case 4:
                    System.out.println(structure.isEmpty());
                    break;
                case 5:
                    System.out.println(structure.isFull());
                    break;
                case 6:
                    goBack = true;
                    break;
                default:
                    System.out.println("Invalid command index!");
                    break;
            }
        }
    }
    private void printStackQueueElements(StackQueue structure) {
        int[] elements = structure.getAll();
        for (int i = elements.length - 1; i >= 0; i--) {
            System.out.println(elements[i]);
        }
    }
}
