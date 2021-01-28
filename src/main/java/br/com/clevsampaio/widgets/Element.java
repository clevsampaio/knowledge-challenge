package br.com.clevsampaio.widgets;

import br.com.clevsampaio.utils.Screenshot;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {
    private final WebDriver webDriver;
    private final By byElement;
    private WebDriverWait wait;
    private final int TIMEOUT_IN_SECONDS = 15;

    public Element(WebDriver driver, By by) {
        webDriver = driver;
        byElement = by;
    }

    public Element click() {
        try {
            WebElement element = waitVisibilityAndClickableElement(webDriver, byElement);
            element.click();
        }  catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        ExtentTestManager.getTest().log(Status.INFO, "O elemento " + byElement.toString() + " recebeu um clique.");
        return this;
    }

    public Element setText(String text) {
        try {
            WebElement element = waitVisibilityAndClickableElement(webDriver, byElement);
            element.sendKeys(text);
        }  catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        ExtentTestManager.getTest().log(Status.INFO, "O elemento " + byElement.toString() + " recebeu seguinte texto " + text + ".");
        return this;
    }

    public String getText() {
        try {
            WebElement element = waitVisibilityElement(webDriver, byElement);
            return element.getText();
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }

        return null;
    }

    public void selectByIndex(int index) {
        try {
            Select select = new Select(webDriver.findElement(byElement));
            select.selectByIndex(index);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            exceptionMessage(byElement);
        }
    }

    public WebElement getElement() {
        return webDriver.findElement(byElement);
    }

    private void exceptionMessage(By by) {
        String message = "O elemento " + by.toString() + " existente no DOM e tem um conjunto de recursos como oculto.";
        ExtentTestManager.getTest().log(Status.WARNING, message, Screenshot.capture());
        throw new ElementNotVisibleException(message);
    }

    private WebElement waitVisibilityElement(WebDriver driver, By by) {
        return visibilityOfElementLocated(driver, by);
    }

    private WebElement waitVisibilityAndClickableElement(WebDriver driver, By by) {
        WebElement elementVisibility = visibilityOfElementLocated(driver, by);
        return elementToBeClickable(driver, elementVisibility);
    }

    private WebElement visibilityOfElementLocated(WebDriver driver, By by) {
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement elementToBeClickable(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}