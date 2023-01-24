import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runActionMenu();
    }

    public static void runActionMenu(){
        EncryptionService encryptionService = new EncryptionService(new ShiftUpAlgorithm());

        System.out.println("Please select one of the following options:");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Please enter the file path:");
                String encryptionFilePath =  scanner.next();

                System.out.println(encryptionService.encrypt(encryptionFilePath));
                break;
            case 2:
                System.out.println("Please enter the file path:");
                String decryptionFilePath = scanner.next();
                System.out.println("Please enter the key file path:");
                String decryptionKeyFilePath = scanner.next();

                System.out.println(encryptionService.decrypt(decryptionFilePath, decryptionKeyFilePath));
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}