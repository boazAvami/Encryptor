
public class  ShiftUpAlgorithm implements EncryptionAlgorithm {
    @Override
    public char[] encrypt(char[] content, char key) {
        char[] contentClone =  content.clone();
        for (int currContentChar = 0; currContentChar < contentClone.length; currContentChar++) {
            contentClone[currContentChar] += key;
        }
        return contentClone;
    }

    @Override
    public char[] decrypt(char[] content, char key) {
        char[] contentClone =  content.clone();
        for (int currContentChar = 0; currContentChar < contentClone.length; currContentChar++) {
            contentClone[currContentChar] -= key;
        }
        return contentClone;
    }
}
