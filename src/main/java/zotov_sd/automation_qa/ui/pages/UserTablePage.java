package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.cucumber.ElementName;
import zotov_sd.automation_qa.cucumber.PageName;
import zotov_sd.automation_qa.ui.browser.BrowserManager;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Пользователи")
public class UserTablePage extends Page {

    @ElementName("Пользователи")
    @FindBy(xpath = "//*[@id=\"main\"]//div[@id=\"content\"]/h2")
    public WebElement content;

    @ElementName("Столбец Пользователи")
    @FindBy(xpath = "//table[@class='list users']//a[@class=\"sort asc icon icon-sorted-desc\"]")
    public WebElement users;

    @ElementName("Список Логинов")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='username']")
    public List<WebElement> userNameList;

    @ElementName("Имя")
    @FindBy(xpath = "//table[@class='list users']//a[.=\"Имя\"]")
    public WebElement firstName;

    @ElementName("Список Имен")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='firstname']")
    public List<WebElement> firstNameList;

    @ElementName("Фамилия")
    @FindBy(xpath = "//table[@class='list users']//a[.=\"Фамилия\"]")
    public WebElement lastName;

    @ElementName("Список Фамилий")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='lastname']")
    public List<WebElement> lastNameList;

    @ElementName("Даты создания")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='created_on']")
    public List<WebElement> creationDates;

    @ElementName("Новый пользователь")
    @FindBy(xpath = "//*[@id=\"content\"]//a[@class=\"icon icon-add\"]")
    public WebElement newUser;

    @ElementName("Создано")
    @FindBy(xpath = "//th[@title='Сортировать по \"Создано\"']/a")
    private WebElement created;


    public WebElement button(String text) {
        return BrowserManager.getBrowser().getDriver().findElement(By.xpath("//table[@class='list users']/thead//th[.='" + text + "']"));
    }

}
