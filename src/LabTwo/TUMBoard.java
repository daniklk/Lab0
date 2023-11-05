import java.util.*;
import java.text.SimpleDateFormat;

enum StudyField {
    MECHANICAL_ENGINEERING,
    SOFTWARE_ENGINEERING,
    FOOD_TECHNOLOGY,
    URBANISM_ARCHITECTURE,
    VETERINARY_MEDICINE
}

class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;

    public Student(String firstName, String lastName, String email, Date enrollmentDate, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public boolean isGraduated() {
        // Implement this method based on your graduation criteria
        return false;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Student: " + firstName + " " + lastName + "\nEmail: " + email + "\nEnrollment Date: " + dateFormat.format(enrollmentDate) + "\nDate of Birth: " + dateFormat.format(dateOfBirth);
    }
}

class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void assignStudent(Student student) {
        students.add(student);
    }

    public void graduateStudent(Student student) {
        students.remove(student);
    }

    public List<Student> getEnrolledStudents() {
        List<Student> enrolledStudents = new ArrayList<>();
        for (Student student : students) {
            if (!student.isGraduated()) {
                enrolledStudents.add(student);
            }
        }
        return enrolledStudents;
    }

    public List<Student> getGraduates() {
        List<Student> graduates = new ArrayList<>();
        for (Student student : students) {
            if (student.isGraduated()) {
                graduates.add(student);
            }
        }
        return graduates;
    }

    public boolean isStudentInFaculty(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Faculty: " + name + " (" + abbreviation + ")\nStudy Field: " + studyField;
    }
}

public class TUMBoard {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Faculty> faculties = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createFaculty();
                    break;
                case 2:
                    searchFacultyByStudent();
                    break;
                case 3:
                    displayUniversityFaculties();
                    break;
                case 4:
                    displayFacultiesByStudyField();
                    break;
                case 5:
                    createStudent();
                    break;
                case 6:
                    assignStudentToFaculty();
                    break;
                case 7:
                    scanner.close(); // Close the Scanner before exiting
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("TUM Board - Main Menu");
        System.out.println("1. Create a new faculty");
        System.out.println("2. Search for a faculty by student email");
        System.out.println("3. Display University faculties");
        System.out.println("4. Display faculties belonging to a study field");
        System.out.println("5. Create a new student");
        System.out.println("6. Assign a student to a faculty");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createFaculty() {
        System.out.print("Enter the name of the faculty: ");
        String name = scanner.nextLine();

        System.out.print("Enter the abbreviation of the faculty: ");
        String abbreviation = scanner.nextLine();

        System.out.println("Select a study field:");
        for (StudyField field : StudyField.values()) {
            System.out.println(field.ordinal() + ". " + field);
        }
        int studyFieldIndex = scanner.nextInt();
        StudyField studyField = StudyField.values()[studyFieldIndex];

        Faculty faculty = new Faculty(name, abbreviation, studyField);
        faculties.add(faculty);

        System.out.println("Faculty created successfully.");
    }

    private static void searchFacultyByStudent() {
        System.out.print("Enter the student's email: ");
        String email = scanner.nextLine();

        for (Faculty faculty : faculties) {
            if (faculty.isStudentInFaculty(email)) {
                System.out.println("The student with email " + email + " belongs to the following faculty:");
                System.out.println(faculty);
                return;
            }
        }
        System.out.println("No faculty found for the student with email " + email);
    }

    private static void displayUniversityFaculties() {
        System.out.println("University Faculties:");
        for (Faculty faculty : faculties) {
            System.out.println(faculty);
        }
    }

    private static void displayFacultiesByStudyField() {
        System.out.println("Select a study field:");
        for (StudyField field : StudyField.values()) {
            System.out.println(field.ordinal() + ". " + field);
        }
        int studyFieldIndex = scanner.nextInt();
        StudyField studyField = StudyField.values()[studyFieldIndex];

        System.out.println("Faculties in the " + studyField + " field:");
        for (Faculty faculty : faculties) {
            if (faculty.getStudyField() == studyField) {
                System.out.println(faculty);
            }
        }
    }

    private static void createStudent() {
        System.out.print("Enter the first name of the student: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the last name of the student: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter the email of the student: ");
        String email = scanner.nextLine();

        System.out.print("Enter the enrollment date (yyyy-MM-dd) of the student: ");
        String enrollmentDateStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date enrollmentDate = null;
        try {
            enrollmentDate = dateFormat.parse(enrollmentDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Student creation failed.");
            return;
        }

        System.out.print("Enter the date of birth (yyyy-MM-dd) of the student: ");
        String dateOfBirthStr = scanner.nextLine();
        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(dateOfBirthStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Student creation failed.");
            return;
        }

        Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
        System.out.println("Student created successfully.");
    }

    private static void assignStudentToFaculty() {
        System.out.print("Enter the student's email: ");
        String email = scanner.nextLine();

        Student studentToAssign = null;
        for (Faculty faculty : faculties) {
            if (faculty.isStudentInFaculty(email)) {
                System.out.println("The student with email " + email + " already belongs to a faculty.");
                return;
            }
        }

        for (Faculty faculty : faculties) {
            if (!faculty.isStudentInFaculty(email)) {
                studentToAssign = new Student("", "", email, new Date(), new Date());
                faculty.assignStudent(studentToAssign);
                System.out.println("Student with email " + email + " has been assigned to the following faculty:");
                System.out.println(faculty);
                return;
            }
        }
    }
}
