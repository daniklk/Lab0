package lab01;
import java.util.List;
import java.util.ArrayList;

public class University {
    private List<Faculty> faculties;
    public University() {
        faculties = FileManager.readDataFromFile();
    }
    public void addFaculty(Faculty newFaculty) {
        faculties.add(newFaculty);
    }

    public void searchStudentByEmail(String email) {
        for (int i = 0; i < faculties.size(); i++) {
            for (int j = 0; j < faculties.get(i).getStudents().size(); j++) {
                if (faculties.get(i).getStudents().get(j).getEmail().equals(email)) {
                    System.out.println(faculties.get(i));
                    System.out.println(faculties.get(i).getStudents().get(j));
                    return;
                }
            }
        }
        System.out.println("Student not found!\n");
    }

    public void printFacultiesAsChoiceMenu() {
        for (int i = 0; i < this.faculties.size(); i++) {
            System.out.println(faculties.get(i).getName() + " - " + i);
        }
    }

    public void printFacultyByStudyField(int studyFieldIndex) {
        for (int i = 0; i < this.faculties.size(); i++) {
            if (StudyField.values()[studyFieldIndex] == this.faculties.get(i).getStudyField())
                System.out.println(faculties.get(i));
        }
    }
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < faculties.size(); i++)
            text.append(this.faculties.get(i) + "\n");

        return text.toString();
    }

    public List<Faculty> getFaculties() { return this.faculties; }
}
