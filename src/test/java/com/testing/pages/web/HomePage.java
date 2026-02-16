package com.testing.pages.web;

import com.testing.config.TestConfig;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseWebPage {
    private final By siteLogo = By.cssSelector("header a[href='/']");
    private final By topMenuLinks = By.cssSelector("header nav a[href^='/']");
    private final By articleLinks = By.cssSelector("main a[href^='/tags/']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(TestConfig.get("web.baseUrl"));
        waitVisible(siteLogo);
        return this;
    }

    public boolean isLogoDisplayed() {
        return waitVisible(siteLogo).isDisplayed();
    }

    public int getTopMenuLinksCount() {
        waitVisible(topMenuLinks);
        return driver.findElements(topMenuLinks).size();
    }

    public SectionPage openMatchesFromMenu() {
        waitClickable(By.cssSelector("header nav a[href='/matches']")).click();
        return new SectionPage(driver);
    }

    public ArticlePage openFirstArticle() {
        waitVisible(articleLinks);
        String startUrl = driver.getCurrentUrl();

        List<WebElement> visibleLinks = driver.findElements(articleLinks).stream()
                .filter(WebElement::isDisplayed)
                .filter(link -> {
                    String href = link.getAttribute("href");
                    return href != null && href.contains("/tags/") && href.length() > 35;
                })
                .collect(Collectors.toList());

        if (visibleLinks.isEmpty()) {
            throw new IllegalStateException("No visible article links were found on the home page");
        }

        WebElement firstArticleLink = visibleLinks.get(0);
        try {
            firstArticleLink.click();
        } catch (Exception clickException) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstArticleLink);
        }
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(startUrl)));
        wait.until(ExpectedConditions.urlContains("/tags/"));

        return new ArticlePage(driver);
    }

    public SearchPage openSearchByUrl(String query) {
        driver.get(TestConfig.get("web.baseUrl") + "/search?q=" + query);
        return new SearchPage(driver);
    }
}