package steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.То;
import zotov_sd.automation_qa.ui.browser.BrowserManager;

public class BrowserSteps {

    @Дано("Открыт браузер на странице \"(.+)\"")
    public void openBrowserOnPage(String url) {
        BrowserManager.getBrowser(url);
    }

    @Дано("Открыт браузер на главной странице")
    public void openBrowserOnMainPage() {
        BrowserManager.getBrowser();
    }

    @То("Закрыть браузер")
    public void closeBrowser() {
        BrowserManager.closeBrowser();
    }
}
