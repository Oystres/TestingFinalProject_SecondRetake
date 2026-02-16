# UI Automation Project: Cybersport.ru + Wikipedia Android

Тестовый проект на **Java 11 + Maven**, включающий:
- веб‑автотесты для сайта [cybersport.ru](https://www.cybersport.ru/) на **Selenium WebDriver + TestNG**;
- мобильные автотесты для Android‑приложения **Wikipedia** на **Appium + TestNG**.

## Технологический стек
- Java 11+
- Maven
- TestNG
- Selenium WebDriver
- WebDriverManager
- Appium Java Client

## Структура проекта

```text
src/test/java/com/testing
  config/
    TestConfig.java
  pages/
    web/
      BaseWebPage.java
      HomePage.java
      SectionPage.java
      SearchPage.java
      ArticlePage.java
    mobile/
      BaseMobilePage.java
      WikipediaOnboardingPage.java
      WikipediaMainPage.java
      WikipediaSearchPage.java
      WikipediaArticlePage.java
  tests/
    web/
      CybersportWebTests.java
    mobile/
      WikipediaMobileTests.java
  utils/
    BaseWebTest.java
    BaseMobileTest.java

src/test/resources
  config.properties
  testng.xml
```

## Реализованные сценарии

### Web (Cybersport.ru)
1. Проверка загрузки главной страницы и базовых элементов шапки.
2. Переход в раздел **Матчи** из верхнего меню и проверка URL/заголовка.
3. Открытие первой статьи с главной и проверка, что страница материала открылась.
4. Поиск (`/search?q=dota`) и проверка наличия результатов.
5. Возврат на главную страницу по клику на логотип.

### Mobile (Wikipedia Android)
1. Проверка отображения главного экрана (поисковый блок).
2. Поиск статьи по ключевому слову и проверка, что есть результаты.
3. Открытие статьи, проверка заголовка и прокрутка к текстовому элементу.

## Подготовка окружения

### Общее
1. Установить JDK 11+.
2. Установить Maven.
3. Клонировать проект.

### Для web-тестов
- Google Chrome должен быть установлен.
- ChromeDriver скачивается автоматически через WebDriverManager.

### Для mobile-тестов
1. Установить Android Studio и создать эмулятор (или подключить реальное устройство).
2. Установить приложение Wikipedia (`org.wikipedia`).
3. Установить и запустить Appium Server (по умолчанию `http://127.0.0.1:4723`).
4. Проверить доступность устройства через `adb devices`.

## Конфигурация

Параметры лежат в `src/test/resources/config.properties`.

Примеры:
- `web.baseUrl=https://www.cybersport.ru`
- `web.headless=true`
- `mobile.appiumServerUrl=http://127.0.0.1:4723`
- `mobile.appPackage=org.wikipedia`

Любое значение можно переопределить через `-D`:

```bash
mvn test -Dweb.headless=false
```

## Запуск тестов

### Все тесты
```bash
mvn test
```

### Только web-тесты
```bash
mvn test -Dtest=com.testing.tests.web.CybersportWebTests
```

### Только mobile-тесты
```bash
mvn test -Dtest=com.testing.tests.mobile.WikipediaMobileTests
```

## Проверенное окружение (ориентир)
- Java 11+
- Maven 3.8+
- Chrome (актуальная стабильная версия)
- Android Emulator + Appium 2.x

## Примечания
- Для стабильности используются явные ожидания (`WebDriverWait`), а не `Thread.sleep`.
- Для web-части применён базовый Page Object Model.
- Mobile-тесты также организованы через page-objects экранов.
