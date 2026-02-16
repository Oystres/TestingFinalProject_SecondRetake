package com.testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WikipediaSearchPage extends BaseMobilePage {
    private final By searchContainer = AppiumBy.id("org.wikipedia:id/search_container");
    private final By searchInput = AppiumBy.id("org.wikipedia:id/search_src_text");
    private final By searchResults = AppiumBy.id("org.wikipedia:id/page_list_item_title");

    public WikipediaSearchPage(AndroidDriver driver) {
        super(driver);
    }

    public WikipediaSearchPage openSearch() {
        tap(searchContainer);
        isDisplayed(searchInput);
        return this;
    }

    public WikipediaSearchPage typeQuery(String query) {
        driver.findElement(searchInput).sendKeys(query);
        return this;
    }

    public int getResultsCount() {
        wait.until(d -> !driver.findElements(searchResults).isEmpty());
        return driver.findElements(searchResults).size();
    }

    public WikipediaArticlePage openFirstResult() {
        tap(searchResults);
        return new WikipediaArticlePage(driver);
    }
}
