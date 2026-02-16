package com.testing.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionPage extends BaseWebPage {
    private final By sectionTitle = By.tagName("h1");

    public SectionPage(WebDriver driver) {
        super(driver);
    }

    public String getSectionTitle() {
        return waitVisible(sectionTitle).getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public HomePage returnToHomeByLogo() {
        waitClickable(By.cssSelector("header a[href='/']")).click();
        return new HomePage(driver);
    }
}
