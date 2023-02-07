import algorithms.DoubleEncryption;
import algorithms.OperatorShiftAlgorithm;
import algorithms.RepeatEncryption;
import algorithms.charActions.Add;
import algorithms.charActions.Multiply;
import algorithms.charActions.Xor;
import algorithms.interfaces.IEncryptionFunctionality;
import algorithms.models.AlgorithmsFactory;
import algorithms.models.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestAlgorithms {

    private Key<Character> key;
    private char[] plaintext;

    @BeforeEach
    public void setUp() throws Exception {
        this.plaintext = new char[]{'A', 'B', 'C'};
        this.key = new Key<>((char) 93);
    }

    @Test
    public void testAddOperatorShiftAlgorithmEncryption() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Add());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testAddOperatorShiftAlgorithmEncryption_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Add());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testXorOperatorShiftAlgorithmEncryption() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testXorOperatorShiftAlgorithmEncryption_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Xor());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyOperatorShiftAlgorithmEncryption() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Multiply());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyOperatorShiftAlgorithmEncryption_Empty() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(this.key, new Multiply());
        this.plaintext = new char[]{};
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyOperatorShiftAlgorithmEncryption_NotOddKey() {
        Key<Character> key = new Key<>((char) 92);
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Multiply());
        char[] encrypted = algorithm.encrypt(this.plaintext);
        char[] decrypted = algorithm.decrypt(encrypted);
        assertNotEquals(this.plaintext[0], decrypted[0]);
    }

    @Test
    public void testMultiplyDoubleEncryption_DifferentAlgorithm() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(this.key, new Add());
        Key<Character> secondKey = new Key<>((char) 99);
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(secondKey, new Multiply());

        IEncryptionFunctionality doubleEncryption = new DoubleEncryption(firstAlgorithm, secondAlgorithm);
        char[] encrypted = doubleEncryption.encrypt(this.plaintext);
        char[] decrypted = doubleEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyDoubleEncryption_SameAlgorithm() {
        IEncryptionFunctionality firstAlgorithm = new OperatorShiftAlgorithm(key, new Xor());
        IEncryptionFunctionality secondAlgorithm = new OperatorShiftAlgorithm(key, new Xor());

        IEncryptionFunctionality doubleEncryption = new DoubleEncryption(firstAlgorithm, secondAlgorithm);
        char[] encrypted = doubleEncryption.encrypt(this.plaintext);
        char[] decrypted = doubleEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyRepeatEncryptionAlgorithm() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Add());
        final int REPEATS = 10;

        IEncryptionFunctionality repeatEncryption = new RepeatEncryption(algorithm, REPEATS);
        char[] encrypted = repeatEncryption.encrypt(this.plaintext);
        char[] decrypted = repeatEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
    }

    @Test
    public void testMultiplyRepeatEncryptionAlgorithm_WithoutRepeats() {
        IEncryptionFunctionality algorithm = new OperatorShiftAlgorithm(key, new Xor());
        final int REPEATS = 0;

        IEncryptionFunctionality repeatEncryption = new RepeatEncryption(algorithm, REPEATS);
        char[] encrypted = repeatEncryption.encrypt(this.plaintext);
        char[] decrypted = repeatEncryption.decrypt(encrypted);
        assertArrayEquals(this.plaintext, decrypted);
        assertArrayEquals(encrypted, decrypted);
    }

    @Test
    public void testRandOddKey() {
        char c = AlgorithmsFactory.randomOddChar();
        assertEquals(1, c % 2);
    }
}
