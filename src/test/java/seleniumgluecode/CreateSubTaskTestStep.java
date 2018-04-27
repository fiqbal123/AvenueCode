package seleniumgluecode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.deps.difflib.myers.MyersDiff;
import pageobjects.CreateSubTasksPopupPage;
import pageobjects.HomePage;
import pageobjects.MyTasksPage;

public class CreateSubTaskTestStep {
	HomePage home;
	MyTasksPage myTask = new MyTasksPage(CreateTaskTestStep.driver);

	WebDriver driver = CreateTaskTestStep.driver;
	CreateSubTasksPopupPage subTaskPage;

	private static final String LONG_TEXT = "RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR RTG TEST YTTUU ITT TPR J";

	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private static final DateFormat incorrectDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	Date currentDate = new Date();
	// convert date to calendar
	Calendar c = Calendar.getInstance();

	@Given("^a task is already created$")
	public void a_task_is_already_created() throws Throwable {
		myTask.enterTaskDescription("test task");
		myTask.hitEnterKeyForTaskDescription();
	}

	@When("^I view the column of subTasks for the task created$")
	public void i_view_the_column_of_subTasks_for_the_task_created() throws Throwable {

	}

	@Then("^I should see the \"([^\"]*)\" button$")
	public void i_should_see_the_button(String arg1) throws Throwable {
		Assert.assertTrue(myTask.getSubTaskButtonValueForTask("test task").contains(arg1));
	}

	@Then("^I should see \"([^\"]*)\" for the number of subtasks in the button$")
	public void i_should_see_for_the_number_of_subtasks_in_the_button(String arg1) throws Throwable {
		Assert.assertTrue(myTask.getSubTaskButtonValueForTask("test task").contains(arg1));
	}

	@When("^I click on the Manage Subtasks button$")
	public void i_click_on_the_Manage_Subtasks_button() throws Throwable {
		myTask = new MyTasksPage(driver);
		subTaskPage = myTask.clickSubTaskButtonForTask("test task");
	}

	@Then("^I should see pop window$")
	public void i_should_see_pop_window() throws Throwable {
		Assert.assertTrue(subTaskPage.isPopupDisplayed());
	}

	@Given("^user is on subtask popup page$")
	public void user_is_on_subtask_popup_page() throws Throwable {
		subTaskPage = new CreateSubTasksPopupPage(driver);
	}

	@When("^I enter \"([^\"]*)\" in task description textbox$")
	public void i_enter_in_task_description_textbox(String arg1) throws Throwable {
		subTaskPage.enterValueForTaskDescription(arg1);
	}

	@Then("^I should not see any changes to the text of task \"([^\"]*)\"$")
	public void i_should_not_see_any_changes_to_the_text_of_task(String arg1) throws Throwable {
		Assert.assertEquals(arg1, subTaskPage.getValueForTaskDescription());
	}

	@When("^I enter \"([^\"]*)\" in Sub Task description field$")
	public void i_enter_in_Sub_Task_description_field(String arg1) throws Throwable {
		subTaskPage.enterValueForSubTaskDescription(arg1);
		subTaskPage.clickAddSubTaskButton();
	}

	@Then("^I should see the subtask of \"([^\"]*)\" successfully created$")
	public void i_should_see_the_subtask_of_successfully_created(String arg1) throws Throwable {
		Assert.assertEquals(true, subTaskPage.isSubTaskDisplayed(arg1));
	}

	@When("^I enter greater than the allowed limit in Sub Task description field$")
	public void i_enter_greater_than_the_allowed_limit_in_Sub_Task_description_field() throws Throwable {
		subTaskPage.enterValueForSubTaskDescription(LONG_TEXT);
		subTaskPage.clickAddSubTaskButton();
	}

	@Then("^I should NOT see the subtask created$")
	public void i_should_NOT_see_the_subtask_created() throws Throwable {
		Assert.assertEquals(false, subTaskPage.isSubTaskDisplayed(LONG_TEXT));
	}

	@When("^I enter correct date format in due Date field and create a subtask$")
	public void i_enter_correct_date_format_in_due_Date_field_and_create_a_subtask() throws Throwable {

		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, 1);
		subTaskPage.enterValueForSubTaskDescription("testDate");
		subTaskPage.enterValueForDueDate(dateFormat.format(c.getTime()));
		subTaskPage.clickAddSubTaskButton();

	}

	@Then("^I should see the subtask created successfully$")
	public void i_should_see_the_subtask_created_successfully() throws Throwable {
		Assert.assertEquals(true, subTaskPage.isSubTaskDisplayed("testDate"));
	}

	@When("^I enter incorrect date format in due Date field and create a subtask$")
	public void i_enter_incorrect_date_format_in_due_Date_field_and_create_a_subtask() throws Throwable {
		c.setTime(currentDate);
		c.add(Calendar.DAY_OF_MONTH, 1);

		subTaskPage.enterValueForSubTaskDescription("testIncorrectDate");
		subTaskPage.enterValueForDueDate(incorrectDateFormat.format(c.getTime()));
		subTaskPage.clickAddSubTaskButton();
	}

	@Then("^I should Not see the subtask created successfully$")
	public void i_should_Not_see_the_subtask_created_successfully() throws Throwable {
		Assert.assertEquals(false, subTaskPage.isSubTaskDisplayed("testIncorrectDate"));
	}

	@When("^I create a subtask without entering any value for task description field$")
	public void i_create_a_subtask_without_entering_any_value_for_task_description_field() throws Throwable {
		subTaskPage.clickAddSubTaskButton();
	}

	@When("^I create a subtask without entering any value for Due date field$")
	public void i_create_a_subtask_without_entering_any_value_for_Due_date_field() throws Throwable {
		subTaskPage.enterValueForDueDate("");
		subTaskPage.clickAddSubTaskButton();
	}

	@Then("^I should Not see the subtask created successfully for task empty$")
	public void i_should_Not_see_the_subtask_created_successfully_for_task_empty() throws Throwable {
		Assert.assertEquals(false, subTaskPage.isSubTaskDisplayed("empty"));
	}

	@When("^I create a subtask \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_create_a_subtask_and(String arg1, String arg2) throws Throwable {
		subTaskPage.enterValueForSubTaskDescription(arg1);
		subTaskPage.clickAddSubTaskButton();
		subTaskPage.enterValueForSubTaskDescription(arg2);
		subTaskPage.clickAddSubTaskButton();

	}

	@When("^click on the Done column for subtasks of \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_on_the_Done_column_for_subtasks_of_and(String arg1, String arg2) throws Throwable {
		subTaskPage.toggleSubTaskChecbox(arg1, true);
		subTaskPage.toggleSubTaskChecbox(arg2, true);
	}

	@When("^close the popup window$")
	public void close_the_popup_window() throws Throwable {
		subTaskPage.clickCloseButton();
	}

	@When("^open the popup window again for task of \"([^\"]*)\"$")
	public void open_the_popup_window_again_for_task_of(String arg1) throws Throwable {
		myTask = new MyTasksPage(driver);
		myTask.clickSubTaskButtonForTask(arg1);
	}

	@Then("^I should still see both the subtasks done checkbox as selected$")
	public void i_should_still_see_both_the_subtasks_done_checkbox_as_selected() throws Throwable {
		Assert.assertEquals(true, subTaskPage.isSubTaskChecboxSelected("Done1"));
		Assert.assertEquals(true, subTaskPage.isSubTaskChecboxSelected("Done2"));
	}

	@When("^I create a subtask \"([^\"]*)\"$")
	public void i_create_a_subtask(String arg1) throws Throwable {
		subTaskPage.enterValueForSubTaskDescription(arg1);
		subTaskPage.clickAddSubTaskButton();
	}

	@When("^click on the Done column for subtasks of \"([^\"]*)\"$")
	public void click_on_the_Done_column_for_subtasks_of(String arg1) throws Throwable {		
		subTaskPage.toggleSubTaskChecbox(arg1, true);
	}

	@When("^disable the checkbox for the subtasks of \"([^\"]*)\"$")
	public void disable_the_checkbox_for_the_subtasks_of(String arg1) throws Throwable {
		subTaskPage.toggleSubTaskChecbox(arg1, false);
	}

	@Then("^I should see the checkbox for the subtasks of \"([^\"]*)\"$")
	public void i_should_see_the_checkbox_for_the_subtasks_of(String arg1) throws Throwable {
		Assert.assertEquals(false, subTaskPage.isSubTaskChecboxSelected(arg1));
	}

}
