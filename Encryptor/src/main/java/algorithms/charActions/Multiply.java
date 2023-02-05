package algorithms.charActions;

import algorithms.interfaces.ICharactersOperator;

import java.util.concurrent.atomic.AtomicInteger;

public class Multiply implements ICharactersOperator {
    public static int calculateDecryptedChar(int a, int b, AtomicInteger x, AtomicInteger y) {
        if (a == 0) {
            x.set(0);
            y.set(1);
            return b;
        }

        AtomicInteger _x = new AtomicInteger(), _y = new AtomicInteger();
        int gcd = calculateDecryptedChar(b % a, a, _x, _y);

        x.set(_y.get() - (b / a) * _x.get());
        y.set(_x.get());

        return gcd;
    }

    @Override
    public char actionForward(char a, char b) {
        return (char) ((a * b) % 65536);
    }

    @Override
    public char actionBackward(char a, char b) {
        AtomicInteger x = new AtomicInteger(), y = new AtomicInteger();
        calculateDecryptedChar(b, (65536), x, y);
        return (char) ((a * x.get()) % (65536));
    }
}
