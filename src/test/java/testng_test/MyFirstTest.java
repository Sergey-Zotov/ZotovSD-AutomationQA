package testng_test;

import org.testng.Assert;
import org.testng.annotations.Test;
import testng_examples.Calculator;

public class MyFirstTest {

    @Test
    public void calculatorSumTest() {
        int a = 4;
        int b = 3;

        int expectedResult = 7;
        int actualResult = Calculator.sum(a, b);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divideByZeroTest() {
        int a = 4;
        int b = 0;

        Calculator.divide(a, b);
    }

}
