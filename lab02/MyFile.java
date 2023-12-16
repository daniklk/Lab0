package lab02;

public class MyFile {
    protected String fileName;
    protected String createdTime;
    protected String modifiedTime;
    // status since last commit (snapshot)
    protected String status;

    public MyFile(String fileName, String createdTime, String modifiedTime) {
        this.fileName = fileName;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;

        this.status = "New File";
    }
    public void printStatus() {
        System.out.println(getColorByStatus() + this.fileName + " - " + this.status + Application.RESET);
    }
    private String getColorByStatus() {
        switch (this.status) {
            case "New File":
                return Application.GREEN;
            case "Changed":
                return Application.YELLOW;
            case "No Change":
                return Application.BLUE;
            case "Deleted":
                return Application.RED;
            default:
                System.out.println("ERROR: Failed to detect status for text color picking!");
                return "-";
        }
    }
    public void printInformation() {
        System.out.println("===================================");
        System.out.println(this.fileName);
        System.out.println("Created: " + this.createdTime);
        System.out.println("Modified: " + this.modifiedTime);
        System.out.println("===================================");
    }

    public String getFileName() { return this.fileName; }
    public String getModifiedTime() { return this.modifiedTime; }
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return this.status; }


}
