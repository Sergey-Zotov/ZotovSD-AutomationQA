package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProjectTablePage extends Page {

    @FindBy(xpath = "//*[@id=\"main-menu\"]//a[@class=\"projects selected\"]")
    public WebElement projectsSelected;

    @FindBy(xpath = "//*[@id=\"values_status_1\"]")
    public WebElement projectStatus;

    @FindBy(xpath = "//*[@id=\"values_status_1\"]/option[@value=\"5\"]")
    public WebElement statusClosed;

    @FindBy(xpath = "//*[@id=\"query_form_with_buttons\"]//a[@href=\"#\"]")
    public WebElement applying;


}

