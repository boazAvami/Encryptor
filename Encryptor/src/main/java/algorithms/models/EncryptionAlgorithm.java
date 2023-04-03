package algorithms.models;

import algorithms.interfaces.IEncryptionFunctionality;
import exceptions.InvalidEncryptionKeyException;

public abstract class EncryptionAlgorithm<T> implements IEncryptionFunctionality {
    protected Key<T> key;

    public EncryptionAlgorithm(Key<T> key) {
        this.key = key;
    }

    public Key<T> getKey() {
        return key;
    }
}
