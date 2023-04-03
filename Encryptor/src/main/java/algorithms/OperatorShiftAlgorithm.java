package algorithms;

import algorithms.interfaces.ICharactersOperator;
import algorithms.models.EncryptionAlgorithm;
import algorithms.models.Key;
import exceptions.InvalidEncryptionKeyException;

public class OperatorShiftAlgorithm extends EncryptionAlgorithm<Character> {
    private ICharactersOperator charactersOperator;

    public OperatorShiftAlgorithm(Key<Character> key, ICharactersOperator charactersOperator) {
        super(key);
        this.charactersOperator = charactersOperator;
    }

    public ICharactersOperator getCharactersOperator() {
        return charactersOperator;
    }

    public void setCharactersOperator(ICharactersOperator charactersOperator) {
        this.charactersOperator = charactersOperator;
    }

    @Override
    public char[] encrypt(char[] content) {
        char[] contentClone = content.clone();
        for (int currContentChar = 0; currContentChar < contentClone.length; currContentChar++) {
            contentClone[currContentChar] =
                    this.charactersOperator.actionForward(contentClone[currContentChar], this.key.getKeyObject());
        }
        return contentClone;
    }

    @Override
    public char[] decrypt(char[] content) {
        char[] contentClone = content.clone();
        for (int currContentChar = 0; currContentChar < contentClone.length; currContentChar++) {
            contentClone[currContentChar] =
                    this.charactersOperator.actionBackward(contentClone[currContentChar],
                            this.key.getKeyObject());
        }
        return contentClone;
    }

    @Override
    public void setKey(Key<?> key) throws InvalidEncryptionKeyException {
        try {
            this.key = new Key<Character>((Character) (key.getKeyObject()));
        } catch (Exception e) {
            throw new InvalidEncryptionKeyException();
        }
    }
}
