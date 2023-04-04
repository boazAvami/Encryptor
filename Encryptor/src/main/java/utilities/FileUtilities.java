package utilities;

import algorithms.models.Key;
import exceptions.InvalidEncryptionKeyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtilities {

    public static char[] readFileToCharArray(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String content = Files.readString(filePath);

        return content.toCharArray();
    }

    public static void createNewFile(String fileName, char[] content) throws IOException {
        File file = new File(Path.of(fileName).getParent().toString(), Path.of(fileName).getFileName().toString());
        boolean fileCreated = file.createNewFile();
        if (fileCreated) {
            Writer writer = new BufferedWriter(new FileWriter(file.getPath()));
            writer.write(content);
            writer.close();
        } else {
            throw new IOException("file already exists");
        }
    }

    public static boolean createNewDir(String dirPath) {
        File parentDir = new File(dirPath);
        return parentDir.mkdir();
    }

    public static String addEndingToFileName(String filePath, String nameToAdd) {
        Path originalFilePath = Path.of(filePath);
        return originalFilePath.getParent() + "\\" +
                originalFilePath.getFileName().toString().replaceFirst("[.][^.]+$", "")
                + nameToAdd + FileUtilities.getFileExtension(originalFilePath);
    }

    public static String getNewPathOnSameDirectory(String filePath, String newName) {
        Path originalFilePath = Path.of(filePath);

        if(isDirectory((filePath))){
            return originalFilePath+ "\\" + newName;
        }

        return originalFilePath.getParent() + "\\" + newName;
    }

    private static String getFileExtension(Path path) {
        String name = path.getFileName().toString();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }

    public static void createKeyFile(Key<?> key, String filePath) throws IOException {
        File file = new File(Path.of(filePath).getParent().toString(), Path.of(filePath).getFileName().toString());
        boolean fileCreated = file.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file.getPath()));
        objectOutputStream.writeObject(key);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Key<?> readKeyFile(String fileName) throws IOException, InvalidEncryptionKeyException {
        Path filePath = Path.of(fileName);
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream(filePath.toString()));
        Key<?> key;

        try {
            key = (Key<?>) (objectInputStream.readObject());
        } catch (Exception e) {
            throw new InvalidEncryptionKeyException();
        }

        return key;
    }

    public static boolean isDirectory(String path) {
        Path isDirPath = Path.of(path);
        return Files.isDirectory(isDirPath);
    }

    public static List<Path> getAllFilesInDir(String directoryPath) throws IOException {
        if (isDirectory(directoryPath)) {
            Path dirPath = Paths.get(directoryPath);
            return Files.list(dirPath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .collect(Collectors.toList());
        }

        return new ArrayList<Path>();
    }
}
