package com.testing.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticlePage extends BaseWebPage {
    private final By articleHeader = By.cssSelector("main h1, main h2, [data-qa='material-title']");

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public boolean isArticleHeaderDisplayed() {
        return waitVisible(articleHeader).isDisplayed();
    }

    public String getArticleHeaderText() {
        return waitVisible(articleHeader).getText().trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}