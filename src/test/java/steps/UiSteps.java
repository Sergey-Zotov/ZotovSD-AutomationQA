package steps;


import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import org.openqa.selenium.WebElement;
import zotov_sd.automation_qa.allure.AllureAssert;
import zotov_sd.automation_qa.context.Context;
import zotov_sd.automation_qa.cucumber.PageObjectHelper;
import zotov_sd.automation_qa.model.user.User;
import zotov_sd.automation_qa.ui.pages.HeaderPage;
import zotov_sd.automation_qa.ui.pages.LoginPage;

import java.util.List;

import static zotov_sd.automation_qa.ui.browser.BrowserUtils.getElementsText;
import static zotov_sd.automation_qa.ui.pages.Page.getPage;
import static zotov_sd.automation_qa.utils.CompareUtils.assertListSortedByDateDesc;


public class UiSteps {

    @И("Авторизоваться как пользователь \"(.+)\"")
    public void authByUser(String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);
        getPage(LoginPage.class).login(user);
    }

    @И("Авторизоваться по логину \"(.+)\" и паролю \"(.+)\"")
    public void authByLoginAndPassword(String login, String password) {
        getPage(LoginPage.class).login(login, password);
    }

    @И("Текст элемента Моя учётная запись - \"(.*)\"")
    public void assertMyAccountText(String expectedText) {
        AllureAssert.assertEquals(getPage(HeaderPage.class).myAccount.getText(), expectedText);
    }

    @Если("На странице {string} нажать на элемент {string}")
    public void clickOnElementOnPage(String pageName, String elementName) {
        PageObjectHelper.findElement(pageName, elementName).click();
    }

    @И("На странице {string} в поле {string} ввести текст {string}")
    public void sendKeysToElementOnPage(String pageName, String elementName, String charSequence) {
        PageObjectHelper.findElement(pageName, elementName).sendKeys(charSequence);
    }

    @И("На странице {string} тексты элементов {string} отсортированы по дате по убыванию")
    public void assertElementsTextsIsSortedByDateDesc(String pageName, String elementsName) {
        List<WebElement> elements = PageObjectHelper.findElements(pageName, elementsName);
        List<String> elementsTexts = getElementsText(elements);
        assertListSortedByDateDesc(elementsTexts);
    }

}
