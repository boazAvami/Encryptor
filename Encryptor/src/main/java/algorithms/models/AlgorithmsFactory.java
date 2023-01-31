package algorithms.models;

import algorithms.*;
import algorithms.charActions.CharactersOperatorFactory;
import algorithms.enums.CharOperatorType;
import algorithms.interfaces.IEncryptionFunctionality;

public class AlgorithmsFactory {
    public static IEncryptionFunctionality generateAlgorithm(AlgorithmConfig algorithmConfig){
        switch(algorithmConfig.getAlgorithmType()) {
            case REPEAT:
                return new RepeatEncryption(
                        generateAlgorithm(algorithmConfig.getFirstAlgoConfigurations()),
                        algorithmConfig.getRepeats());
            case DOUBLE:
                return new DoubleEncryption(
                        generateAlgorithm(algorithmConfig.getFirstAlgoConfigurations()),
                        generateAlgorithm(algorithmConfig.getSecondAlgoConfigurations()));
            case SHIFT_MULTIPLY:
                return new OperatorShiftAlgorithm(
                        new Key<>(randomChar()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.MULTIPLY));
            case XOR:
                return new OperatorShiftAlgorithm(
                        new Key<>(randomChar()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.XOR));
            default:
                return new OperatorShiftAlgorithm(
                        new Key<>(randomChar()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.ADD));
        }
    }

    public static char randomChar() {
        int rnd = (int) (Math.random() * 1000);
        return (char) (rnd);
    }
}
