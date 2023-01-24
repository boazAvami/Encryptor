import java.util.Random;

public class EncryptionService {
    private EncryptionAlgorithm algorithm;

    public EncryptionService(EncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public EncryptionAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(EncryptionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    private char randomChar() {
        int rand = new Random().nextInt(52);
        char start = (rand < 26) ? 'A' : 'a';
        return (char) (start + rand % 26);
    }


    public String encrypt(String filePath){
        try {
            char[] content = FileUtilities.convertFileToCharArray(filePath);
            char key = this.randomChar();
            char[] encryptedContent = this.algorithm.encrypt(content, key);

            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_encrypted");
            FileUtilities.createNewFile(newFilePath, encryptedContent);
            String newKeyFilePath = FileUtilities.getNewPathOnSameDirectory(newFilePath, "key.txt");
            FileUtilities.createNewFile(newKeyFilePath, new char[]{key});

            return "Encrypted successfully. \nnew file path: " + newFilePath
                    + "\nnew Key file Path: " + newKeyFilePath;

        } catch (Exception e) {
            return "ERROR : encryption Failed." + "\n" + e.getMessage();
        }
    }

    public String decrypt(String filePath, String keyFilePath){
        try {
            char[] content = FileUtilities.convertFileToCharArray(filePath);
            char key = FileUtilities.convertFileToCharArray(keyFilePath)[0];
            char[] decryptedContent = this.algorithm.decrypt(content,key);

            String newFilePath = FileUtilities.addEndingToFileName(filePath, "_decrypted");
            FileUtilities.createNewFile(newFilePath, decryptedContent);

            return "Decrypted successfully. \nnew file path: " + newFilePath;
        } catch (Exception e) {
            return "ERROR : decryption Failed." + "\n" + e.getMessage();
        }
    }
}
