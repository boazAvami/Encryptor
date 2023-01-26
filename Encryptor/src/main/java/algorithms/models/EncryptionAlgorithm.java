package algorithms.models;

import algorithms.interfaces.IEncryptionFunctionality;

public abstract class EncryptionAlgorithm<T> implements IEncryptionFunctionality {
    protected Key<T> key;

    public Key<T> getKey() {
        return key;
    }

    public void setKey(Key<T> key) {
        this.key = key;
    }

    public EncryptionAlgorithm(Key<T> key) {
        this.key = key;
    }
}
