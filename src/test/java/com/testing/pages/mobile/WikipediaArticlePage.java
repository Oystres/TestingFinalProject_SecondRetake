package com.testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WikipediaArticlePage extends BaseMobilePage {
    private final By articleTitle = AppiumBy.id("org.wikipedia:id/view_page_title_text");

    public WikipediaArticlePage(AndroidDriver driver) {
        super(driver);
    }

    public String getArticleTitle() {
        return text(articleTitle);
    }

    public boolean scrollToText(String textValue) {
        String uiAutomatorExpression = "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\""
                + textValue + "\")";
        By locator = AppiumBy.androidUIAutomator(uiAutomatorExpression);
        return isDisplayed(locator);
    }
}
