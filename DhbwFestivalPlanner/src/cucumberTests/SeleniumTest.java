package cucumberTests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



 
public class SeleniumTest {

    private WebDriver driver;

    private String baseUrl;


    public void setUp() throws Exception {

    	driver = new  InternetExplorerDriver ();
    	

    baseUrl = "http://localhost:8080/DhbwFestivalPlanner/";
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    driver.manage().window().maximize();
    }

     public void tearDown() {

     driver.quit();

     }

    public void goToHomePage(){

     driver.get(baseUrl);

     }


    
    //LOGIN
    
    /*public void notLoggedIn(){
    	assertEquals("Festival Planner", driver.getTitle());
    }
    
    public void fillInCorrect(){
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test3");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test");
    }
    public void submit(){
    	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }

	public void checkOverview() {
		assertEquals("Deine Events:", driver.getTitle());
		
	}

	public void fillInWrong() {
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("test");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("hallo");
		
	}

	public void checkLoginError() {
		assertTrue(driver.findElement(By.id("loginerror")).isDisplayed());
		
	}*/
	
	//REGISTER
	

	/*   public void clickRegister(){
        driver.findElement(By.xpath("//div[@onclick='showregister(this)']")).click();
    }
    
    public void fillOutRegister(){
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("test3@test.com");
        driver.findElement(By.cssSelector("#registerform > form > input[name=\"username\"]")).clear();
        driver.findElement(By.cssSelector("#registerform > form > input[name=\"username\"]")).sendKeys("Test3");
        driver.findElement(By.cssSelector("#registerform > form > input[name=\"password\"]")).clear();
        driver.findElement(By.cssSelector("#registerform > form > input[name=\"password\"]")).sendKeys("test");
        
    }
    public void submitRegister(){
    	driver.findElement(By.cssSelector("#registerform > form > input[type=\"submit\"]")).click();
    }
    public void returnToLoginRegister(){
		driver.findElement(By.cssSelector("div.tab.actualtab")).click();
	      assertEquals("Login", driver.findElement(By.cssSelector("div.tab.actualtab")).getText());
    }
    public void firstLoginRegister(){
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test3");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
          assertEquals("Deine Events:", driver.getTitle());

    } */

    //DISPLAY
    
	public void logInUser() {
		driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test3");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
	}
	public void isEventCreated(){
		driver.findElement(By.linkText("The new event")).isDisplayed();

	}
	public void clickEvent(){
		driver.findElement(By.linkText("The new event")).click();
	}
	public void showInfos(){
		driver.findElement(By.id("guest1")).isDisplayed();
	}
	public void notLoggedIn(){
		assertEquals("Festival Planner", driver.getTitle());
	}
	public void followGuestLink(){
		driver.get("http://localhost:8080/DhbwFestivalPlanner/event/guestView?id=3");
	}
	public void displayGuestView(){
		assertEquals("THE NEW EVENT", driver.findElement(By.id("titel")).getText());
	}
    
    //CREATE/EDIT
    
    /*public void logInUser() {
		driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test3");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
	}
    
    public void createEvent(){
    	driver.findElement(By.id("createevent")).click();
    	driver.get(baseUrl + "/event/edit");

    }
    public void displayForm(){
    	assertEquals("Festival Planner: Party bearbeiten", driver.getTitle());
    }
    public void clickPen(){
    	driver.findElement(By.linkText("The new event")).click();
    	driver.findElement(By.cssSelector("i.fa.fa-pencil")).click();
    	driver.get(baseUrl + "/event/edit?id=3");
    }
    public void edit(){
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("the even newer event");
        submit();
    }
    public void updatePage(){
    	driver.findElement(By.linkText("the even newer event"));
    }
    public void fillOutEverything(){
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Silvesterparty an der DH");
        driver.findElement(By.id("date")).click();
        driver.findElement(By.id("date")).clear();
        driver.findElement(By.id("date")).sendKeys("31.12.2014");
        driver.findElement(By.id("time")).clear();
        driver.findElement(By.id("time")).sendKeys("22:00");
        driver.findElement(By.id("veranstalter")).clear();
        driver.findElement(By.id("veranstalter")).sendKeys("DHBW Karlsruhe");
        driver.findElement(By.id("address")).clear();
        driver.findElement(By.id("address")).sendKeys("Erzbergerstrasse 121");
        driver.findElement(By.id("plz")).clear();
        driver.findElement(By.id("plz")).sendKeys("76133");
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys("Karlsruhe");
        driver.findElement(By.id("beschreibung")).clear();
        driver.findElement(By.id("beschreibung")).sendKeys("Silvester!!!");
    }
    public void submit(){
    	driver.findElement(By.xpath("//input[@value=\"Los geht's\"]")).click();
    	
    }
    public void addEvent(){
    	driver.get("http://localhost:8080/DhbwFestivalPlanner");
    	driver.findElement(By.xpath("(//a[contains(text(),'Silvesterparty an der DH')])"));
    }
    public void checkOverview(){
    	driver.get("http://localhost:8080/DhbwFestivalPlanner");
    	assertEquals("Deine Events:", driver.getTitle());
    }
    public void showHints(){
    	driver.findElement(By.xpath("(//div[contains(text(),'Bitte fülle alle Felder aus!')])"));
    }*/
}
