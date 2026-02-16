package com.testing.utils;

import com.testing.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseWebTest {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpWebDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-notifications");
            options.addArguments("--no-sandbox");
            if (TestConfig.getBoolean("web.headless")) {
                options.addArguments("--headless=new");
            }

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        } catch (Exception e) {
            throw new SkipException("Web tests skipped: failed to initialize browser. " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
