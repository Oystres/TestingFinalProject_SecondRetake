package com.testing.pages.mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WikipediaOnboardingPage extends BaseMobilePage {
    private final By skipButton = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private final By searchContainer = AppiumBy.id("org.wikipedia:id/search_container");

    public WikipediaOnboardingPage(AndroidDriver driver) {
        super(driver);
    }

    public void skipOnboardingIfShown() {
        if (!driver.findElements(skipButton).isEmpty()) {
            tap(skipButton);
        }
        isDisplayed(searchContainer);
    }
}
