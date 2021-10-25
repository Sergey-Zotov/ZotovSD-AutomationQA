package zotov_sd.automation_qa.ui.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.support.PageFactory;
import zotov_sd.automation_qa.ui.browser.BrowserManager;

public abstract class Page {

    @SneakyThrows
    public static <T extends Page> T getPage(Class<T> clazz) {
        T page = clazz.getDeclaredConstructor().newInstance();
        PageFactory.initElements(BrowserManager.getBrowser().getDriver(), page);
        return page;
    }
}
