package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.cucumber.ElementName;
import zotov_sd.automation_qa.cucumber.PageName;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Новый пользователь")
public class CreateUserPage extends Page {

    @ElementName("Создать")
    @FindBy(xpath = "//*[@id=\"new_user\"]/p/input[@value=\"Создать\"]")
    public WebElement createUser;

    @ElementName("Чекбокс создание пароля")
    @FindBy(xpath = "//*[@id=\"user_generate_password\"]")
    public WebElement userGeneratePassword;

    @ElementName("Email")
    @FindBy(xpath = "//*[@id=\"user_mail\"]")
    public WebElement userMail;

    @ElementName("Имя")
    @FindBy(xpath = "//*[@id=\"user_lastname\"]")
    public WebElement userLastName;

    @ElementName("Фамилия")
    @FindBy(xpath = "//*[@id=\"user_firstname\"]")
    public WebElement userFirstName;

    @ElementName("Логин")
    @FindBy(xpath = "//*[@id=\"user_login\"]")
    public WebElement userLogin;

    @ElementName("Пользователь создан")
    @FindBy(xpath = "//*[@id=\"flash_notice\"]")
    public WebElement userCrated;
}
