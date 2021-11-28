package zotov_sd.automation_qa.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import zotov_sd.automation_qa.cucumber.ElementName;
import zotov_sd.automation_qa.cucumber.PageName;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Проекты")
public class ProjectTablePage extends Page {

    @ElementName("Проекты")
    @FindBy(xpath = "//*[@id=\"main-menu\"]//a[@class=\"projects selected\"]")
    public WebElement projectsSelected;

    @ElementName("Статус проектов")
    @FindBy(xpath = "//*[@id=\"values_status_1\"]")
    public WebElement projectStatus;

    @ElementName("Закрытые")
    @FindBy(xpath = "//*[@id=\"values_status_1\"]/option[@value=\"5\"]")
    public WebElement statusClosed;

    @ElementName("Применить")
    @FindBy(xpath = "//*[@id=\"query_form_with_buttons\"]//a[@href=\"#\"]")
    public WebElement applying;


}

