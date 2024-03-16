package seleniumlearning;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.OrderPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import SeleniumFrameworkDesign.testcomponent.BaseTest;

//Commanad for running maven project from commandlin : mvn test -PRegression
//Command for running maven project from commaandline by global properties : mvn test -PErrorValidation -Dbrowser=edge
public class SubmitOrderTest extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(groups = { "ErrorValidation" }, dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// List<WebElement> products = productCatalogue.getProdcutList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistroyTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrdertDisplay(input.get("productName")));

	}

	// Using Json File for data
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				"F:\\PT_TOOL\\WorkPlace\\SeleniumFrameworkDesign\\src\\main\\java\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	/*
	 * How to use HashMap as data provider
	 * 
	 * @DataProvider public Object[][] getData() { HashMap<String,String> map = new
	 * HashMap<String,String>(); map.put("email","seleniumtesting2024@gmail.com");
	 * map.put("password","Selenium@2024"); map.put("productName","ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>();
	 * map1.put("email","sss7587906235@gmail.com");
	 * map1.put("password","Selenium@2024");
	 * map1.put("productName","ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map},{map1}}; }
	 * 
	 * How to use mulitdimension array
	 * 
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"seleniumtesting2024@gmail.com","Selenium@2024","ADIDAS ORIGINAL"},{
	 * "sss7587906235@gmail.com","Selenium@2024" ,"ZARA COAT 3"}};
	 */

}
