package ru.yandex.market;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class FistLinkYandexMarket extends WebDriverSettings{

    public WebElement firstElement;
    public String firstUrl;

    @Before
    public void google(){
        driver.get(URL_GOOGLE);
    }

    @Test
    public void openGoogle(){
        String title = driver.getTitle();
        Assert.assertTrue(title.equals(TITLE_GOOGLE));
    }

    @Test
    public void fistLink(){
        firstUrl();
        Assert.assertTrue(firstUrl.equals(URL_YANDEX_MARKET));
    }

    @Test
    public void openYandexMarket(){
        firstUrl();
        firstElement.click();
        String title = driver.getTitle();
        Assert.assertTrue(title.equals(YANDEX_MARKET_TITLE));

    }

    public String firstUrl(){
        driver.findElementByXPath("//input[@name='q']")
                .sendKeys(REQUEST_YANDEX_MARKET + Keys.ENTER);
        firstElement  = driver.findElementByXPath("//div[@class='yuRUbf']/a");
        firstUrl = firstElement.getAttribute("href");
        return firstUrl;
    }






}
