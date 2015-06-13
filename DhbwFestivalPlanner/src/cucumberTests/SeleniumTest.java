package cucumberTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.junit.Assert.*;



 
public class SeleniumTest {

    private WebDriver driver;

    private String baseUrl;
    private boolean acceptNextAlert = true;

    public void setUp() throws Exception {
    	System.setProperty("webdriver.chrome.driver", "C:/Users/Yvonne/Downloads/chromedriver_win32/chromedriver.exe");
    	driver = new  ChromeDriver ();
    	

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
    
    public void notLoggedIn(){
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
		
	}
	
	//REGISTER
	

	   public void clickRegister(){
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

    } 

    //DISPLAY
    
	public void logInUser() {
		driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("Test3");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
	}
	public void isEventCreated(){
		driver.findElement(By.cssSelector("li.dummy:nth-child(2) > a:nth-child(1)")).isDisplayed();

	}
	public void clickEvent(){
		driver.findElement(By.cssSelector("li.dummy:nth-child(2) > a:nth-child(1)")).click();
	}
	public void showInfos(){
		driver.findElement(By.id("guest1")).isDisplayed();
	}

	public void followGuestLink(){
		driver.get("http://localhost:8080/DhbwFestivalPlanner/event/guestView?id=2");
	}
	public void displayGuestView(){
		assertEquals("UPDATED SAMPLE", driver.findElement(By.id("titel")).getText());
	}
    
    //CREATE/EDIT

    
    public void createEvent(){
    	driver.findElement(By.id("createevent")).click();
    	driver.get(baseUrl + "/event/edit");

    }
    public void displayForm(){
    	assertEquals("Festival Planner: Party bearbeiten", driver.getTitle());
    }
    public void clickPen(){
    	driver.findElement(By.cssSelector("li.dummy:nth-child(2) > a:nth-child(1)")).click();
    	//driver.findElement(By.cssSelector("i.fa.fa-pencil")).click(); //hat derzeit probleme
    	driver.get(baseUrl + "/event/edit?id=1");
    }
    public void edit(){
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("updated sample");
        submitCreate();
    }
    public void updatePage(){
    	driver.get(baseUrl);
    	driver.get(baseUrl);
    	driver.findElement(By.linkText("updated sample"));
    }
    public void fillOutEverything(){
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Silvesterparty an der DH");
        driver.findElement(By.id("date")).click();
        driver.findElement(By.id("date")).clear();
        driver.findElement(By.id("date")).sendKeys("31.12.2014");
        driver.findElement(By.id("time")).clear();
        driver.findElement(By.id("time")).sendKeys("22:00");
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("DHBW Karlsruhe");
        driver.findElement(By.id("address")).clear();
        driver.findElement(By.id("address")).sendKeys("Erzbergerstrasse 121");
        driver.findElement(By.id("plz")).clear();
        driver.findElement(By.id("plz")).sendKeys("76133");
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys("Karlsruhe");
        driver.findElement(By.id("beschreibung")).clear();
        driver.findElement(By.id("beschreibung")).sendKeys("Silvester!!!");
    }
    public void submitCreate(){
    	driver.findElement(By.xpath("//input[@value=\"Los geht's\"]")).click();
    	
    }
    public void addEvent(){
    	driver.get("http://localhost:8080/DhbwFestivalPlanner");
    	driver.findElement(By.xpath("(//a[contains(text(),'Silvesterparty an der DH')])"));
    }

    public void showHints(){
    	driver.findElement(By.id("editerror"));
    }

    //INVITE
    
	public void clickGuestPanel() {
		driver.findElement(By.id("teiln")).click();
		driver.get(baseUrl + "/guestList/show?id=1");
	}

	public void fillGuestForm() {
		driver.findElement(By.id("gastname")).clear();
		driver.findElement(By.id("gastname")).sendKeys("Best Friend");
		driver.findElement(By.id("emailgast")).clear();
		driver.findElement(By.id("emailgast")).sendKeys("best@friend.com");
		sendInvitation();
	}
	public void sendInvitation(){
		driver.findElement(By.id("senden")).click();
	}
	
	public void wrongMail(){
		driver.findElement(By.id("gastname")).clear();
		driver.findElement(By.id("gastname")).sendKeys("Best Friend");
		driver.findElement(By.id("emailgast")).clear();
		driver.findElement(By.id("emailgast")).sendKeys("bestfriend.com");
		sendInvitation();
	}

	public void checkInvitation() {
		assertTrue(driver.findElement(By.cssSelector("li.dummy")).isDisplayed());
		
		
	}

	public void removeGuest() {
		driver.findElement(By.cssSelector("button")).click();
	}

	public void checkUninvitation() {
		
		assertFalse(isElementPresent(By.cssSelector("li.dummy")));
		
	}
	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }

	public void alertName() {
		assertTrue(isAlertPresent());
		assertTrue(closeAlertAndGetItsText().equals("Bitte gib einen Namen ein!"));
		
	}

	public void alertMail() {
		assertTrue(isAlertPresent());
		assertTrue(closeAlertAndGetItsText().equals("Bitte gib eine g&uuml;ltige Emailadresse ein!"));
		
	}
}
