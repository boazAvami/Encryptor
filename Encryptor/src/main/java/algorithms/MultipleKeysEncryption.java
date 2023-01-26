package algorithms;

import algorithms.models.EncryptionAlgorithm;
import algorithms.models.EncryptionAlgorithmDecorator;
import algorithms.models.Key;

public class MultipleKeysEncryption<T> extends EncryptionAlgorithmDecorator<T, T[]> {

    public MultipleKeysEncryption(Key<T[]> key, EncryptionAlgorithm<T> baseAlgorithm) {
        super(key, baseAlgorithm);
    }

    @Override
    public char[] encrypt(char[] content) {
        char[] newContent = content.clone();
        for (int keysIndex = 0; keysIndex < this.key.getKeyObject().length; keysIndex++) {
            this.baseAlgorithm.setKey(new Key<T>(this.key.getKeyObject()[keysIndex]));
            newContent = this.baseAlgorithm.encrypt(newContent);
        }
        return newContent;
    }

    @Override
    public char[] decrypt(char[] content) {
        char[] newContent = content.clone();
        for (int keysIndex = 0; keysIndex < this.key.getKeyObject().length; keysIndex++) {
            this.baseAlgorithm.setKey(new Key<T>(this.key.getKeyObject()[keysIndex]));
            newContent = this.baseAlgorithm.decrypt(newContent);
        }
        return newContent;
    }
}
