package zotov_sd.automation_qa.test.ui_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumSampleTest {

    @Test
    public void sampleSeleniumTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");

        Thread.sleep(5000);

        WebElement element = driver.findElement(By.name("q"));
        WebElement element2 = driver.findElement(By.xpath("//*[@name='q']"));
        element.sendKeys("I-Teco");
        element.submit();



        Thread.sleep(5000);

        driver.quit();
    }

}
