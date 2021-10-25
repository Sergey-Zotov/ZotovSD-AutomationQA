package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.ui.browser.BrowserManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserTablePage extends Page {

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='created_on']")
    public List<WebElement> creationDates;

    public WebElement button(String text) {
        return BrowserManager.getBrowser().getDriver().findElement(By.xpath("//table[@class='list users']/thead//th[.='" + text + "']"));
    }

}
