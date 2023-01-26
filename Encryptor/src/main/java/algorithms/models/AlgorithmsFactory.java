package algorithms.models;

import algorithms.*;
import algorithms.charActions.CharactersOperatorFactory;
import algorithms.enums.AlgorithmType;
import algorithms.enums.CharOperatorType;
import algorithms.interfaces.IEncryptionFunctionality;

public class AlgorithmsFactory {
    public static Key<?> generateKey(AlgorithmType algorithmType){
        if(algorithmType == AlgorithmType.DOUBLE){
            return new Key<Character[]>(new Character[]{randomChar(),randomChar()});
        } else {
            return new Key<Character>(randomChar());
        }
    }

    public static IEncryptionFunctionality generateAlgorithm(AlgorithmConfig algorithmConfig){
        switch(algorithmConfig.getAlgorithmType()) {
            case REPEAT:
                return new SameKeyEncryptionAlgorithm<Character>(
                        new Key<Integer>(algorithmConfig.getRepeats()),
                        generateBasicAlgorithm(algorithmConfig.getSecondaryConfig()));
            case DOUBLE:
                Character[] keyObject = (Character[])algorithmConfig.getKey().getKeyObject();
                return new MultipleKeysEncryption<Character>(
                        new Key<Character[]>(keyObject),
                        generateBasicAlgorithm(algorithmConfig.getSecondaryConfig(
                                new Key<Character>(keyObject[0]))));
            default:
                return generateBasicAlgorithm(algorithmConfig);
        }
    }

    public static EncryptionAlgorithm<Character> generateBasicAlgorithm(AlgorithmConfig algorithmConfig){
        switch(algorithmConfig.getAlgorithmType()) {
            case SHIFT_MULTIPLY:
                return new OperatorShiftAlgorithm(
                        new Key<Character>((Character) algorithmConfig.getKey().getKeyObject()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.MULTIPLY));
            case SHIFT_XOR:
                return new OperatorShiftAlgorithm(
                        new Key<Character>((Character) algorithmConfig.getKey().getKeyObject()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.XOR));
            default:
                return new OperatorShiftAlgorithm(
                        new Key<Character>((Character) algorithmConfig.getKey().getKeyObject()),
                        CharactersOperatorFactory.generateOperator(CharOperatorType.ADD));
        }
    }

    public static char randomChar() {
        int rnd = (int) (Math.random() * 80);
        return (char) (rnd);
    }
}
