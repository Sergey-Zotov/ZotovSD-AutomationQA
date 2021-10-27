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

    @FindBy(xpath = "//*[@id=\"main\"]//div[@id=\"content\"]/h2")
    public WebElement content;

    @FindBy(xpath = "//table[@class='list users']//a[@class=\"sort asc icon icon-sorted-desc\"]")
    public WebElement users;

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='username']")
    public List<WebElement> userNameList;

    @FindBy(xpath = "//table[@class='list users']//a[.=\"Имя\"]")
    public WebElement firstName;

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='firstname']")
    public List<WebElement> firstNameList;

    @FindBy(xpath = "//table[@class='list users']//a[.=\"Фамилия\"]")
    public WebElement lastName;

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='lastname']")
    public List<WebElement> lastNameList;

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='created_on']")
    public List<WebElement> creationDates;

    @FindBy(xpath = "//*[@id=\"content\"]//a[@class=\"icon icon-add\"]")
    public WebElement newUser;


    public WebElement button(String text) {
        return BrowserManager.getBrowser().getDriver().findElement(By.xpath("//table[@class='list users']/thead//th[.='" + text + "']"));
    }

}
