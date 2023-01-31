package algorithms;

import algorithms.interfaces.IEncryptionFunctionality;
import algorithms.models.Key;

public class RepeatEncryption implements IEncryptionFunctionality {
    private IEncryptionFunctionality encryption;
    private int repeats;

    public RepeatEncryption(IEncryptionFunctionality firstEncryption, int repeats) {
        this.encryption = firstEncryption;
        this.repeats = repeats;
    }

    public IEncryptionFunctionality getEncryption() {
        return encryption;
    }

    public void setEncryption(IEncryptionFunctionality encryption) {
        this.encryption = encryption;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    @Override
    public char[] encrypt(char[] content) {
        char[] newContent = content.clone();
        for (int repeatIndex = 0; repeatIndex < this.repeats; repeatIndex++) {
            newContent = this.encryption.encrypt(newContent);
        }
        return newContent;
    }

    @Override
    public char[] decrypt(char[] content) {
        char[] newContent = content.clone();
        for (int repeatIndex = 0; repeatIndex < this.repeats; repeatIndex++) {
            newContent = this.encryption.decrypt(newContent);
        }
        return newContent;
    }

    @Override
    public void setKey(Key<?> key) {
        this.encryption.setKey(key);
    }

    @Override
    public Key<?> getKey() {
        return this.encryption.getKey();
    }
}
