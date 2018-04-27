package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{
	
	  @FindBy(id = "user_email")
	  private WebElement userEmailText;
	  
	  @FindBy(id = "user_password")
	  private WebElement userPasswordText;
	  
	  @FindBy(xpath = ".//input[@class='btn btn-primary']")
	  private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void enterUserEmail(String userEmail)
	{
		userEmailText.clear();
		userEmailText.sendKeys(userEmail);
	}
	
	public void enterUserPassword(String password)
	{
		userPasswordText.clear();
		userPasswordText.sendKeys(password);
	}
	
	public void clickLogin()
	{
		loginButton.click();
	}

}
