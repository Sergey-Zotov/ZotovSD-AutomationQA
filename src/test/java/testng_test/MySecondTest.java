package testng_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testng_examples.Calculator;

import java.util.Random;

public class MySecondTest {
    private int a;
    private int b;

    @BeforeMethod
    public void prepareFixtures() {
        a = new Random().nextInt(10);
        b = new Random().nextInt(5);
    }

    @Test
    public void calculateSubtractTest() {
        int expected = a - b;
        int actual = Calculator.subtract(a, b);

        Assert.assertEquals(actual, expected);
    }

}
