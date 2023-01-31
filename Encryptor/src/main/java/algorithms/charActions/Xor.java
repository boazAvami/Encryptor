package algorithms.charActions;

import algorithms.interfaces.ICharactersOperator;

public class Xor implements ICharactersOperator {
    @Override
    public char actionForward(char a, char b) {
        return (char)(a^b);
    }

    @Override
    public char actionBackward(char a, char b) {
        return (char)(a^b);
    }
}
