package seleniumlearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import SeleniumFrameworkDesign.testcomponent.BaseTest;
import SeleniumFrameworkDesign.testcomponent.RetryTest;

public class ErrorValidationTest extends BaseTest {

	@Test(retryAnalyzer = RetryTest.class)
	public void LoginErrorValidation() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("sss7587906235@gmail.com", "Selenium@2025");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test
	public void ProductErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";

		ProductCatalogue productCatalogue = landingPage.loginApplication("seleniumtesting2024@gmail.com","Selenium@2024");

		List<WebElement> products = productCatalogue.getProdcutList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

	}

}
