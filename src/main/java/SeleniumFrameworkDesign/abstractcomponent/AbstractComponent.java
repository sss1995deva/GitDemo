package SeleniumFrameworkDesign.abstractcomponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	@FindBy(css="[routerlink*='dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='dashboard/myorders']")
	WebElement orderHeaders;
	
	public AbstractComponent(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}
	
	public OrderPage goToOrderPage(){
		orderHeaders.click();
		return new OrderPage(driver);
	}
	
	public CartPage goToCartPage(){
		cartHeader.click();
		return new CartPage(driver);
	}


	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));	
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForCartElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitElementToDisAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));	
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
}
