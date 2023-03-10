package algorithms.models;

import algorithms.interfaces.IEncryptionFunctionality;

public abstract class EncryptionAlgorithm<T> implements IEncryptionFunctionality {
    protected Key<T> key;

    public EncryptionAlgorithm(Key<T> key) {
        this.key = key;
    }

    public Key<T> getKey() {
        return key;
    }

    public void setKey(Key<?> key) {
        this.key = (Key<T>) (key);
    }
}
