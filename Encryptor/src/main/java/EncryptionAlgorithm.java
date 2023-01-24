public interface EncryptionAlgorithm {
        public char[] encrypt(char[] content, char key);
        public char[] decrypt(char[] content, char key);
}
