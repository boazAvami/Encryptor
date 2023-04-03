import algorithms.DoubleEncryption;
import algorithms.OperatorShiftAlgorithm;
import algorithms.RepeatEncryption;
import algorithms.charActions.Add;
import algorithms.charActions.CharactersOperatorFactory;
import algorithms.charActions.Multiply;
import algorithms.charActions.Xor;
import algorithms.enums.AlgorithmType;
import algorithms.enums.CharOperatorType;
import algorithms.interfaces.ICharactersOperator;
import algorithms.interfaces.IEncryptionFunctionality;
import algorithms.models.AlgorithmConfig;
import algorithms.models.AlgorithmsFactory;
import algorithms.models.Key;
import exceptions.InvalidEncryptionKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlgorithms {

    private Key<Character> key;
    private char[] plaintext;

    @BeforeEach
    public void setUp() throws Exception {
        this.plaintext = new char[]{'A', 'B', 'C'};
        this.key = new Key<>((char) 93);
    }

    @Test
    public void testEncryptionAlgorithm_AddOperatorShift() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Add());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_AddOperatorShift_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Add());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_XorOperatorShift() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_XorOperatorShift_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_MultiplyOperatorShift() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Multiply());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_MultiplyOperatorShift_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Multiply());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_MultiplyOperatorShift_NotOddKey() {
        Key<Character> key = new Key<>((char) 92);
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Multiply());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertNotEquals(this.plaintext[0], decrypted[0]);
    }

    @Test
    public void testEncryptionAlgorithm_DoubleEncryption_DifferentAlgorithm() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(this.key, new Add());
        Key<Character> secondKey = new Key<>((char) 99);
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(secondKey, new Multiply());

        IEncryptionFunctionality doubleEncryption = new DoubleEncryption(firstAlgorithm, secondAlgorithm);
        char[] encrypted = doubleEncryption.encrypt(this.plaintext);
        char[] decrypted = doubleEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_DoubleEncryption_SameAlgorithm() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(key, new Xor());
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(key, new Xor());

        IEncryptionFunctionality doubleEncryption = new DoubleEncryption(firstAlgorithm, secondAlgorithm);
        char[] encrypted = doubleEncryption.encrypt(this.plaintext);
        char[] decrypted = doubleEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_DoubleEncryption_InvalidKeyNotArray() throws InvalidEncryptionKeyException {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        IEncryptionFunctionality doubleEncryption = new DoubleEncryption(firstAlgorithm, secondAlgorithm);

        Assertions.assertThrows(InvalidEncryptionKeyException.class, () -> {
            doubleEncryption.setKey(this.key);
        });
    }

    @Test
    public void testEncryptionAlgorithm_CharacterOperator_CastingInvalidKey() throws InvalidEncryptionKeyException {
        Key<int[]> invalidKey = new Key<>(new int[]{1,2,3,});
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Xor());

        Assertions.assertThrows(InvalidEncryptionKeyException.class, () -> {
            algorithm.setKey(invalidKey);
        });
    }





    @Test
    public void testEncryptionAlgorithm_RepeatEncryption_MultipleRepeats() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Add());
        final int REPEATS = 10;

        IEncryptionFunctionality repeatEncryption = new RepeatEncryption(algorithm, REPEATS);
        char[] encrypted = repeatEncryption.encrypt(this.plaintext);
        char[] decrypted = repeatEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_RepeatEncryption_WithoutRepeats() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Xor());
        final int REPEATS = 0;

        IEncryptionFunctionality repeatEncryption = new RepeatEncryption(algorithm, REPEATS);
        char[] encrypted = repeatEncryption.encrypt(this.plaintext);
        char[] decrypted = repeatEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
        assertArrayEquals(encrypted, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_RepeatOnDouble() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(key, new Xor());
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(key, new Add());

        final int REPEATS = 0;

        IEncryptionFunctionality repeatEncryption = new RepeatEncryption(
                new DoubleEncryption(firstAlgorithm,secondAlgorithm), REPEATS);

        char[] encrypted = repeatEncryption.encrypt(this.plaintext);
        char[] decrypted = repeatEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
        assertArrayEquals(encrypted, decrypted);
    }

    @Test
    public void testEncryptionAlgorithm_DoubleOnRepeat() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(key, new Xor());
        final int REPEATS = 0;
        IEncryptionFunctionality repeatEncryption =
                new RepeatEncryption(new OperatorShiftAlgorithm(key, new Multiply()),REPEATS);


        IEncryptionFunctionality doubleEncryption = new RepeatEncryption(
                new DoubleEncryption(firstAlgorithm,repeatEncryption), REPEATS);

        char[] encrypted = doubleEncryption.encrypt(this.plaintext);
        char[] decrypted = doubleEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
        assertArrayEquals(encrypted, decrypted);
    }

    // Algorithm Factory Test

    @Test
    public void testRandOddKey() {
        char c = AlgorithmsFactory.randomOddChar();
        assertEquals(1, c % 2);
    }

    @Test
    public void testAlgorithmFactoryGenerate_ShiftAdd() {
        AlgorithmConfig config = new AlgorithmConfig(AlgorithmType.SHIFT_ADD);
        IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(config);

        assertEquals(algorithm.getClass(), OperatorShiftAlgorithm.class);
        OperatorShiftAlgorithm operatorShiftAlgorithm = (OperatorShiftAlgorithm) algorithm;
        assertEquals(operatorShiftAlgorithm.getCharactersOperator().getClass(),Add.class);
    }
    @Test
    public void testAlgorithmFactoryGenerate_Xor() {
        AlgorithmConfig config = new AlgorithmConfig(AlgorithmType.XOR);
        IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(config);

        assertEquals(algorithm.getClass(), OperatorShiftAlgorithm.class);
        OperatorShiftAlgorithm operatorShiftAlgorithm = (OperatorShiftAlgorithm) algorithm;
        assertEquals(operatorShiftAlgorithm.getCharactersOperator().getClass(),Xor.class);
    }
    @Test
    public void testAlgorithmFactoryGenerate_ShiftMultiply() {
        AlgorithmConfig config = new AlgorithmConfig(AlgorithmType.SHIFT_MULTIPLY);
        IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(config);

        assertEquals(algorithm.getClass(), OperatorShiftAlgorithm.class);
        OperatorShiftAlgorithm operatorShiftAlgorithm = (OperatorShiftAlgorithm) algorithm;
        assertEquals(operatorShiftAlgorithm.getCharactersOperator().getClass(),Multiply.class);
    }
    @Test
    public void testAlgorithmFactoryGenerate_DoubleOperators() {
        AlgorithmConfig config = new AlgorithmConfig(AlgorithmType.DOUBLE,
                new AlgorithmConfig(AlgorithmType.SHIFT_ADD),
                new AlgorithmConfig(AlgorithmType.XOR));
        IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(config);

        assertEquals(algorithm.getClass(), DoubleEncryption.class);
        DoubleEncryption doubleEncryption = (DoubleEncryption) algorithm;
        assertEquals(doubleEncryption.getFirstEncryption().getClass(),OperatorShiftAlgorithm.class);
        assertEquals(doubleEncryption.getSecondEncryption().getClass(),OperatorShiftAlgorithm.class);
    }
    @Test
    public void testAlgorithmFactoryGenerate_RepeatOperator() {
        final int REPEATS = 2;
        AlgorithmConfig config = new AlgorithmConfig(AlgorithmType.REPEAT,
                new AlgorithmConfig(AlgorithmType.SHIFT_ADD),REPEATS);
        IEncryptionFunctionality algorithm = AlgorithmsFactory.generateAlgorithm(config);

        assertEquals(algorithm.getClass(), RepeatEncryption.class);
        RepeatEncryption repeatEncryption = (RepeatEncryption) algorithm;
        assertEquals(repeatEncryption.getEncryption().getClass(), OperatorShiftAlgorithm.class);
    }

    @Test
    public void testCharactersOperatorFactoryGenerate_Add() {
        ICharactersOperator operator = CharactersOperatorFactory.generateOperator(CharOperatorType.ADD);
        assertEquals(operator.getClass(), Add.class);
    }

    @Test
    public void testCharactersOperatorFactoryGenerate_Xor() {
        ICharactersOperator operator = CharactersOperatorFactory.generateOperator(CharOperatorType.ADD);
        assertEquals(operator.getClass(), Add.class);
    }

    @Test
    public void testCharactersOperatorFactoryGenerate_Multiply() {
        ICharactersOperator operator = CharactersOperatorFactory.generateOperator(CharOperatorType.ADD);
        assertEquals(operator.getClass(), Add.class);
    }


}
