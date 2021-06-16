package hospitalRun.utils;

public class RandomDataGenerator {
    public static int getRandomNumber(int max, int min) {
        return (int) (Math.random() * ((max - min) + 1) + min);
    }
}
