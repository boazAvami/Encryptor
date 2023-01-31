import algorithms.models.AlgorithmConfig;
import algorithms.models.AlgorithmsFactory;
import algorithms.models.Key;
import algorithms.interfaces.IEncryptionFunctionality;

public class FileEncryptionService {

    public String encrypt(String filePath, AlgorithmConfig algorithmConfig){
        try {
            char[] content = FileUtilities.readFileToCharArray(filePath);
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);

            char[] encryptedContent = algorithm.encrypt(content);

            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_encrypted");
            FileUtilities.createNewFile(newFilePath, encryptedContent);
            String newKeyFilePath = FileUtilities.getNewPathOnSameDirectory(newFilePath, "key.dat");
            FileUtilities.createKeyFile(algorithm.getKey(), newKeyFilePath);

            return "Encrypted successfully. \nnew file path: " + newFilePath
                    + "\nnew Key binary file path: " + newKeyFilePath;

        } catch (Exception e) {
            return "ERROR : encryption Failed." + "\n" + e.getMessage();
        }
    }

    public String decrypt(String filePath, String keyFilePath, AlgorithmConfig algorithmConfig){
        try {
            char[] content = FileUtilities.readFileToCharArray(filePath);
            Key<?> key = FileUtilities.readKeyFile(keyFilePath);
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);
            algorithm.setKey(key);

            char[] decryptedContent = algorithm.decrypt(content);
            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_decrypted");
            FileUtilities.createNewFile(newFilePath, decryptedContent);

            return "Decrypted successfully. \nnew file path: " + newFilePath;
        } catch (Exception e) {
            return "ERROR : decryption Failed." + "\n" + e.getMessage();
        }
    }
}
