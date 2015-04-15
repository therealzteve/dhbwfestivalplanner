package cucumberTests;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs { 
	
	private SeleniumTest script;
	

	public void setUpWebDriver() throws Exception {

	   script = new SeleniumTest();

	   script.setUp();

	   script.goToHomePage();

	}
	
	//LOGIN
	
	/*@Given("^I am not logged in$")
	public void i_am_not_logged_in() throws Throwable {
		setUpWebDriver();
	    script.notLoggedIn();
	}

	@When("^I filled the required fields$")
	public void i_filled_the_required_fields() throws Throwable {
	    script.fillInCorrect();
	}

	@When("^I submit the formular$")
	public void submit_the_formular() throws Throwable {
	    script.submit();
	}

	@Then("^the system should validate the data and show me the main overview$")
	public void the_system_should_validate_the_data_and_show_me_the_main_overview() throws Throwable {
	    script.checkOverview();
	}

	@Given("^I did not fill in the correct data or did not fill all required fields$")
	public void i_did_not_fill_in_the_correct_data_or_did_not_fill_all_required_fields() throws Throwable {
		script.fillInWrong();
	}



	@Then("^the system should validate the data and show me an error message$")
	public void the_system_should_validate_the_data_and_show_me_an_error_message() throws Throwable {
	    script.checkLoginError();
	}*/
	
	//REGISTER
	
	/*@Given("^I am on the Homepage$")
	public void i_am_on_the_Homepage() throws Throwable {
	    setUpWebDriver();
	}

	@When("^I click Register$")
	public void i_click_Register() throws Throwable {
	    script.clickRegister();
	}

	@When("^I fill out the required Information$")
	public void i_fill_out_the_required_Information() throws Throwable {
	    script.fillOutRegister();

	}

	@When("^click submit$")
	public void click_submit() throws Throwable {
	    script.submitRegister();
	}

	@Then("^I return to the Login form$")
	public void i_return_to_the_Login_form() throws Throwable {
		script.returnToLoginRegister();
	    
	}

	@Then("^my Account is unlocked so I am able to Login now$")
	public void my_Account_is_unlocked_so_I_am_able_to_Login_now() throws Throwable {
		script.firstLoginRegister();
	  
	}*/
	/*@Given("^I am on the Homepage$")
	public void i_am_on_the_Homepage() throws Throwable {
	    setUpWebDriver();
	}*/


	
	//DISPLAY
	
	@Given("^that the User is logged in$")
	public void that_the_User_is_logged_in() throws Throwable {
		setUpWebDriver();
	    script.logInUser();
	}

	@Given("^the Event is created$")
	public void the_Event_is_created() throws Throwable {
	    script.isEventCreated();
	}

	@When("^the user clicks on the Events title$")
	public void the_user_clicks_on_the_Events_title() throws Throwable {
	    script.clickEvent();
	}

	@Then("^he should be displayed the Events Information$")
	public void he_should_be_displayed_the_Events_Information() throws Throwable {
	    script.showInfos();
	}

	@Given("^the Guest is not logged in$")
	public void the_Guest_is_not_logged_in() throws Throwable {
		setUpWebDriver();
		script.notLoggedIn();
	}

	@When("^he follows on the link$")
	public void he_follows_on_the_link() throws Throwable {
	    script.followGuestLink();
	}

	@Then("^he should be displayed the event page$")
	public void he_should_be_displayed_the_event_page() throws Throwable {
	    script.displayGuestView();
	}
	
	//CREATE/EDIT
	
	/*@Given("^I am logged in$")
	public void i_am_logged_in() throws Throwable {
		setUpWebDriver();
	    script.logInUser();
	}

	@When("^I create a new event$")
	public void i_create_a_new_event() throws Throwable {
	    script.createEvent();
	}

	@Then("^a form with the required data should be displayed$")
	public void a_form_with_the_required_data_should_be_displayed() throws Throwable {
	    script.displayForm();
	}

	@When("^I klick on the pen icon underneath an Event$")
	public void i_klick_on_the_pen_icon_underneath_an_Event() throws Throwable {
	    script.clickPen();
	}

	@Then("^a form with the event data should be displayed$")
	public void a_form_with_the_event_data_should_be_displayed() throws Throwable {
		script.displayForm();
	}

	@When("^I changed the data$")
	public void i_changed_the_data() throws Throwable {
	    script.edit();
	}

	@Then("^The event should be displayed again with the updated Information$")
	public void the_event_should_be_displayed_again_with_the_updated_Information() throws Throwable {
	    script.updatePage();
	}

	@Given("^I filled all required fields$")
	public void i_filled_all_required_fields() throws Throwable {
		script.createEvent();
	    script.fillOutEverything();
	}

	@When("^I submit the formular$")
	public void i_submit_the_formular() throws Throwable {
	    script.submit();
	}

	@Then("^the system should create the event$")
	public void the_system_should_create_the_event() throws Throwable {
	    script.addEvent();
	}

	@Then("^I should see the main menu of the event$")
	public void i_should_see_the_main_menu_of_the_event() throws Throwable {
	    script.checkOverview();
	}

	@Given("^I did not fill all required fields$")
	public void i_did_not_fill_all_required_fields() throws Throwable {
		script.createEvent();
	    script.submit();
	}

	@Then("^I should see the formular again$")
	public void i_should_see_the_formular_again() throws Throwable {
	    script.displayForm();
	}

	@Then("^I should get a hint which fields are not valid$")
	public void i_should_get_a_hint_which_fields_are_not_valid() throws Throwable {
	    script.showHints();
	}*/

	
	@After
    public void tearDown() throws Exception {

	       script.tearDown();

	    }}
