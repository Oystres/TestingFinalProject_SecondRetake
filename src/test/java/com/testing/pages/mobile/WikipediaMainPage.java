package com.testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WikipediaMainPage extends BaseMobilePage {
    private final By searchContainer = AppiumBy.id("org.wikipedia:id/search_container");

    public WikipediaMainPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isSearchVisible() {
        return isDisplayed(searchContainer);
    }
}
