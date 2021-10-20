package zotov_sd.automation_qa.ui.pages;

import org.openqa.selenium.support.PageFactory;
import zotov_sd.automation_qa.ui.browser.BrowserManager;

public abstract class Page {

    Page() {
        PageFactory.initElements(BrowserManager.getBrowser().getDriver(), this);
    }

}
