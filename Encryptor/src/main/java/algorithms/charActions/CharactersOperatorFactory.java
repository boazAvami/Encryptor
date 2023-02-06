package algorithms.charActions;

import algorithms.enums.CharOperatorType;
import algorithms.interfaces.ICharactersOperator;

public class CharactersOperatorFactory {
    public static ICharactersOperator generateOperator(CharOperatorType charOperatorType) {
        switch (charOperatorType) {
            case MULTIPLY:
                return new Multiply();
            case XOR:
                return new Xor();
            default:
                return new Add();
        }
    }
}
