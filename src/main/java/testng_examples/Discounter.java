package testng_examples;

public class Discounter {

    public static int calculate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException();
        }
        if (money < 50) {
            return 10;
        }
        if (money < 100) {
            return 20;
        }
        return 30;
    }

}
