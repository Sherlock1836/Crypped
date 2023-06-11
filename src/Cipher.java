import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Cipher {
    private Random rand;
    private long seed;
    private ArrayList<Character> characters;
    private ArrayList<Character> usedChars;
    private char[] encryptedChars;

    public Cipher() {
        characters = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        resetCipher();
    }
    private void resetCipher() {
        rand = new Random();
        resetRand();
        createCipher();
    }
    private void resetRand() {
        seed = rand.nextLong(10, 100);
        rand.setSeed(seed);
    }
    private void setSeed(long seed) {
        this.seed = seed;
        rand.setSeed(seed);
    }
    private void createCipher() {
        usedChars = new ArrayList<Character>(characters);
        encryptedChars = new char[characters.size()];
        for(int i = characters.size() - 1; i > -1; --i) {
            int index = rand.nextInt(i + 1);
            encryptedChars[i] = usedChars.get(index);
            usedChars.remove(index);
        }
    }
    public String encryptMessage(Message message) {
        resetCipher();
        message.setDecryptedMessage(seed + message.getDecryptedMessage());
        for(char c : message.getDecryptedMessage().toCharArray()) {
            int index = characters.indexOf(c);
            message.setEncryptedMessage(message.getEncryptedMessage() + encryptedChars[index]);
        }
        return message.getEncryptedMessage();
    }
    public String decryptMessage(Message message) {
        return "Can't do that yet.";
    }
}
