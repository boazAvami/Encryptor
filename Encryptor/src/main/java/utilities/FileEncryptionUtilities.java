package utilities;

import algorithms.enums.AlgorithmType;
import algorithms.interfaces.IEncryptionFunctionality;
import algorithms.models.AlgorithmConfig;
import algorithms.models.AlgorithmsFactory;
import algorithms.models.EncryptionAlgorithm;
import algorithms.models.Key;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileEncryptionUtilities {
    private static EncryptionLogger logger = new EncryptionLogger();

    public String encrypt(String filePath, AlgorithmConfig algorithmConfig) {
        logger.debug("Encryption Started");
        try {
            String message = "";
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);

            if(FileUtilities.isDirectory(filePath)){
                logger.debug("Attempt to Encrypt a directory");
                message = saveEncryptFileList(filePath,algorithm);
            } else {
                message = saveEncryptSingleFile(filePath, algorithm);
            }

            message += "\n" + saveKeyFile(filePath,algorithm);

            logger.debug("Encryption Ended");

            return message;
        } catch (Exception e) {
            logger.error("Encryption failed", e);
            return "ERROR : encryption Failed." + "\n" + e.getMessage();
        }
    }

    public String decrypt(String filePath, String keyFilePath, AlgorithmConfig algorithmConfig) {
        logger.debug("Decryption Started");
        try {
            String message = "";
            Key<?> key = FileUtilities.readKeyFile(keyFilePath);
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);
            algorithm.setKey(key);

            if(FileUtilities.isDirectory(filePath)){
                logger.debug("Attempt to Decrypt a directory");

                message = saveDecryptFileList(filePath,algorithm);
            } else {
                message = saveDecryptSingleFile(filePath, algorithm);
            }

            logger.debug("Decryption Ended");

            return message;

        } catch (Exception e) {
            logger.error("Decryption failed", e);
            return "ERROR : decryption Failed." + "\n" + e.getMessage();
        }
    }


    private String saveEncryptSingleFile(String filePath, IEncryptionFunctionality algorithm) throws IOException {
        char[] content = FileUtilities.readFileToCharArray(filePath);
        char[] encryptedContent = algorithm.encrypt(content);
        String newFilePath = FileUtilities.addEndingToFileName(filePath, "_encrypted");
        FileUtilities.createNewFile(newFilePath, encryptedContent);
        System.out.println("ends - " + filePath);
        return "Encrypted successfully. \nnew file path: " + newFilePath;
    }

    private String saveEncryptFileList(String dirPath, IEncryptionFunctionality algorithm) throws IOException {
        List<Path> filesListToEncrypt = FileUtilities.getAllFilesInDir(dirPath);

        final int THREADS_AMOUNT = filesListToEncrypt.size();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_AMOUNT);

       filesListToEncrypt.forEach(path ->  {
            Runnable task = () -> {
                System.out.println("start - " + path.getFileName());
                try {
                    this.saveEncryptSingleFile(path.toString(),algorithm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(task);
        });

        executor.shutdown();

        return "Directory encrypted successfully.";
    }

    private String saveDecryptSingleFile(String filePath, IEncryptionFunctionality algorithm) throws IOException {
        char[] content = FileUtilities.readFileToCharArray(filePath);
        char[] decryptedContent = algorithm.decrypt(content);
        String newFilePath = FileUtilities.addEndingToFileName(filePath, "_decrypted");
        FileUtilities.createNewFile(newFilePath, decryptedContent);

        return "Decrypted successfully. \nnew file path: " + newFilePath;
    }

    private String saveDecryptFileList(String dirPath, IEncryptionFunctionality algorithm) throws IOException {
        List<Path> filesListToDecrypt =
                FileUtilities.getAllFilesInDir(dirPath).stream()
                        .filter(path -> path.toString().contains("_encrypted"))
                        .collect(Collectors.toList());


        final int THREADS_AMOUNT = filesListToDecrypt.size();
        System.out.println(THREADS_AMOUNT);

        ExecutorService executor = Executors.newFixedThreadPool(THREADS_AMOUNT);

        filesListToDecrypt.forEach(path ->  {
            Runnable task = () -> {
                try {
                    this.saveDecryptSingleFile(path.toString(),algorithm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(task);
        });

        executor.shutdown();

        return "Directory decrypted successfully.";
    }

    private String saveKeyFile(String filePath, IEncryptionFunctionality algorithm) throws IOException {
        String newKeyFilePath = FileUtilities.getNewPathOnSameDirectory(filePath, "key.dat");
        FileUtilities.createKeyFile(algorithm.getKey(), newKeyFilePath);

        return "\nnew Key binary file path: " + newKeyFilePath;
    }

}
