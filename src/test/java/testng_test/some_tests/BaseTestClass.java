package testng_test.some_tests;

import org.testng.annotations.*;

public abstract class BaseTestClass {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite Method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class Method");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method Method");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method Method");
    }

}
