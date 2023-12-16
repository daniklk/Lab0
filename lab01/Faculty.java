package lab01;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Faculty {
    private String name;
    private String abbrev;
    private List<Student> students;
    private StudyField studyField;

    public Faculty(String name, String abbrev, StudyField studyField) {
        this.name = name;
        this.abbrev = abbrev;
        this.students = new ArrayList<>();
        this.studyField = studyField;
    }
    public void createStudent(String firstName, String lastName, String email,
                              String enDate, String birthDate, boolean graduated) {
        LocalDate parsedEnrollmentDate;
        LocalDate parsedBirthDate;
        try {
            parsedEnrollmentDate = LocalDate.parse(enDate);
            parsedBirthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format, use yyyy-mm-dd");
            return;
        }
        students.add(new Student(firstName, lastName, email, parsedEnrollmentDate, parsedBirthDate, graduated));
    }
    public void printStudents(boolean graduated) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getGraduated() == graduated)
                System.out.println(students.get(i));
        }
    }
    public void checkStudentEnrollment(String firstName, String lastName) {
        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().toUpperCase().equals(firstName) &&
                    students.get(i).getLastName().toUpperCase().equals(lastName) &&
            !students.get(i).getGraduated()) {
                System.out.println("Student: " + students.get(i) + "is enrolled!");
                return;
            }
        }
        System.out.println("Student is not enrolled!");
    }
    public void graduateStudent (String firstName, String lastName) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName) && students.get(i).getLastName().equals(lastName)) {
                if (students.get(i).getGraduated())
                    System.out.println("Student already graduated!\n");
                else
                    students.get(i).setGraduated(true);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Abbreviation: " + abbrev + "\n" +
                "Study Field: " + studyField.name() + "\n";
    }

    public void addStudent(Student newStudent) {
        students.add(newStudent);
    }
    public String getName() { return this.name; }
    public String getAbbrev() { return this.abbrev; }

    public StudyField getStudyField() {
        return this.studyField;
    }
    public List<Student> getStudents() { return this.students; }
    public void setName(String name) { this.name = name; }
    public void setAbbrev(String abbrev) { this.abbrev = abbrev; }
    public void setStudyField(String field) { this.studyField = StudyField.valueOf(field); }



}
