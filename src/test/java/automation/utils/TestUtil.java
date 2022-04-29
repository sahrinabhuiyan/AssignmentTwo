package automation.utils;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestUtil extends TestBase {

    public void navigateTo(String Url) {
        driver.get(Url); // This navigates and waits for the entire page to load before proceeding
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void validateTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle.toLowerCase(), expectedTitle.toLowerCase());
    }

    public WebElement waitForElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        Boolean found = wait.until(ExpectedConditions.or(
                ExpectedConditions.elementToBeClickable(element),
                ExpectedConditions.visibilityOfElementLocated(element)
        ));

        if (found) {
            return driver.findElement(element);
        } else {
            return driver.findElement(element);
        }
    }
    public void clickElement(By element) {
        try {
            WebElement localElement = waitForElement(element);
            driver.findElement(element);
            localElement.click();

        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebElement localElement = waitForElement(element);
            driver.findElement(element);
            localElement.click();
        }

    }
    public void jsClick( By element){
        WebElement localElement = driver.findElement(element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        localElement.click();
    }

    public void sendText(By element, String input) {
        WebElement localElement = waitForElement(element);
        localElement.sendKeys(input);
    }

    public void getText(By element) {
        WebElement localElement = waitForElement(element);
        String gotText = localElement.getText();
        System.out.println(gotText);
    }
    public void clearText(By element) {
        WebElement localElement = waitForElement(element);
        localElement.clear();
    }

    public void verifyText(By locator, String expectedText) {
        WebElement localElement = waitForElement(locator);
        String actualText = localElement.getText();
        Assert.assertEquals(expectedText.toLowerCase(), actualText.toLowerCase());
    }
    public void assertContains(By locator, String text) {
        WebElement localElement = waitForElement(locator);
        String actualText = localElement.getText();
        Assert.assertTrue(actualText.contains(text));
    }
    public void sendKeysJavascript(By element, String keysToSend) {
        WebElement el = driver.findElement(element);
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].value='"+ keysToSend +"';", el);
    }


}
