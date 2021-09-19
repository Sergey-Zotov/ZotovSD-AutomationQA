package testng_test.some_tests;

import org.testng.annotations.Test;

public class TestClassA extends BaseTestClass {

    @Test
    public void testMethod1() {
        System.out.println("Test Class A - Method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test Class A - Method 2");
        throw new RuntimeException();
    }

}
