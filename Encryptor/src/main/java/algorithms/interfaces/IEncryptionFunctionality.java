package algorithms.interfaces;

import algorithms.models.Key;

public interface IEncryptionFunctionality {
    public char[] encrypt(char[] content);

    public char[] decrypt(char[] content);

    public Key<?> getKey();

    public void setKey(Key<?> key);
}
