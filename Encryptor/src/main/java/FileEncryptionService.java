import algorithms.enums.AlgorithmType;
import algorithms.models.AlgorithmConfig;
import algorithms.models.AlgorithmRequirements;
import algorithms.models.AlgorithmsFactory;
import algorithms.models.Key;
import algorithms.OperatorShiftAlgorithm;
import algorithms.charActions.Add;
import algorithms.interfaces.IEncryptionFunctionality;

import java.util.function.IntFunction;

public class FileEncryptionService {

    public String encrypt(String filePath, AlgorithmRequirements algorithmRequirements){
        try {
            char[] content = FileUtilities.readFileToCharArray(filePath);
            Key<?> key = AlgorithmsFactory.generateKey(algorithmRequirements.getAlgorithmType());
            AlgorithmConfig algorithmConfig = new AlgorithmConfig(algorithmRequirements, key);
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);

            char[] encryptedContent = algorithm.encrypt(content);

            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_encrypted");
            FileUtilities.createNewFile(newFilePath, encryptedContent);
            String newKeyFilePath = FileUtilities.getNewPathOnSameDirectory(newFilePath, "key.dat");
            FileUtilities.createKeyFile(key, newKeyFilePath);

            return "Encrypted successfully. \nnew file path: " + newFilePath
                    + "\nnew Key file path: " + newKeyFilePath;

        } catch (Exception e) {
            return "ERROR : encryption Failed." + "\n" + e.getMessage();
        }
    }

    public String decrypt(String filePath, String keyFilePath, AlgorithmRequirements algorithmRequirements){
        try {
            char[] content = FileUtilities.readFileToCharArray(filePath);
            Key<?> key = FileUtilities.readKeyFile(keyFilePath);
            AlgorithmConfig algorithmConfig = new AlgorithmConfig(algorithmRequirements, key);
            IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(algorithmConfig);
            char[] decryptedContent = algorithm.decrypt(content);

            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_decrypted");
            FileUtilities.createNewFile(newFilePath, decryptedContent);

            return "Decrypted successfully. \nnew file path: " + newFilePath;
        } catch (Exception e) {
            return "ERROR : decryption Failed." + "\n" + e.getMessage();
        }
    }
}
