package lab02;

public class ImageFile extends MyFile{
    private int imageWidth;
    private int imageHeight;

    public ImageFile(String fileName, String createdTime, String modifiedTime, int imageWidth, int imageHeight) {
        super(fileName, createdTime, modifiedTime);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public void printInformation() {
        super.printInformation();

        System.out.println("Image width: " + imageWidth);
        System.out.println("Image height: " + imageHeight);
        System.out.println("===================================");
    }
}
