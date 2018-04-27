package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyTasksPage extends Page{
	
	  @FindBy(xpath = ".//div[@class='container']//h1")
	  private WebElement tasksNavBarLink;
	  
	  @FindBy(xpath = ".//a[@class='ng-scope ng-binding editable editable-click editable-empty']")
	  private WebElement emptyTask;	
	  
	  @FindBy(xpath = ".//span[@ng-click='addTask()']")
	  private WebElement addTaskButton;
	  
	  @FindBy(xpath = ".//input[@id='new_task']")
	  private WebElement taskDescriptionTextBox;
	  
	  @FindBy(xpath = ".//button[@ng-click='editModal(task)']")
	  private WebElement manageSubTasksButton;	  
	  
	  private static final String TASK_WITH_DESCRIPTION_XPATH = ".//a[@class='ng-scope ng-binding editable editable-click'][contains(text(),'%s')]";
	  
	  private static final String MANAGE_SUBTASK_BUTTON_FOR_TASK =".//a[@class='ng-scope ng-binding editable editable-click'][contains(text(),'%s')]/..//following-sibling::td//button[@ng-click='editModal(task)']";
	  

	public MyTasksPage(WebDriver driver) {
		super(driver);
	}
	
	public String getMessageAtTop()
	{
		return tasksNavBarLink.getText().trim();
	}
	
	public boolean isEmptyTaskVisible()
	{
		return emptyTask.isDisplayed();
	}
	
	public void clickAddTaskButton()
	{
		addTaskButton.click();
	}
	public boolean isTaskDisplayed(String task)
	{
		return !driver.findElements(By.xpath(String.format(TASK_WITH_DESCRIPTION_XPATH, task))).isEmpty();
	}
	
	public void enterTaskDescription(String description)
	{
		taskDescriptionTextBox.clear();
		taskDescriptionTextBox.sendKeys(description);
	}
	
	public void hitEnterKeyForTaskDescription()
	{
		taskDescriptionTextBox.sendKeys(Keys.ENTER);
	}
	
	public String getSubTaskButtonValueForTask(String task) throws Exception
	{
		if(!driver.findElements(By.xpath(String.format(MANAGE_SUBTASK_BUTTON_FOR_TASK, task))).isEmpty())
		{
			return driver.findElements(By.xpath(String.format(MANAGE_SUBTASK_BUTTON_FOR_TASK, task))).get(0).getText().trim();
		}
		throw new Exception();
	}
	
	public CreateSubTasksPopupPage clickSubTaskButtonForTask(String task) throws Exception
	{
		if(!driver.findElements(By.xpath(String.format(MANAGE_SUBTASK_BUTTON_FOR_TASK, task))).isEmpty())
		{
			driver.findElement(By.xpath(String.format(MANAGE_SUBTASK_BUTTON_FOR_TASK, task))).click();
			return new CreateSubTasksPopupPage(driver);
		}
		throw new Exception();
	}
	

}
