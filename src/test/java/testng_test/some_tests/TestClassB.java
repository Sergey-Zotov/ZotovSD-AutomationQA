package testng_test.some_tests;

import org.testng.annotations.Test;

public class TestClassB extends BaseTestClass {

    @Test
    public void testMethod1() {
        System.out.println("Test Class B - Method 1");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test Class B - Method 2");
    }

}
