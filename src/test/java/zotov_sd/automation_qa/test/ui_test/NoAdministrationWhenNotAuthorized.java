package zotov_sd.automation_qa.test.ui_test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.isElementDisplayed;

public class NoAdministrationWhenNotAuthorized extends BaseUITest {

    @BeforeMethod
    public void prepareFixtures() {
        openBrowser();
    }

    @Test
    public void noAdministrationDisplayedTest() {
        Assert.assertFalse(isElementDisplayed(headerPage.administration));
    }


}
