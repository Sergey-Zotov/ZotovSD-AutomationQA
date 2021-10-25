package zotov_sd.automation_qa.ui.browser;

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
}
