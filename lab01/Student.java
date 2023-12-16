package lab01;
import java.time.LocalDate;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate enrollmentDate;
    private LocalDate birthDate;
    private boolean graduated;

    public Student (String firstName, String lastName, String email, LocalDate enDate, LocalDate birthDate, boolean graduated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enDate;
        this.birthDate = birthDate;
        this.graduated = graduated;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n";
    }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getEmail() { return this.email; }
    public LocalDate getEnrollmentDate() { return this.enrollmentDate; }
    public LocalDate getBirthDate() { return this.birthDate; }
    public boolean getGraduated() { return this.graduated; }
    public void setGraduated(boolean value) { this.graduated = value; }

}
