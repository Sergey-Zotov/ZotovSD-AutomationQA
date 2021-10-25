package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class HeaderPage extends Page {

    @FindBy(xpath = "//div[@id='account']//a[@class='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@id='account']//a[@class='my-account']")
    public WebElement myAccount;

    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"home\"]")
    public WebElement myHomepage;

    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"my-page\"]")
    public WebElement myPage;

    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"projects\"]")
    public WebElement projects;

    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"administration\"]")
    public WebElement administration;

    @FindBy(xpath = "///*[@id=\"top-menu\"]//a[@class=\"help\"]")
    public WebElement help;

    @FindBy(xpath = "//*[@id=\"top-menu\"]//a[@class=\"logout\"]")
    public WebElement out;

    @FindBy(xpath = "//*[@id='account']//a[@class='login']")
    public WebElement logIn;

    @FindBy(xpath = "//*[@id=\"account\"]//a[@class=\"register\"]")
    public WebElement registration;

    @FindBy(xpath = "//*[@id=\"loggedas\"]")
    public WebElement enteredAs;

    @FindBy(xpath = "//*[@id=\"top-menu\"]")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"q\"]")
    public WebElement search;

    @FindBy(xpath = "//*[@id=\"content\"]")
    public WebElement content;

}
