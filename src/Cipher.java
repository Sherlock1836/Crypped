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
        characters = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        rand = new Random();
        resetRand();
        createCipher();
    }
    public void resetRand() {
        seed = rand.nextLong();
        rand.setSeed(seed);
    }
    public void setSeed(long seed) {
        this.seed = seed;
        rand.setSeed(seed);
    }
    public void createCipher() {
        usedChars = new ArrayList<Character>(characters);
        encryptedChars = new char[characters.size()];
        for(int i = characters.size() - 1; i > -1; --i) {
            int index = rand.nextInt(i + 1);
            encryptedChars[i] = usedChars.get(index);
            usedChars.remove(index);
            System.out.print(encryptedChars[i]);
        }
    }
}
