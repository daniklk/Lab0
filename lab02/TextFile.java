package lab02;

public class TextFile extends MyFile{
    private int lineCount;
    private int wordCount;
    private int charCount;

    public TextFile(String fileName, String createdTime, String modifiedTime,
                    int lineCount, int wordCount, int charCount) {
        super(fileName, createdTime, modifiedTime);
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.charCount = charCount;
    }

    @Override
    public void printInformation() {
        super.printInformation();

        System.out.println("Line count: " + this.lineCount);
        System.out.println("Word count: " + this.wordCount);
        System.out.println("Character count: " + this.charCount);
        System.out.println("===================================");
    }
}
