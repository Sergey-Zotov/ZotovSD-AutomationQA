package zotov_sd.automation_qa.test.ui_test;

import org.testng.annotations.AfterMethod;
import zotov_sd.automation_qa.ui.browser.Browser;
import zotov_sd.automation_qa.ui.browser.BrowserManager;
import zotov_sd.automation_qa.ui.pages.*;

public class BaseUITest {

    protected Browser browser;
    protected HeaderPage headerPage;
    protected LoginPage loginPage;
    protected AdministrationPage administrationPage;
    protected UserTablePage userTablePage;

    protected void openBrowser() {
        browser = BrowserManager.getBrowser();
        initPages();
    }

    protected void openBrowser(String uri) {
        browser = BrowserManager.getBrowser(uri);
        initPages();
    }

    private void initPages() {
        headerPage = Page.getPage(HeaderPage.class);
        loginPage = Page.getPage(LoginPage.class);
        administrationPage = Page.getPage(AdministrationPage.class);
        userTablePage = Page.getPage(UserTablePage.class);
    }

    @AfterMethod
    public void tearDown() {
        BrowserManager.closeBrowser();
    }
}
