package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CreateUserPage extends Page {

    @FindBy(xpath = "//*[@id=\"new_user\"]/p/input[@value=\"Создать\"]")
    public WebElement createUser;

    @FindBy(xpath = "//*[@id=\"user_generate_password\"]")
    public WebElement userGeneratePassword;

    @FindBy(xpath = "//*[@id=\"user_mail\"]")
    public WebElement userMail;

    @FindBy(xpath = "//*[@id=\"user_lastname\"]")
    public WebElement userLastName;

    @FindBy(xpath = "//*[@id=\"user_firstname\"]")
    public WebElement userFirstName;

    @FindBy(xpath = "//*[@id=\"user_login\"]")
    public WebElement userLogin;

    @FindBy(xpath = "//*[@id=\"flash_notice\"]")
    public WebElement userCrated;
}
