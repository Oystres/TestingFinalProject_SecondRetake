package com.testing.tests.web;

import com.testing.pages.web.ArticlePage;
import com.testing.pages.web.HomePage;
import com.testing.pages.web.SearchPage;
import com.testing.pages.web.SectionPage;
import com.testing.utils.BaseWebTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CybersportWebTests extends BaseWebTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        if (driver == null) {
            throw new SkipException("Web driver is not initialized");
        }
        homePage = new HomePage(driver).open();
    }

    @Test(description = "Главная страница открывается и показывает базовые элементы интерфейса")
    public void homePageShouldLoadWithHeaderElements() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Логотип сайта в хедере должен быть видим");
        Assert.assertTrue(homePage.getTopMenuLinksCount() >= 5, "В верхнем меню ожидается минимум 5 ссылок");
    }

    @Test(description = "Переход в раздел матчей через меню открывает корректную страницу")
    public void shouldOpenMatchesSectionFromTopMenu() {
        SectionPage sectionPage = homePage.openMatchesFromMenu();

        Assert.assertTrue(sectionPage.getCurrentUrl().contains("/matches"), "URL должен содержать /matches");
        Assert.assertEquals(sectionPage.getSectionTitle(), "Матчи", "Заголовок раздела должен быть 'Матчи'");
    }

    @Test(description = "Открытие первой статьи с главной страницы ведёт на страницу материала")
    public void shouldOpenArticleFromHomePage() {
        ArticlePage articlePage = homePage.openFirstArticle();

        Assert.assertTrue(articlePage.getCurrentUrl().contains("/tags/"), "URL статьи должен содержать /tags/");
        Assert.assertTrue(articlePage.isArticleHeaderDisplayed(), "На странице статьи должен отображаться заголовок");
        Assert.assertFalse(articlePage.getArticleHeaderText().isBlank(), "Текст заголовка статьи не должен быть пустым");
    }

    @Test(description = "Поиск возвращает страницу результатов и список найденных материалов")
    public void searchShouldReturnResults() {
        SearchPage searchPage = homePage.openSearchByUrl("dota");

        Assert.assertTrue(searchPage.isResultsHeaderVisible(), "На странице поиска должен быть заголовок 'Результаты'");
        Assert.assertTrue(searchPage.getResultsCount() > 0, "По запросу должны быть найдены материалы");
    }

    @Test(description = "Логотип из раздела возвращает пользователя на главную страницу")
    public void logoShouldNavigateBackToHomePage() {
        SectionPage sectionPage = homePage.openMatchesFromMenu();
        HomePage returnedHome = sectionPage.returnToHomeByLogo();

        Assert.assertTrue(returnedHome.isLogoDisplayed(), "После клика по логотипу пользователь должен вернуться на главную");
    }
}
