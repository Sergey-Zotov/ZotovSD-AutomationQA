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

    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[1]/a")
    public WebElement myHomepage;

    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[2]/a")
    public WebElement myPage;

    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[3]/a")
    public WebElement projects;

    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[4]/a")
    public WebElement administration;

    @FindBy(xpath = "//*[@id=\"top-menu\"]/ul/li[5]/a")
    public WebElement help;

    @FindBy(xpath = "//*[@id=\"account\"]/ul/li[2]/a")
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

}
