import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtilities {

    public static char[] convertFileToCharArray(String fileName) throws IOException
    {
        Path filePath = Path.of(fileName);
        String content = Files.readString(filePath);

        return content.toCharArray();
    }

    public static void createNewFile(String fileName, char[] content) throws IOException
    {
        File file = new File(Path.of(fileName).getParent().toString() ,Path.of(fileName).getFileName().toString());
        boolean fileCreated = file.createNewFile();

        if(fileCreated) {
            Writer writer = writer = new BufferedWriter(new FileWriter(file.getPath()));
            writer.write(content);
            writer.close();
        } else {
            throw new IOException("files already exists");
        }
    }

    public static boolean createNewDir(String dirPath)
    {
        File parentDir = new File(dirPath);
        return parentDir.mkdir();
    }

    public static String addEndingToFileName(String filePath, String nameToAdd){
        Path originalFilePath = Path.of(filePath);
        return  originalFilePath.getParent() + "\\" +
                originalFilePath.getFileName().toString().replaceFirst("[.][^.]+$", "")
                 + nameToAdd  + FileUtilities.getFileExtension(originalFilePath);
    }

    public static String getNewPathOnSameDirectory(String filePath, String newName){
        Path originalFilePath = Path.of(filePath);
        return  originalFilePath.getParent() + "\\" + newName;
    }

    private static  String getFileExtension(Path path) {
        String name = path.getFileName().toString();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
}
