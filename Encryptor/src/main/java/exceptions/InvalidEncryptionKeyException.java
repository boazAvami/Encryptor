package exceptions;

public class InvalidEncryptionKeyException extends Exception {
    public InvalidEncryptionKeyException() {
        super("ERROR: Invalid Encryption Key was provided.");
    }
}
