package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.cucumber.ElementName;
import zotov_sd.automation_qa.cucumber.PageName;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Заголовок страницы")
public class HeaderPage extends Page {

    @ElementName("Моя учётная запись")
    @FindBy(xpath = "//div[@id='account']//a[@class='my-account']")
    public WebElement myAccount;

    @ElementName("Моя страница")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"my-page\"]")
    public WebElement myPage;

    @ElementName("Проекты")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"projects\"]")
    public WebElement projects;

    @ElementName("Администрирование")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"administration\"]")
    public WebElement administration;

    @ElementName("Помощь")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"help\"]")
    public WebElement help;

    @ElementName("Выйти")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"logout\"]")
    public WebElement out;

    @ElementName("Войти")
    @FindBy(xpath = "//*[@id='account']//a[@class='login']")
    public WebElement logIn;

    @ElementName("Регистрация")
    @FindBy(xpath = "//*[@id=\"account\"]//a[@class=\"register\"]")
    public WebElement registration;

    @FindBy(xpath = "//*[@id=\"loggedas\"]")
    public WebElement enteredAs;

    @FindBy(xpath = "//*[@id=\"top-menu\"]")
    public WebElement pageTitle;

    @ElementName("Поиск")
    @FindBy(xpath = "//*[@id=\"q\"]")
    public WebElement search;

    @ElementName("Домашняя страница")
    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"home\"]")
    public WebElement myHomepage;

}
