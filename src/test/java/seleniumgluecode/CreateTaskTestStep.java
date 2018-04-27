package seleniumgluecode;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyTasksPage;

import org.junit.AfterClass;
import org.junit.Assert;

public class CreateTaskTestStep {
	public static WebDriver driver;
	HomePage home;
	MyTasksPage myTask;
	boolean actualValue;
	String messageAtTop;

	private static final String LONG_TEXT = "RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR J";

	@Given("^User is already logged in$")
	public void user_is_already_logged_in() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Dev\\workspace\\todoapp\\Cucumber_Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://qa-test.avenuecode.com/");
		if (driver.findElements(By.partialLinkText("Welcome")).isEmpty()) {
			driver.findElement(By.linkText("Sign In")).click();
			LoginPage login = new LoginPage(driver);
			login.enterUserEmail("faraz_rage@hotmail.com");
			login.enterUserPassword("Test1234");
			login.clickLogin();
		}
	}

	@Given("^on home page$")
	public void on_home_page() throws Throwable {
		home = new HomePage(driver);
	}

	@When("^I view the My Tasks link in navigation bar$")
	public void i_view_the_My_Tasks_link_in_navigation_bar() throws Throwable {
		actualValue = home.isTaksLinkFromNavigationBarDisplayed();
	}

	@Then("^I should see the My Tasks link in the navigation bar$")
	public void i_should_see_the_My_Tasks_link_in_the_navigation_bar() throws Throwable {
		Assert.assertEquals(true, actualValue);
	}

	@Given("^User is on home page$")
	public void user_is_on_home_page() throws Throwable {
		home = new HomePage(driver);
	}

	@When("^I click on My Tasks link in navigation bar$")
	public void i_click_on_My_Tasks_link_in_navigation_bar() throws Throwable {
		myTask = home.clickTaksLinkFromNavigationBar();
	}

	@Then("^I should be redirected to My Tasks page$")
	public void i_should_be_redirected_to_My_Tasks_page() throws Throwable {
		String url = myTask.getDriver().getCurrentUrl().toString();
		Assert.assertTrue(myTask.getDriver().getCurrentUrl().equals(url));
	}

	@Given("^User is on My Tasks page$")
	public void user_is_on_My_Tasks_page() throws Throwable {
		myTask = new MyTasksPage(driver);
	}

	@When("^I check the message at the top part$")
	public void i_check_the_message_at_the_top_part() throws Throwable {
		messageAtTop = myTask.getMessageAtTop();
	}

	@Then("^I should see the message \"([^\"]*)\"$")
	public void i_should_see_the_message(String arg1) throws Throwable {
		Assert.assertEquals(arg1, messageAtTop);
	}

	@When("^I dont enter anything in the Task description field and click on Add task button$")
	public void i_dont_enter_anything_in_the_Task_description_field_and_click_on_Add_task_button() throws Throwable {
		myTask = new MyTasksPage(driver);
		myTask.clickAddTaskButton();
	}

	@Then("^I shouldnt see any empty task being created in the list\\.$")
	public void i_shouldnt_see_any_empty_task_being_created_in_the_list() throws Throwable {
		Assert.assertEquals(false, myTask.isEmptyTaskVisible());
	}

	@When("^I enter a value \"([^\"]*)\" in the task description textbox which is less than (\\d+) characters\\.$")
	public void i_enter_a_value_in_the_task_description_textbox_which_is_less_than_characters(String arg1, int arg2)
			throws Throwable {
		myTask = new MyTasksPage(driver);
		myTask.enterTaskDescription(arg1);
		myTask.clickAddTaskButton();
	}

	@Then("^I shouldnt see the task \"([^\"]*)\" created in the list$")
	public void i_shouldnt_see_the_task_created_in_the_list(String arg1) throws Throwable {
		Assert.assertEquals(false, myTask.isTaskDisplayed(arg1));
	}

	@When("^I enter a value \"([^\"]*)\" in the task description textbox and hit the enter key\\.$")
	public void i_enter_a_value_in_the_task_description_textbox_and_hit_the_enter_key(String arg1) throws Throwable {
		myTask = new MyTasksPage(driver);
		myTask.enterTaskDescription(arg1);
		myTask.hitEnterKeyForTaskDescription();
	}

	@Then("^I should see the task \"([^\"]*)\" created in the list$")
	public void i_should_see_the_task_created_in_the_list(String arg1) throws Throwable {
		Assert.assertEquals(true, myTask.isTaskDisplayed(arg1));
	}

	@When("^I enter a value greater than 250 characters in the task description textbox and hit the enter key\\.$")
	public void i_enter_a_value_greater_than_characters_in_the_task_description_textbox_and_hit_the_enter_key()
			throws Throwable {
		myTask = new MyTasksPage(driver);
		myTask.enterTaskDescription(LONG_TEXT);
		myTask.hitEnterKeyForTaskDescription();

	}

	@Then("^I should NOT see the task created as the description is greater than 250 characters$")
	public void i_should_NOT_see_the_task_created_as_the_description_is_greater_than_characters()
			throws Throwable {
		Assert.assertEquals(false, myTask.isTaskDisplayed(LONG_TEXT));
		driver.close();
	}
}
