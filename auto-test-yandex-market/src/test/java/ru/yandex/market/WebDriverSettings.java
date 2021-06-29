package ru.yandex.market;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverSettings {

    public ChromeDriver driver;
    

    public static final String URL_GOOGLE = "https://www.google.com/";
    public static final String TITLE_GOOGLE = "Google";

    public static final String URL_YANDEX_MARKET = "https://market.yandex.ru/";

    public static final String REQUEST_YANDEX_MARKET = "яндекс маркет";
    public static final String YANDEX_MARKET_TITLE = "Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов";


    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    /*@After
    public void close(){
        driver.quit();
    }*/
}
