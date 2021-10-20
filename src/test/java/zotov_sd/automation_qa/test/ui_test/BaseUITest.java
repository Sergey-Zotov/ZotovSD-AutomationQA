package zotov_sd.automation_qa.test.ui_test;

import org.testng.annotations.AfterMethod;
import zotov_sd.automation_qa.ui.browser.Browser;
import zotov_sd.automation_qa.ui.browser.BrowserManager;
import zotov_sd.automation_qa.ui.pages.HeaderPage;
import zotov_sd.automation_qa.ui.pages.LoginPage;

public class BaseUITest {

    protected Browser browser;
    protected HeaderPage headerPage;
    protected LoginPage loginPage;

    protected void openBrowser() {
        browser = BrowserManager.getBrowser();
        headerPage = new HeaderPage();
        loginPage = new LoginPage();
    }

    protected void openBrowser(String uri) {
        browser = BrowserManager.getBrowser(uri);
        headerPage = new HeaderPage();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        BrowserManager.closeBrowser();
    }
}
