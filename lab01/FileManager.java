package lab01;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static void saveDataToFile(List<Faculty> faculties) {
        try {
            FileWriter writer = new FileWriter("Data.txt");
            writer.write(faculties.size() + "\n");
            for (int i = 0; i < faculties.size(); i++) {
                writer.write(faculties.get(i).getName() + "\n");
                writer.write(faculties.get(i).getAbbrev() + "\n");
                writer.write(faculties.get(i).getStudyField() + "\n");

                writer.write(faculties.get(i).getStudents().size() + "\n");
                if (faculties.get(i).getStudents().size() > 0) {
                    for (int j = 0; j < faculties.get(i).getStudents().size(); j++) {
                        writer.write(faculties.get(i).getStudents().get(j).getFirstName() + "\n");
                        writer.write(faculties.get(i).getStudents().get(j).getLastName() + "\n");
                        writer.write(faculties.get(i).getStudents().get(j).getEmail() + "\n");
                        writer.write(faculties.get(i).getStudents().get(j).getEnrollmentDate() + "\n");
                        writer.write(faculties.get(i).getStudents().get(j).getBirthDate() + "\n");
                        writer.write(faculties.get(i).getStudents().get(j).getGraduated() + "\n");
                    }
                }
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("ERROR: Can't create new file!");
        }
    }
    public static List<Faculty> readDataFromFile() {
        List<Faculty> faculties = new ArrayList<>();
        try {
            File file = new File("Data.txt");
            if (!file.exists() || file.length() == 0) return faculties;
            Scanner reader = new Scanner(file);
            String name, abbrev, studyField;
            String firstName, lastName, email, enrollmentDate, birthDate, graduated;

            int facultyCount = reader.nextInt();
            reader.nextLine();
            for (int i = 0; i < facultyCount; i++) {
                name = reader.nextLine();
                abbrev = reader.nextLine();
                studyField = reader.nextLine();
                faculties.add(new Faculty(name, abbrev, StudyField.valueOf(studyField)));

                int studentsCount = reader.nextInt();
                reader.nextLine();
                if (studentsCount != 0) {
                    for (int j = 0; j < studentsCount; j++) {
                        firstName = reader.nextLine();
                        lastName = reader.nextLine();
                        email = reader.nextLine();
                        enrollmentDate = reader.nextLine();
                        birthDate = reader.nextLine();
                        graduated = reader.nextLine();

                        faculties.get(i).createStudent(firstName, lastName, email,
                                enrollmentDate, birthDate, Boolean.valueOf(graduated));
                    }
                }
            }
            return faculties;
        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found!");
        }
        return faculties;
    }
}
