package algorithms.models;

public abstract class EncryptionAlgorithmDecorator<S,T> extends EncryptionAlgorithm<T> {
    protected EncryptionAlgorithm<S> baseAlgorithm;

    public EncryptionAlgorithmDecorator(Key<T> key, EncryptionAlgorithm<S> baseAlgorithm) {
        super(key);
        this.baseAlgorithm = baseAlgorithm;
    }

    public EncryptionAlgorithm<S> getBaseAlgorithm() {
        return baseAlgorithm;
    }

    public void setBaseAlgorithm(EncryptionAlgorithm<S> baseAlgorithm) {
        this.baseAlgorithm = baseAlgorithm;
    }
}
