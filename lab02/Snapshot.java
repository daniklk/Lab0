package lab02;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
// date formatting
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
// lists
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// text files reading
import java.io.FileReader;
import java.io.BufferedReader;
// image files reading
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Snapshot {
    private List<File> fileObjs;
    private List<MyFile> files;
    private LocalDateTime time;
    String path = "C:\\Users\\Boris\\Desktop\\University\\3rd Semester\\OOP\\Laboratories\\Resources";

    public Snapshot() {

        this.time = java.time.LocalDateTime.now();
        //System.out.println("Commit made at: " + this.time);
        this.files = new ArrayList<>();

        getFileObjects(this.path);
        for (int i = 0; i < fileObjs.size(); i++) {
            String fileName;
            String createdTime = "-";
            String modifiedTime = "-";

            fileName = fileObjs.get(i).getName();
            String ext = getFileExtension(fileName);
            String filePath = fileObjs.get(i).getAbsolutePath();
            try {
                Path file = Paths.get(filePath);
                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                createdTime = formatDateTime(attr.creationTime());
                modifiedTime = formatDateTime(attr.lastModifiedTime());
            } catch (IOException e) { e.printStackTrace(); }

            if (ext.equals("txt")) {
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;
                String line;
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        lineCount++;
                        charCount += line.length();

                        String[] words = line.split(" ");
                        wordCount += words.length;
                    }
                    this.files.add(new TextFile(fileName, createdTime, modifiedTime, lineCount, wordCount, charCount));
                    bufferedReader.close();
                    fileReader.close();
                } catch(IOException e) { e.printStackTrace(); }
            }
            else if (ext.equals("jpg") || ext.equals("png")) {
                try {
                    BufferedImage image = ImageIO.read(fileObjs.get(i));
                    this.files.add(new ImageFile(fileName, createdTime, modifiedTime, image.getWidth(), image.getHeight()));
                } catch (IOException e) { e.printStackTrace(); }
            }
            else {
                this.files.add(new MyFile(fileName, createdTime, modifiedTime));
            }
        }
    }

    private void getFileObjects(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            fileObjs = Arrays.asList(dir.listFiles());
            return;
        }
        System.out.println("ERROR: failed to get file objects!");
    }
    public void printFilesStatus() {
        System.out.println("Snapshot created at: " + this.time);
        for (int i = 0; i < files.size(); i++)
            files.get(i).printStatus();
    }
    public void printFilesInfo() {
        for (int i = 0; i < files.size(); i++) {
            files.get(i).printInformation();
        }
    }
    public int searchFileByName(String fileName) {
        for (int i = 0; i < this.files.size(); i++) {
            if (this.files.get(i).getFileName().equals(fileName))
                return i;
        }
        return -1;
    }
    private static String formatDateTime(FileTime fileTime) {

        LocalDateTime localDateTime = fileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime.format(
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
    }
    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        String ext = fileName.substring(index + 1);
        return ext;
    }
    public List<MyFile> getFiles() { return this.files; }
}
