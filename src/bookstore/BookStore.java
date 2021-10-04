package bookstore;

import static org.testng.Assert.expectThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class BookStore {
	WebDriver driver;
	String baseUrl;
	
	private String bookStoreMenuXPath = "//h5[text()='Book Store Application']/parent::div[@class='card-body']/parent::div";
	private String loginButtonXPath = "//button[@id='login' and @type='button']";
	private String newUserXPath = "//button[@id='newUser']";
	private String firstNameXPath = "//input[@type='text' and @id='firstname']";
	private String lastNameXPath = "//input[@type='text' and @id='lastname']";
	private String usernameXPath = "//input[@type='text' and @id='userName']";
	private String passwordXPath = "//input[@type='password' and @id='password']";
	private String captchaXPath = "//div[@id='g-recaptcha']";
	private String registerButtonXPath ="//button[@id='register']";
	private String backToLoginButtonXPath = "//button[@id='gotologin']";
	private String popupXPath = "//a[@id='close-fixedban']";
	private String bookListXPath = "//div[@class='ReactTable -striped -highlight']";
	private String loginListXPath = "//span[@class='text' and text()='Login']/parent::li";
	
	private String bookStoreListXPath = "//span[@class='text' and text()='Book Store']/parent::li";
	private String book1XPath = "//span[@id='see-book-Git Pocket Guide']/descendant::a";
	private String book2XPath = "//span[@id='see-book-Learning JavaScript Design Patterns']/descendant::a";
	private String book3XPath = "//span[@id='see-book-Designing Evolvable Web APIs with ASP.NET']/descendant::a";
	private String addXPath = "//button[@id='addNewRecordButton' and @type='button' and text()='Add To Your Collection']";
	private String backStoreXPath = "//button[@id='addNewRecordButton' and @type='button' and text()='Back To Book Store']";
	private String profileListXPath = "//span[@class='text' and text()='Profile']/parent::li";
	private String tableBooksXPath = "//div[@class='rt-tbody']";
	
	private String booksDeleteXpaths = "//span[@id='delete-record-undefined']";
	private String confirmDeleteXPath = "//div[@class='modal-body' and text()='Do you want to delete this book?']/parent::div";
	private String confirmDeleteButtonXPath = "//button[@id='closeSmallModal-ok' and @type='button']";
	private String deleteAllBooksXPath = "//div[@class='text-right button di']/button[@id='submit' and @type='button' and text()='Delete All Books']";
	private String confirmDeleteAllXPath = "//div[@class='modal-title h4' and text()='Delete All Books']/parent::div/parent::div";
	private String confirmDeleteAllButtonXPath = "//button[@id='closeSmallModal-ok' and @type='button']";
	
	private String logoutButtonXPath = "//button[@id='submit' and @type='button' and text()='Log out']";
	private String loginHeaderXPath = "//div[@class='main-header' and text()='Login']";
	
	private String deleteAccountButtonXPath = "//button[@id='submit' and @type='button' and text()='Delete Account']";
	private String confirmDeleteAccountXPath = "//div[@class='modal-content']";
	private String confirmDeleteAccountButtonXPath = "//button[@id='closeSmallModal-ok' and @type='button']";
	
	private String firstName = "Gustavo";
	private String lastName = "Salazar";
	private String username = "gusSal09";
	private String password = "GusSal-09@";
	
	WebDriverWait wait, waitAlert;
	JavascriptExecutor jse;
	
	@BeforeTest
	public void StartDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\AcademiaSept2021\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://demoqa.com/";
		driver.manage().window().maximize();
		wait = new WebDriverWait (driver, 10);
		waitAlert = new WebDriverWait (driver, 30);
		jse = (JavascriptExecutor)driver;
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void FinishDriver() {
		driver.close();
		driver.quit();
	}
	
	@Test(priority=5)
	public void RegisterTest() throws Exception {
		driver.get(baseUrl);
		
		WebElement popup = driver.findElement(By.xpath(popupXPath));
		if(popup.isDisplayed()) popup.click();
		
		WebElement bookStoreMenu = driver.findElement(By.xpath(bookStoreMenuXPath));
		bookStoreMenu.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginButtonXPath)));
		WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));
		loginButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newUserXPath)));
		WebElement newUserButton = driver.findElement(By.xpath(newUserXPath));
		newUserButton.click();
		
		takeSnapShot(driver, "Screenshots//Register_1.png") ;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstNameXPath)));
		WebElement firstNameInput = driver.findElement(By.xpath(firstNameXPath));
		firstNameInput.sendKeys(firstName);
		WebElement lastNameInput = driver.findElement(By.xpath(lastNameXPath));
		lastNameInput.sendKeys(lastName);
		WebElement usernameInput = driver.findElement(By.xpath(usernameXPath));
		usernameInput.sendKeys(username);
		WebElement passwordInput = driver.findElement(By.xpath(passwordXPath));
		passwordInput.sendKeys(password);
		WebElement captcha = driver.findElement(By.xpath(captchaXPath));
		captcha.click();
		takeSnapShot(driver, "Screenshots//Register_2.png") ;
		WebElement registerButton = driver.findElement(By.xpath(registerButtonXPath));
		registerButton.click();
		waitAlert.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		takeSnapShot(driver, "Screenshots//Register_3.png") ;
	}
	
	@Test(priority=10)
	public void LoginTest() throws Exception {
WebElement loginButton;
		
		WebElement bookStoreMenu = driver.findElement(By.xpath(bookStoreMenuXPath));
		bookStoreMenu.click();
		
		wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loginButtonXPath)),
				ExpectedConditions.visibilityOfElementLocated(By.xpath(loginListXPath))));
		if(!driver.findElements(By.xpath(loginButtonXPath)).isEmpty()) {
			loginButton = driver.findElement(By.xpath(loginButtonXPath));
			jse.executeScript("arguments[0].scrollIntoView();", loginButton);
			loginButton.click();
		}
		else if(!driver.findElements(By.xpath(loginListXPath)).isEmpty()) {
			loginButton = driver.findElement(By.xpath(loginListXPath));
			jse.executeScript("arguments[0].scrollIntoView();", loginButton);
			loginButton.click();
		}
		
		login();
	}
	
	@Test(priority=15)
	public void AddBooksTest() throws Exception {
		WebElement bookStoreList = driver.findElement(By.xpath(bookStoreListXPath));
		jse.executeScript("arguments[0].scrollIntoView();", bookStoreList);
		bookStoreList.click();
		
		String booksXpath[] = {book1XPath,book2XPath,book3XPath};
		
		for(int i=0; i<2; i++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableBooksXPath)));
			if(driver.findElement(By.xpath(tableBooksXPath)).isDisplayed()){
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(booksXpath[i])));
				WebElement book = driver.findElement(By.xpath(booksXpath[i]));
				jse.executeScript("arguments[0].scrollIntoView();", book);
				book.click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addXPath)));
				WebElement addButton = driver.findElement(By.xpath(addXPath));
				jse.executeScript("arguments[0].scrollIntoView();", addButton);
				addButton.click();
				
				waitAlert.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				takeSnapShot(driver, "Screenshots//Add_"+i+".png") ;
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(backStoreXPath)));
				WebElement backStoreButton = driver.findElement(By.xpath(backStoreXPath));
				backStoreButton.click();
			}
		}
	}
	
	@Test(priority=20)
	public void deleteBooksTest() throws Exception {
		WebElement popup = driver.findElement(By.xpath(popupXPath));
		if(popup.isDisplayed()) popup.click();
		
		WebElement profileButton = driver.findElement(By.xpath(profileListXPath));
		jse.executeScript("arguments[0].scrollIntoView();", profileButton);
		profileButton.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(booksDeleteXpaths)));
		ArrayList<WebElement> deleteBooksElements = (ArrayList<WebElement>) driver.findElements(By.xpath(booksDeleteXpaths));
		if(deleteBooksElements.get(0).isDisplayed()){
			if(popup.isDisplayed()) popup.click();
			WebElement book = deleteBooksElements.get(0);
			jse.executeScript("arguments[0].scrollIntoView();", book);
			book.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmDeleteXPath)));
			if(driver.findElement(By.xpath(confirmDeleteXPath)).isDisplayed()) {
				WebElement confirmDeleteButton = driver.findElement(By.xpath(confirmDeleteButtonXPath));
				confirmDeleteButton.click();
				wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				takeSnapShot(driver, "Screenshots//Delete_1.png") ;
			}
		}
		
		WebElement deleteAllBooksButton = driver.findElement(By.xpath(deleteAllBooksXPath));
		deleteAllBooksButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmDeleteAllXPath)));
		if(driver.findElement(By.xpath(confirmDeleteAllXPath)).isDisplayed()) {
			WebElement confirmDeleteAllButton = driver.findElement(By.xpath(confirmDeleteAllButtonXPath));
			jse.executeScript("arguments[0].scrollIntoView();", confirmDeleteAllButton);
			confirmDeleteAllButton.click();
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			takeSnapShot(driver, "Screenshots//Delete_2.png") ;
		}
	}
	
	@Test(priority=25)
	public void LogoutTest() throws Exception {
		WebElement popup = driver.findElement(By.xpath(popupXPath));
		if(popup.isDisplayed()) popup.click();
		
		WebElement logoutButton = driver.findElement(By.xpath(logoutButtonXPath));
		jse.executeScript("arguments[0].scrollIntoView();", logoutButton);
		logoutButton.click();
		takeSnapShot(driver, "Screenshots//Delete_Account_1.png") ;
		
		Assert.assertEquals(driver.findElement(By.xpath(loginHeaderXPath)).getText(), "Login");
	}
	
	@Test(priority=30)
	public void DeleteAccountTest() throws Exception {
		login();
		
		WebElement popup = driver.findElement(By.xpath(popupXPath));
		if(popup.isDisplayed()) popup.click();
		
		WebElement deleteAccountButton = driver.findElement(By.xpath(deleteAccountButtonXPath));
		jse.executeScript("arguments[0].scrollIntoView();", deleteAccountButton);
		deleteAccountButton.click();
		takeSnapShot(driver, "Screenshots//Logout.png") ;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmDeleteAccountXPath)));
		if(driver.findElement(By.xpath(confirmDeleteAccountXPath)).isDisplayed()) {
			WebElement confirmDeleteAccountButton = driver.findElement(By.xpath(confirmDeleteAccountButtonXPath));
			confirmDeleteAccountButton.click();
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			takeSnapShot(driver, "Screenshots//Delete_Account_2.png") ;
		}
		
		Assert.assertEquals(driver.findElement(By.xpath(loginHeaderXPath)).getText(), "Login");
	}
	
	private void login() throws Exception {
		WebElement popup = driver.findElement(By.xpath(popupXPath));
		if(popup.isDisplayed()) popup.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(usernameXPath)));
		WebElement usernameInput = driver.findElement(By.xpath(usernameXPath));
		usernameInput.sendKeys(username);
		WebElement passwordInput = driver.findElement(By.xpath(passwordXPath));
		passwordInput.sendKeys(password);
		takeSnapShot(driver, "Screenshots//Login_1.png") ;
		WebElement loginFormButton = driver.findElement(By.xpath(loginButtonXPath));
		jse.executeScript("arguments[0].scrollIntoView();", loginFormButton);
		loginFormButton.click();
		takeSnapShot(driver, "Screenshots//Login_2.png") ;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='userName-label' and text()='User Name : ']")));
		if(driver.findElement(By.xpath("//label[@id='userName-label' and text()='User Name : ']")).isDisplayed())
			Assert.assertEquals(username, driver.findElement(By.xpath("//label[@id='userName-value']")).getText());
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(SrcFile);
	        os = new FileOutputStream(DestFile);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
    }
}
