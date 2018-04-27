package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page{
	protected WebDriver driver;
	
	  /**
	   * @param driver WebDriver used to access page elements
	   */
	  public Page(WebDriver driver) 
	  {
	    this.driver = driver;
	    PageFactory.initElements(this.driver, this);
	  }
	  
	  public WebDriver getDriver()
	  {
	    return driver;
	  }

}
