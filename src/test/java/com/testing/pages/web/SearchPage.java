package com.testing.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BaseWebPage {
    private final By resultTitle = By.xpath("//h1[contains(.,'Результаты')]");
    private final By anyResultLink = By.cssSelector("main a[href*='/tags/']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultsHeaderVisible() {
        return waitVisible(resultTitle).isDisplayed();
    }

    public int getResultsCount() {
        waitVisible(anyResultLink);
        return driver.findElements(anyResultLink).size();
    }
}
