package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.cucumber.ElementName;
import zotov_sd.automation_qa.cucumber.PageName;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Администрирование")
public class AdministrationPage extends Page {

    @FindBy(xpath = "//*[@id=\"main\"]//div[@id=\"content\"]/h2")
    public WebElement content;

    @ElementName("Пользователи")
    @FindBy(xpath = "//div[@id='admin-menu']//a[contains(@class,'users')]")
    public WebElement users;

}
