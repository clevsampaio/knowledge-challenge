package br.com.clevsampaio.webdrivers;

import br.com.clevsampaio.utils.Property;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.service.ExtentTestManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createInstance() {
        String browser = System.getProperty("property.browser");
        String type = System.getProperty("property.type");

        if (StringUtils.isEmpty(browser)){
            browser = Property.get("property.browser");
            System.setProperty("property.browser", browser);
        }

        if (StringUtils.isEmpty(type)){
            type = Property.get("property.type");
            System.setProperty("property.type", type);
        }

        try {
            switch (browser) {
                case "firefox":
                    if (type.equals("remote")) {
                        WebDriverManager.firefoxdriver().setup();
                        return new FirefoxDriver();
                    } else if(type.equals("headless")) {
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxLocalOptions = new FirefoxOptions();
                        firefoxLocalOptions.setHeadless(true);
                        return new FirefoxDriver(firefoxLocalOptions);
                    } else {
                        WebDriverManager.firefoxdriver().setup();
                        return new FirefoxDriver();
                    }
                case "chrome":
                    if (type.equals("remote")) {
                        return DriverRemote.connect(new ChromeOptions());
                    } else if(type.equals("headless")) {
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeLocalOptions = new ChromeOptions();
                        chromeLocalOptions.addArguments("--headless");
                        chromeLocalOptions.addArguments("--disable-gpu");
                        chromeLocalOptions.addArguments("--no-sandbox");
                        chromeLocalOptions.addArguments("--disable-dev-shm-usage");
                        return new ChromeDriver(chromeLocalOptions);
                    } else {
                        WebDriverManager.chromedriver().setup();
                        return new ChromeDriver();
                    }
                default:
                    String message = "DriverFactory.getInstance() recebeu um argumento inválido.";
                    ExtentTestManager.getTest().log(Status.FATAL, message);
                    throw new IllegalArgumentException(message);
            }
        } catch (SessionNotCreatedException e) {
            String message = "Sessão não criada, versão do driver não suportada.";
            ExtentTestManager.getTest().log(Status.FATAL, message);
            throw new SessionNotCreatedException(message, e);
        } catch (WebDriverException e) {
            String message = "Não foi possível encontrar o binário do driver.";
            ExtentTestManager.getTest().log(Status.FATAL, message);
            throw new WebDriverException(message, e);
        }
    }
}