import algorithms.enums.AlgorithmType;
import algorithms.models.AlgorithmConfig;
import utilities.FileEncryptionUtilities;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runActionMenu();
    }

    public static void runActionMenu() {

        System.out.println("Please select one of the following options:");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Please enter the file path:");
                String encryptionFilePath = "C:\\Users\\boazo\\Desktop\\try\\trytry.txt"; //scanner.next();

                System.out.println(new AlgorithmConfig(AlgorithmType.DOUBLE).getAlgorithmType());
                System.out.println(FileEncryptionUtilities.encrypt(encryptionFilePath,
                        new AlgorithmConfig(AlgorithmType.DOUBLE,
                                new AlgorithmConfig(AlgorithmType.REPEAT,
                                        new AlgorithmConfig(AlgorithmType.SHIFT_ADD), 4),
                                new AlgorithmConfig(AlgorithmType.XOR))));
                break;
            case 2:
                System.out.println("Please enter the file path:");
                String decryptionFilePath = "C:\\Users\\boazo\\Desktop\\try\\trytry_encrypted.txt"; //scanner.next();
                System.out.println("Please enter the key file path:");
                String decryptionKeyFilePath = "C:\\Users\\boazo\\Desktop\\try\\key.dat";// scanner.next();

                System.out.println(
                        FileEncryptionUtilities.decrypt(decryptionFilePath, decryptionKeyFilePath,
                                new AlgorithmConfig(AlgorithmType.DOUBLE,
                                        new AlgorithmConfig(AlgorithmType.REPEAT,
                                                new AlgorithmConfig(AlgorithmType.SHIFT_ADD), 4),
                                        new AlgorithmConfig(AlgorithmType.XOR))));
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}

//mvn clean verify sonar:sonar -Dsonar.projectKey=Encryptor -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_9945e6f7bfcb561020cad92e2328d328155df38a
