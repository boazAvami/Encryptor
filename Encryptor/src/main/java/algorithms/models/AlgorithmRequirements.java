package algorithms.models;

import algorithms.enums.AlgorithmType;

public class AlgorithmRequirements {
    private AlgorithmType algorithmType;
    private AlgorithmType seconderAlgorithmType;
    private int repeats;

    public AlgorithmRequirements(AlgorithmType algorithmType, AlgorithmType seconderAlgorithmType, int repeats) {
        this.algorithmType = algorithmType;
        this.seconderAlgorithmType = seconderAlgorithmType;
        this.repeats = repeats;
    }

    public AlgorithmRequirements(AlgorithmType algorithmType, AlgorithmType seconderAlgorithmType) {
        this(algorithmType, seconderAlgorithmType, 1);
    }

    public AlgorithmRequirements(AlgorithmType algorithmType) {
        this(algorithmType, algorithmType, 1);
    }

    public AlgorithmType getAlgorithmType() {
        return this.algorithmType;
    }

    public void setAlgorithmType(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public int getRepeats() {
        return this.repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }

    public AlgorithmType getSeconderAlgorithmType() {
        return this.seconderAlgorithmType;
    }

    public void setSeconderAlgorithmType(AlgorithmType seconderAlgorithmType) {
        this.seconderAlgorithmType = seconderAlgorithmType;
    }
}
