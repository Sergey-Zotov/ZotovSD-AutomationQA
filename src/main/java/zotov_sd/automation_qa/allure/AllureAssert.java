package zotov_sd.automation_qa.allure;

import io.qameta.allure.Step;
import org.testng.Assert;

public class AllureAssert {

    @Step("Проверка равенства: {2}")
    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Проверка равенства:")
    public static void assertEquals(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Проверка на наличие обьекта {1}")
    public static void assertNotNull(Object actual, String massage) {
        Assert.assertNotNull(actual, massage);
    }

    @Step("Проверка отсутствия обьекта {1}")
    public static void assertNull(Object actual, String massage) {
        Assert.assertNull(actual, massage);
    }

    @Step("{1} - True")
    public static void assertTrue(Boolean actual, String massage) {
        Assert.assertTrue(actual, massage);
    }

    @Step("{1} - False")
    public static void assertFalse(Boolean actual, String massage) {
        Assert.assertFalse(actual, massage);
    }

}
