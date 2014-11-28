package cucumberTests;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SeleniumTest {

    private WebDriver driver;

    private String baseUrl;

    public void setUp() throws Exception {

driver = new FirefoxDriver();

    baseUrl = "http://localhost:8080/DhbwFestivalPlanner/";
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.manage().window().maximize();
    }

     public void tearDown() {

     driver.quit();

     }

    public void goToHomePage(){

     driver.get(baseUrl);

     }}
