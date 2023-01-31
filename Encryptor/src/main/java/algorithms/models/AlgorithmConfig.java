package algorithms.models;

import algorithms.enums.AlgorithmType;

public class AlgorithmConfig {
    private AlgorithmType algorithmType;
    private AlgorithmConfig firstAlgoConfigurations;
    private AlgorithmConfig secondAlgoConfigurations;
    private int repeats = 1;

    public AlgorithmConfig(AlgorithmType algorithmType, AlgorithmConfig firstAlgoConfigurations, AlgorithmConfig secondAlgoConfigurations, int repeats) {
        this.algorithmType = algorithmType;
        this.firstAlgoConfigurations = firstAlgoConfigurations;
        this.secondAlgoConfigurations = secondAlgoConfigurations;
        this.repeats = repeats;
    }

    public AlgorithmConfig(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public AlgorithmConfig(AlgorithmType algorithmType, AlgorithmConfig firstAlgoConfigurations, int repeats) {
        this.algorithmType = algorithmType;
        this.firstAlgoConfigurations = firstAlgoConfigurations;
        this.repeats = repeats;
    }

    public AlgorithmConfig(AlgorithmType algorithmType, AlgorithmConfig firstAlgoConfigurations, AlgorithmConfig secondAlgoConfigurations) {
        this.algorithmType = algorithmType;
        this.firstAlgoConfigurations = firstAlgoConfigurations;
        this.secondAlgoConfigurations = secondAlgoConfigurations;
    }

    public AlgorithmType getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(AlgorithmType algorithmType) {
        this.algorithmType = algorithmType;
    }

    public AlgorithmConfig getFirstAlgoConfigurations() {
        return firstAlgoConfigurations;
    }

    public void setFirstAlgoConfigurations(AlgorithmConfig firstAlgoConfigurations) {
        this.firstAlgoConfigurations = firstAlgoConfigurations;
    }

    public AlgorithmConfig getSecondAlgoConfigurations() {
        return secondAlgoConfigurations;
    }

    public void setSecondAlgoConfigurations(AlgorithmConfig secondAlgoConfigurations) {
        this.secondAlgoConfigurations = secondAlgoConfigurations;
    }

    public int getRepeats() {
        return repeats;
    }

    public void setRepeats(int repeats) {
        this.repeats = repeats;
    }
}
