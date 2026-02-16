package com.testing.utils;

import com.testing.config.TestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseMobileTest {
    protected AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpMobileDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(TestConfig.get("mobile.platformName"))
                    .setAutomationName(TestConfig.get("mobile.automationName"))
                    .setDeviceName(TestConfig.get("mobile.deviceName"))
                    .setAppPackage(TestConfig.get("mobile.appPackage"))
                    .setAppActivity(TestConfig.get("mobile.appActivity"))
                    .setNewCommandTimeout(Duration.ofSeconds(TestConfig.getInt("mobile.newCommandTimeout")));

            driver = new AndroidDriver(new URL(TestConfig.get("mobile.appiumServerUrl")), options);
        } catch (Exception e) {
            throw new SkipException("Mobile tests skipped: failed to connect to Appium/emulator. " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownMobileDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
