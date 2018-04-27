package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateSubTasksPopupPage extends Page{
	
	@FindBy(xpath = "//div[@class='modal-dialog']")
	  private WebElement popWindow;
	
	@FindBy(id = "edit_task")
	  private WebElement taskDescriptionTextField;
	
	@FindBy(id = "new_sub_task")
	  private WebElement subTaskDescriptionTextField;
	
	@FindBy(id = "dueDate")
	  private WebElement dueDateTextField;
	
	@FindBy(id = "add-subtask")
	  private WebElement addSubTaskButton;
	
	@FindBy(xpath = ".//button[@ng-click='close()']")
	  private WebElement closeButton;	
	
	private static final String SUBTASK_WITH_DESCRIPTION_XPATH = "//a[contains(text(),'%s')]";
	
	private static final String INPUTCHECKBOX_SUBTASK_WITH_DESCRIPTION_XPATH = "//a[contains(text(),'%s')]/../preceding-sibling::td/input";

	
	public CreateSubTasksPopupPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isPopupDisplayed() {
		return popWindow.isDisplayed();
	}
	
	public void enterValueForTaskDescription(String value)
	{
		taskDescriptionTextField.clear();
		taskDescriptionTextField.sendKeys(value + Keys.TAB) ;
	}
	
	public String getValueForTaskDescription()
	{
		return taskDescriptionTextField.getText().trim();
	}
	
	public void enterValueForSubTaskDescription(String value)
	{
		subTaskDescriptionTextField.clear();
		subTaskDescriptionTextField.sendKeys(value + Keys.TAB) ;
	}
	
	public String getValueForSubTaskDescription()
	{
		return subTaskDescriptionTextField.getText().trim();
	}
	
	public void enterValueForDueDate(String value)
	{
		dueDateTextField.clear();
		dueDateTextField.sendKeys(value + Keys.TAB) ;
	}
	
	public String getValueForDueDate()
	{
		return dueDateTextField.getText().trim();
	}
	
	public boolean isSubTaskDisplayed(String task)
	{
		return !driver.findElements(By.xpath(String.format(SUBTASK_WITH_DESCRIPTION_XPATH, task))).isEmpty();
	}
	
	public void clickAddSubTaskButton()
	{
		addSubTaskButton.click();		
	}
	
	public void clickCloseButton()
	{
		closeButton.click();		
	}
	
	public boolean isSubTaskChecboxSelected(String task) throws Exception
	{
		if(!driver.findElements(By.xpath(String.format(INPUTCHECKBOX_SUBTASK_WITH_DESCRIPTION_XPATH, task))).isEmpty())
		{
			return driver.findElement(By.xpath(String.format(INPUTCHECKBOX_SUBTASK_WITH_DESCRIPTION_XPATH, task))).isSelected();
		}
		
		throw new Exception();
	}
	
	public void toggleSubTaskChecbox(String task, boolean check) throws Exception
	{
		if(!driver.findElements(By.xpath(String.format(INPUTCHECKBOX_SUBTASK_WITH_DESCRIPTION_XPATH, task))).isEmpty())
		{
			WebElement checkbox= driver.findElement(By.xpath(String.format(INPUTCHECKBOX_SUBTASK_WITH_DESCRIPTION_XPATH, task)));
			
		    if ((!checkbox.isSelected() && check) || (checkbox.isSelected() && !check))
		    {
		      checkbox.click();
		    }
		}
	}

}
