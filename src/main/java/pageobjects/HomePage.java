package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page{
	  @FindBy(xpath = ".//div[contains(@class, 'navbar-collapse')]//a[contains(@href, '/tasks')]")
	  private WebElement tasksNavBarLink;
	  
	  @FindBy(xpath = ".//a[@class='btn btn-lg btn-success']")
	  private WebElement tasksButton;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public MyTasksPage clickTaksLinkFromNavigationBar()
	{
		tasksNavBarLink.click();
		return new MyTasksPage(driver);		
	}
	
	public boolean isTaksLinkFromNavigationBarDisplayed()
	{
		return tasksNavBarLink.isDisplayed();		
	}
}
