package algorithms.models;

import java.io.Serializable;

public class Key<T> implements Serializable {
    private T keyObject;

    public Key(T keyObject) {
        this.keyObject = keyObject;
    }

    public T getKeyObject() {
        return keyObject;
    }

    public void setKeyObject(T keyObject) {
        this.keyObject = keyObject;
    }
}
