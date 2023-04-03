package algorithms;

import algorithms.interfaces.IEncryptionFunctionality;
import algorithms.models.Key;
import exceptions.InvalidEncryptionKeyException;

public class DoubleEncryption implements IEncryptionFunctionality {
    private IEncryptionFunctionality firstEncryption;
    private IEncryptionFunctionality secondEncryption;

    public DoubleEncryption(IEncryptionFunctionality firstEncryption, IEncryptionFunctionality secondEncryption) {
        this.firstEncryption = firstEncryption;
        this.secondEncryption = secondEncryption;
    }

    public IEncryptionFunctionality getFirstEncryption() {
        return firstEncryption;
    }

    public void setFirstEncryption(IEncryptionFunctionality firstEncryption) {
        this.firstEncryption = firstEncryption;
    }

    public IEncryptionFunctionality getSecondEncryption() {
        return secondEncryption;
    }

    public void setSecondEncryption(IEncryptionFunctionality secondEncryption) {
        this.secondEncryption = secondEncryption;
    }

    @Override
    public char[] encrypt(char[] content) {
        char[] newContent = content.clone();
        newContent = this.firstEncryption.encrypt(newContent);
        newContent = this.secondEncryption.encrypt(newContent);
        return newContent;
    }

    @Override
    public char[] decrypt(char[] content) {
        char[] newContent = content.clone();
        newContent = this.secondEncryption.decrypt(newContent);
        newContent = this.firstEncryption.decrypt(newContent);
        return newContent;
    }

    @Override
    public Key<?> getKey() {
        return new Key<>(new Key<?>[]{this.firstEncryption.getKey(),
                this.secondEncryption.getKey()});
    }

    @Override
    public void setKey(Key<?> key) throws InvalidEncryptionKeyException {
        try {
            this.firstEncryption.setKey(((Key<?>[]) key.getKeyObject())[0]);
            this.secondEncryption.setKey(((Key<?>[]) key.getKeyObject())[1]);
        } catch (Exception e) {
            throw new InvalidEncryptionKeyException();
        }

    }
}
