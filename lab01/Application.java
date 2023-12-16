package lab01;
import java.util.Scanner;

public class Application {
    private Scanner scanner;
    private University university;
    private String command;
    private boolean shouldClose;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.command = "";
        this.shouldClose = false;
    }
    protected void finalize() {
        scanner.close();
    }
    public void run() {
        while (!shouldClose) {
            printCommands();
            String cmd = getCommand();
            String[] commands;
            commands = cmd.split("/");
            switch (commands[0]) {
                case "nf":
                    printStudyFields();
                    int studyFieldIndex = getStudyFieldIndexFromIn();
                    university.addFaculty(new Faculty(commands[1], commands[2], StudyField.values()[studyFieldIndex]));
                    break;
                case "ss":
                    if (commands.length < 2) {
                        System.out.println("Invalid command!");
                        break;
                    }
                    university.searchStudentByEmail(commands[1]);
                    break;
                case "df":
                    System.out.println(this.university);
                    break;
                case "dff":
                    printStudyFields();
                    int index = getStudyFieldIndexFromIn();
                    this.university.printFacultyByStudyField(index);
                    break;
                case "fo":
                    System.out.println("Select a faculty: \n");
                    university.printFacultiesAsChoiceMenu();
                    int facultyIndex = getValidatedIndexFromIn(0, this.university.getFaculties().size() - 1);
                    facultyOperationsLoop(facultyIndex);
                    break;
                case "b":
                    break;
                case "q":
                    System.out.println("Exiting program...");
                    shouldClose = true;
                    break;
                default:
                    System.out.println("Invalid command!\n-------------------------------------------");
                    break;
            }
        }
        FileManager.saveDataToFile(university.getFaculties());
    }
    private void facultyOperationsLoop(int facultyIndex) {
        boolean back = false;
        while (!back) {
            printFacultyOperations();
            int operation = getValidatedIndexFromIn(1, 6);
            this.scanner.nextLine();
            switch (operation) {
                case 1:
                    String firstName, lastName, email, enrollmentDate, birthDate;
                    boolean graduated;
                    System.out.println("First Name:");
                    firstName = this.scanner.nextLine();
                    System.out.println("Last Name:");
                    lastName = this.scanner.nextLine();
                    System.out.println("Email:");
                    email = this.scanner.nextLine();
                    System.out.println("Enrollment Date:");
                    enrollmentDate = this.scanner.nextLine();
                    System.out.println("Date Of Birth:");
                    birthDate = this.scanner.nextLine();
                    System.out.println("graduated (true or false):");
                    graduated = this.scanner.nextBoolean();
                    this.scanner.nextLine();

                    this.university.getFaculties().get(facultyIndex).createStudent(firstName, lastName, email, enrollmentDate,
                            birthDate, graduated);
                    break;
                case 2:
                    System.out.println("First Name:");
                    firstName = this.scanner.nextLine();
                    System.out.println("Last Name:");
                    lastName = this.scanner.nextLine();
                    this.university.getFaculties().get(facultyIndex).graduateStudent(firstName, lastName);
                    break;
                case 3:
                    this.university.getFaculties().get(facultyIndex).printStudents(false);
                    break;
                case 4:
                    this.university.getFaculties().get(facultyIndex).printStudents(true);
                    break;
                case 5:
                    System.out.println("First Name:");
                    firstName = this.scanner.nextLine();
                    System.out.println("Last Name:");
                    lastName = this.scanner.nextLine();
                    this.university.getFaculties().get(facultyIndex).checkStudentEnrollment(firstName, lastName);
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
        }
    }
    private String getCommand() {
        String cmd;
        System.out.print("->");
        cmd = this.scanner.nextLine();
        return cmd;
    }
    private void printCommands() {
        System.out.println("General Operations");
        System.out.println("nf/<faculty name>/<faculty abbreviation>/ -------- Create faculty\n" +
                "ss/<student email> ------------------------------- Search student and show faculty\n" +
                "df ----------------------------------------------- Display all faculties\n" +
                "dff ---------------------------------------------- Display all faculties of a field\n" +
                "fo ----------------------------------------------- Faculty operations\n" +
                "b ------------------------------------------------ Go back\n" +
                "q ------------------------------------------------ Quit");
    }
    private void printFacultyOperations() {
        System.out.println("Faculty Operations:");
        System.out.println("1. Create student\n" +
                "2. Graduate student\n" +
                "3. Display enrolled students\n" +
                "4. Display graduated students\n" +
                "5. Check if student is enrolled\n" +
                "6. Go back\n");
    }
    private void printStudyFields() {
        for (int i = 0; i < StudyField.values().length; i++)
            System.out.println(StudyField.values()[i] + " - " + i);
    }
    private int getStudyFieldIndexFromIn() {
        int studyFieldIndex;
        boolean validIndex = false;
        do {
            System.out.print("->");
            studyFieldIndex = this.scanner.nextInt();
            this.scanner.nextLine();
            if (studyFieldIndex < 0 || studyFieldIndex > StudyField.values().length)
                System.out.println("Invalid Study Field Index!");
            else
                validIndex = true;
        } while (!validIndex);
        return studyFieldIndex;
    }
    private int getValidatedIndexFromIn(int a, int b) {
        int index;
        boolean valid = false;
        do {
            index = this.scanner.nextInt();
            if (index < a || index > b) System.out.println("Invalid Index, please enter again:");
            else valid = true;
        } while (!valid);
        return index;
    }
}
