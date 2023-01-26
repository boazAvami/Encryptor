package algorithms.models;

import algorithms.enums.AlgorithmType;

import java.security.AlgorithmParameterGenerator;

public class AlgorithmConfig extends AlgorithmRequirements {
    private Key<?> key;

    public AlgorithmConfig(AlgorithmType algorithmType, Key<?> key, AlgorithmType seconderAlgorithmType, int repeats) {
        super(algorithmType,seconderAlgorithmType,repeats);
        this.key = key;
    }

    public AlgorithmConfig(AlgorithmType algorithmType, Key<?> key, AlgorithmType seconderAlgorithmType) {
        super(algorithmType,seconderAlgorithmType);
        this.key = key;
    }
    public AlgorithmConfig(AlgorithmType algorithmType, Key<?> key) {
        super(algorithmType);
        this.key = key;
    }

    public AlgorithmConfig(AlgorithmRequirements algorithmRequirements, Key<?> Key){
        this(algorithmRequirements.getAlgorithmType(),Key,
                algorithmRequirements.getSeconderAlgorithmType(),
                algorithmRequirements.getRepeats());
    }

    public Key<?> getKey() {
        return key;
    }

    public void setKey(Key<?> key) {
        this.key = key;
    }

    public AlgorithmConfig getSecondaryConfig(Key<?> key){
        return new AlgorithmConfig(super.getSeconderAlgorithmType(),key);
    }
    public AlgorithmConfig getSecondaryConfig(){
        return new AlgorithmConfig(super.getSeconderAlgorithmType(),key);
    }
}
