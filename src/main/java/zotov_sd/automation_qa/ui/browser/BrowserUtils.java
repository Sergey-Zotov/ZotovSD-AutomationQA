package zotov_sd.automation_qa.ui.browser;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import zotov_sd.automation_qa.property.Property;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BrowserUtils {

    public static List<String> getElementsText(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Проверка отображения елемента {0} на странице")
    public static boolean isElementDisplayed(WebElement element) {
        try {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        } finally {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(Property.getIntegerProperty("element.timeout"), TimeUnit.SECONDS);
        }
    }

    @Step("Проверка отображения проекта с id={0} на странице")
    public static Boolean isProjectDisplayed(Integer id) throws NoSuchElementException {
        try {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement element = BrowserManager.getBrowser().getDriver().findElement(By.xpath("//*[@id=\"projects-index\"]//a[@href=\"/projects/" + id + "\"]"));
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        } finally {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(Property.getIntegerProperty("element.timeout"), TimeUnit.SECONDS);
        }
    }

    @Step("Нажимаю на элемент {1}")
    public static void click(WebElement element, String description) {
        element.click();
    }
}
