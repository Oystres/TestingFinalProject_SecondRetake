package com.testing.tests.mobile;

import com.testing.pages.mobile.WikipediaArticlePage;
import com.testing.pages.mobile.WikipediaMainPage;
import com.testing.pages.mobile.WikipediaOnboardingPage;
import com.testing.pages.mobile.WikipediaSearchPage;
import com.testing.utils.BaseMobileTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WikipediaMobileTests extends BaseMobileTest {

    @BeforeMethod(alwaysRun = true)
    public void prepareAppState() {
        if (driver == null) {
            throw new SkipException("Mobile driver is not initialized");
        }
        new WikipediaOnboardingPage(driver).skipOnboardingIfShown();
    }

    @Test(description = "Главный экран Wikipedia отображает строку поиска")
    public void mainScreenShouldBeDisplayed() {
        WikipediaMainPage mainPage = new WikipediaMainPage(driver);
        Assert.assertTrue(mainPage.isSearchVisible(), "На главном экране должен быть виден блок поиска");
    }

    @Test(description = "Поиск статьи возвращает результаты по ключевому слову")
    public void searchShouldReturnResults() {
        WikipediaSearchPage searchPage = new WikipediaSearchPage(driver)
                .openSearch()
                .typeQuery("Selenium");

        Assert.assertTrue(searchPage.getResultsCount() > 0, "Поиск должен вернуть хотя бы один результат");
    }

    @Test(description = "Открытая статья содержит ожидаемый заголовок и поддерживает прокрутку")
    public void articleShouldOpenWithExpectedTitleAndScrollableContent() {
        WikipediaArticlePage articlePage = new WikipediaSearchPage(driver)
                .openSearch()
                .typeQuery("Appium")
                .openFirstResult();

        String title = articlePage.getArticleTitle();
        Assert.assertTrue(title.toLowerCase().contains("appium"), "Заголовок статьи должен содержать 'Appium'");
        Assert.assertTrue(articlePage.scrollToText("References"), "Статья должна прокручиваться до блока References");
    }
}
