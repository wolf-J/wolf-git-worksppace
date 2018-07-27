package com.aia.ui.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	// protected String[][] locatorMap;
	
	HashMap<String, Locator> locatorMap;
	String path;
	//protected Log log = new Log(this.getClass());
	protected Logger log=LogManager.getLogger(this.getClass().getName());
	
	
	protected BasePage(WebDriver driver) throws Exception {
		this.driver = driver;
		log.debug(this.getClass().getCanonicalName());
		System.out.println(System.getProperty("user.dir"));
		// locatorMap = ReadExcelUtil.getLocatorMap();
		path = System.getProperty("user.dir") + "/src/com/aia/coast/pageobject/" + this.getClass().getSimpleName()
				+ ".xml";
		log.info(path);
		locatorMap = xmlUtils.readXMLDocument(path, this.getClass().getCanonicalName());
		
	}

	protected void type(Locator locator, String values) throws Exception {
		WebElement e = findElement(driver, locator);
		log.info("type value is:  " + values);
		System.out.println(("type value is:  " + values));
		e.sendKeys(values);
		TimeUnit.MILLISECONDS.sleep(100);
	}
	
	protected void selectValue(Locator select, Locator locator, String value) throws Exception
	{
		TimeUnit.MILLISECONDS.sleep(500);
		click(select);
		TimeUnit.MILLISECONDS.sleep(100);
		List<WebElement> eles=findElements(locator);
		
		//performArrowDown();
		
		WebElement defaultItem=null;
		boolean notFound=true;
		int elementsSize=eles.size();
		System.out.println("Thare are "+elementsSize+" drop down items.");
		if(elementsSize>0)
		{
			defaultItem=eles.get(0);
			System.out.println("First item: "+defaultItem.getText());
		}else
		{
			throw new Exception("Select Box is empty!!!!");
		}
		
		eles=findElements(locator);
		
		for(int i=0;i<elementsSize;i++)
		{
			String itemTxt=eles.get(i).getText();
			System.out.println("Drop Down item: "+itemTxt);
			if(itemTxt.contains(value))
			{
				System.out.println("Found the items: "+itemTxt);
				eles.get(i).click();
				notFound=false;
				break;
			}
		}
		if(notFound)
		{
			System.out.println("Thare are not designaged item, use default one.");
			defaultItem.click();
		}
	}
	
	
	protected void performArrowRight(int times) throws Exception
	{
		Actions action=new Actions(driver);
		for(int i=0;i<times;i++)
		{
			TimeUnit.MILLISECONDS.sleep(50);
			action.sendKeys(Keys.ARROW_RIGHT).perform();
		}
	}
	
	protected void performArrowUp() throws Exception
    {
           Actions action=new Actions(driver);
           TimeUnit.MILLISECONDS.sleep(200);
           action.sendKeys(Keys.ARROW_UP).perform();
           
    }
	
	
	protected void performArrowUp(int times) throws Exception
    {
           Actions action=new Actions(driver);
           for(int i=0;i<times;i++)
           {
        	   TimeUnit.MILLISECONDS.sleep(200);
        	   action.sendKeys(Keys.ARROW_UP).perform();
           }
    }
	
	protected void performArrowDown() throws Exception
    {
           Actions action=new Actions(driver);
           TimeUnit.MILLISECONDS.sleep(100);
           action.sendKeys(Keys.ARROW_DOWN).perform();
    }
	
	protected void performArrowDown(int times) throws Exception
    {
           Actions action=new Actions(driver);
           TimeUnit.MILLISECONDS.sleep(100);
           for(int i=0;i<times;i++)
           {
        	   action.sendKeys(Keys.ARROW_DOWN).perform();
           }
    }
	
	
	
	protected void clearType(Locator locator, String values) throws Exception {
		WebElement e = findElement(driver, locator);
		log.info("type value is:  " + values);
		e.clear();
		TimeUnit.MILLISECONDS.sleep(100);
		e.sendKeys(values);
		TimeUnit.MILLISECONDS.sleep(200);
	}
	
	protected void clear(Locator locator) throws Exception {
		WebElement e = findElement(driver, locator);
		e.clear();
		TimeUnit.MILLISECONDS.sleep(200);
	}
	
	public void refresh() throws Exception
	{
		driver.navigate().refresh();
	}
	
	
	
	
	protected void clearSType(Locator locator, String values) throws Exception {
		WebElement e = findHideElement(locator);
		System.out.println("Type value is: "+values);
		log.info("type value is:  " + values);
		//e.clear();
		Actions action=new Actions(driver);
		//action.keyDown(Keys.valueOf(1234));
		//action.keyDown(e, Keys.valueOf(values));
		action.sendKeys(Keys.BACK_SPACE).perform();
		action.sendKeys(Keys.BACK_SPACE).perform();
		action.sendKeys(values).perform();
		
	}
	
	
	protected void clearSType(String values) throws Exception {
		
		System.out.println("Type value is: "+values);
		log.info("type value is:  " + values);
		//e.clear();
		Actions action=new Actions(driver);
		//action.keyDown(Keys.valueOf(1234));
		//action.keyDown(e, Keys.valueOf(values));
		action.sendKeys(Keys.BACK_SPACE).perform();
		TimeUnit.MILLISECONDS.sleep(100);
		action.sendKeys(Keys.BACK_SPACE).perform();
		TimeUnit.MILLISECONDS.sleep(100);
		action.sendKeys(values).perform();
		TimeUnit.MILLISECONDS.sleep(100);
	}
	
	protected void performTab() throws Exception
	{
		Actions action=new Actions(driver);
		TimeUnit.MILLISECONDS.sleep(200);
		action.sendKeys(Keys.TAB).perform();
	}
	
	
	protected void perfromTimesTab(int i) throws Exception
	{
		for(int ii=0;ii<i;ii++)
		{
			performTab();
			TimeUnit.MILLISECONDS.sleep(200);
		}
	}
	
	protected void performDelete(int i) throws Exception
	{
		Actions action=new Actions(driver);
		
		for(int ii=0;ii<i;ii++)
		{
			action.sendKeys(Keys.DELETE).perform();
		}
		
	}
	
	public void pageDown() throws Exception
	{
		Actions action=new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		TimeUnit.MILLISECONDS.sleep(300);
	}
	
	protected void doubleClick() throws Exception
	{
		Actions action=new Actions(driver);
		action.doubleClick().perform();
		TimeUnit.MILLISECONDS.sleep(300);
	}
	
	protected void performEnter() throws Exception
	{
		Actions action=new Actions(driver);
		TimeUnit.MILLISECONDS.sleep(200);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	protected void rawInput(String value) throws Exception
	{
		TimeUnit.MILLISECONDS.sleep(200);
		Actions action=new Actions(driver);
		action.sendKeys(value).perform();
	}
	
	protected void ESC() throws Exception
	{
		TimeUnit.MILLISECONDS.sleep(200);
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ESCAPE).perform();
	}


	protected void typeQuickJS(Locator locator, String values) throws Exception {
		WebElement e = findHideElement(locator);
		log.info("type value is:  " + values);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=\"" + values + "\"", e);
	}
	
	protected void fileUpload(Locator locator, String values) throws Exception{
		WebElement e = findHideElement(locator);
		e.clear();
		e.sendKeys(values);
	}
	
	
	protected void switchToFrame(Locator locator) throws IOException
	{
		WebElement e=findElement(driver,locator);
		log.info("Swith to Frame: "+ locator.getElement());
		System.out.println("Switch to Frame: "+locator.getElement());
		driver.switchTo().frame(e);
		System.out.println("Switch to Frame: "+locator.getElement()+" successfully.");
	}
	
	protected void switchBack()
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * @param locator
	 * @param values
	 * @throws Exception
	 */
	protected void typeQuick(Locator locator, String values) throws Exception {
		WebElement e = findElement(driver, locator);
		log.info("type value is:  " + values);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value=\"" + values + "\"", e);
	}

	/**
	 * @author Jasonzhu
	 * @param locator
	 * @param text
	 * @throws IOException 
	 */
	protected void setRichTextBox(Locator locator, String text) throws IOException {
		WebElement e = findElement(driver, locator);
		log.info("type value is:  " + text);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].innerHTML = \"" + text + "\"", e);
	}

	/**
	 * @param locator
	 * @param text
	 * @return
	 * @throws IOException 
	 */
	protected String getRichTextBox(Locator locator, String text) throws IOException {
		WebElement e = findElement(driver, locator);
		log.info("type value is:  " + text);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String result = (String) js.executeScript("arguments[0].getInnerHTML()", e);
		return result;
	}

	/**
	 * @param locator
	 * @throws IOException 
	 */
	protected void scrollToElement(Locator locator) throws IOException {
		WebElement e = findElement(driver, locator);
		log.info("scroll view element");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// roll down and keep the element to the center of browser
		js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", e);
	}

	protected void click(Locator locator) throws Exception {
		WebElement e = findElement(driver, locator);
		log.info("click button");
		System.out.println("Click: "+locator.getElement());
		e.click();
		TimeUnit.MILLISECONDS.sleep(100);
	}

	protected void select(Locator locator, String value) throws Exception {
		WebElement e = findElement(driver, locator);
		Select select = new Select(e);

		try {
			log.info("select by Value " + value);
			select.selectByValue(value);
		} catch (Exception notByValue) {
			log.info("select by VisibleText " + value);
			select.selectByVisibleText(value);
		}
	}

	protected void alertConfirm() throws Exception {
		Alert alert = driver.switchTo().alert();
		try {
			alert.accept();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}

	protected void alertDismiss() throws Exception {
		Alert alert = driver.switchTo().alert();
		try {
			alert.dismiss();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}

	protected String getAlertText() throws Exception {
		Alert alert = driver.switchTo().alert();
		try {
			return alert.getText();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}

	protected void clickAndHold(Locator locator) throws IOException {
		WebElement e = findElement(driver, locator);
		Actions actions = new Actions(driver);
		actions.clickAndHold(e).perform();
	}
	
	protected void moveToElement(Locator locator) throws IOException{
		WebElement e=findElement(driver,locator);
		Actions action=new Actions(driver);
		action.moveToElement(e).perform();;
	
	}

	protected String getAttributeVal(Locator locator, String attri) throws Exception{
		WebElement ele=getElement(driver,locator);
		String val=ele.getAttribute(attri);
		return val;
	}
	
	protected String getVisualAttributeVal(Locator locator, String attri) throws Exception{
		WebElement ele=findElement(driver,locator);
		String val=ele.getAttribute(attri);
		return val;
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(Locator locator) throws IOException {
		return getElement(this.getDriver(), locator);
	}

	/**
	 * get by parameter
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 * @throws IOException
	 */
	public WebElement getElement(WebDriver driver, Locator locator) throws IOException {
		locator = getLocator(locator.getElement());
		WebElement e;
		switch (locator.getBy()) {
		case xpath:
			log.debug("find element By xpath");
			e = driver.findElement(By.xpath(locator.getElement()));
			break;
		case id:
			log.debug("find element By id");
			e = driver.findElement(By.id(locator.getElement()));
			break;
		case name:
			log.debug("find element By name");
			e = driver.findElement(By.name(locator.getElement()));
			break;
		case cssSelector:
			log.debug("find element By cssSelector");
			e = driver.findElement(By.cssSelector(locator.getElement()));
			break;
		case className:
			log.debug("find element By className");
			e = driver.findElement(By.className(locator.getElement()));
			break;
		case tagName:
			log.debug("find element By tagName");
			e = driver.findElement(By.tagName(locator.getElement()));
			break;
		case linkText:
			log.debug("find element By linkText");
			e = driver.findElement(By.linkText(locator.getElement()));
			break;
		case partialLinkText:
			log.debug("find element By partialLinkText");
			e = driver.findElement(By.partialLinkText(locator.getElement()));
			break;
		default:
			e = driver.findElement(By.id(locator.getElement()));
		}
		return e;
	}

	
	public List<WebElement> getElements(WebDriver driver, Locator locator) throws IOException {
		List<WebElement> elements;
		switch (locator.getBy()) {
		case xpath:
			log.debug("find element By xpath");
			elements = driver.findElements(By.xpath(locator.getElement()));
			break;
		case id:
			log.debug("find element By id");
			elements = driver.findElements(By.id(locator.getElement()));
			break;
		case name:
			log.debug("find element By name");
			elements = driver.findElements(By.name(locator.getElement()));
			break;
		case cssSelector:
			log.debug("find element By cssSelector");
			elements = driver.findElements(By.cssSelector(locator.getElement()));
			break;
		case className:
			log.debug("find element By className");
			elements = driver.findElements(By.className(locator.getElement()));
			break;
		case tagName:
			log.debug("find element By tagName");
			elements = driver.findElements(By.tagName(locator.getElement()));
			break;
		case linkText:
			log.debug("find element By linkText");
			elements = driver.findElements(By.linkText(locator.getElement()));
			break;
		case partialLinkText:
			log.debug("find element By partialLinkText");
			elements = driver.findElements(By.partialLinkText(locator.getElement()));
			break;
		default:
			elements = driver.findElements(By.id(locator.getElement()));
		}
		return elements;
	}
	
	public boolean isElementPresent(WebDriver driver, Locator myLocator, int timeOut) throws IOException {
		final Locator locator = getLocator(myLocator.getElement());
		boolean isPresent = false;		
		WebDriverWait wait = new WebDriverWait(driver, locator.getWaitSec());
		
		isPresent = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				try {
					return findElement(d, locator);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).isDisplayed();
		return isPresent;
	}
	
	
	public boolean isElementPresent(Locator myLocator) throws IOException {
		final Locator locator = getLocator(myLocator.getElement());
		boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(driver, locator.getWaitSec());
		
		isPresent = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				try {
					return findElement(d, myLocator);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}).isDisplayed();
		return isPresent;
	}
	
	
	public boolean ElementPresent(Locator myLocator) throws IOException {
		final Locator locator = getLocator(myLocator.getElement());
		boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(driver, locator.getWaitSec());
		WebElement element;
		try{
			element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getElement())));
		}catch(org.openqa.selenium.TimeoutException e)
		{
			//isPresent=false;
			return isPresent;
		}
		isPresent=element.isDisplayed();
		return isPresent;
	}
	
	public boolean isElementPresent(WebDriver driver, Locator myLocator) throws IOException {
		final Locator locator = getLocator(myLocator.getElement());
		boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(driver, locator.getWaitSec());
		WebElement element;
		try{
			element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator.getElement())));
		}catch(org.openqa.selenium.TimeoutException e)
		{
			isPresent=false;
			return isPresent;
		}
		isPresent=element.isDisplayed();
		return isPresent;
	}
	
	/**
	 * This Method for check isPresent Locator
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 * @throws IOException
	 */
	public boolean isElementPresent(Locator locator, int timeOut) throws IOException {
		return isElementPresent(driver, locator, timeOut);
	}

	/**
	 * 
	 * @param driver
	 * @param locator
	 * @return
	 * @throws IOException 
	 */
	public WebElement findElement(WebDriver driver, final Locator locator) throws IOException {
		Locator localLocator=getLocator(locator.getElement());
		//int waitSec=getLocator(locator.getElement()).getWaitSec();
		System.out.println("Wait second in the config file: "+localLocator.getWaitSec());
		/*WebElement element = (new WebDriverWait(driver, waitSec))
				.until(new ExpectedCondition<WebElement>() {

					@Override
					public WebElement apply(WebDriver driver) {
						try {
							
							return getElement(driver, locator);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							log.error("can't find element " + locator.getElement());
							return null;
						}

					}

				});*/
		WebDriverWait wait=new WebDriverWait(driver,localLocator.getWaitSec());
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(localLocator.getElement())));
		
		return element;
		
	}

	public WebElement findElementByClickable(final Locator locator) throws IOException
	{
		Locator localLocator=getLocator(locator.getElement());
		WebElement element=(new WebDriverWait(driver,localLocator.getWaitSec())
		.until(ExpectedConditions.elementToBeClickable(By.xpath(localLocator.getElement()))));
		return element;
	}
	
	
	
	
	public List<WebElement> findElements(final Locator locator) throws IOException {
		Locator localLocator=getLocator(locator.getElement());
		//int waitSec=getLocator(locator.getElement()).getWaitSec();
		//System.out.println("Wait second in the config file: "+ locator.getElement()+ " : "+localLocator.getWaitSec()+" --"+localLocator.getElement());
		/*WebElement element = (new WebDriverWait(driver, waitSec))
				.until(new ExpectedCondition<WebElement>() {

					@Override
					public WebElement apply(WebDriver driver) {
						try {
							return getElement(driver, locator);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							log.error("can't find element " + locator.getElement());
							return null;
						}

					}

				});*/
		
		/*List<WebElement> elements=(new WebDriverWait(driver,localLocator.getWaitSec())).until(new ExpectedCondition<List<WebElement>>(){

			@Override
			public List<WebElement> apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					return getElements(driver, locator);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		});*/
		
	
		WebDriverWait wait=new WebDriverWait(driver,localLocator.getWaitSec());
		List<WebElement> elements=null;
		try{
			elements=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(localLocator.getElement())));
		}catch(Exception e)
		{
			//ignore
		}
		/*
		List<WebElement> elements=driver.findElements(By.xpath(localLocator.getElement()));*/
		return elements;
		
	}
	
	public List<WebElement> findHiddenElements(final Locator locator) throws IOException {
		Locator localLocator=getLocator(locator.getElement());
		//int waitSec=getLocator(locator.getElement()).getWaitSec();
		System.out.println("Wait second in the config file: "+ locator.getElement()+ " : "+localLocator.getWaitSec()+" --"+localLocator.getElement());
		/*WebElement element = (new WebDriverWait(driver, waitSec))
				.until(new ExpectedCondition<WebElement>() {

					@Override
					public WebElement apply(WebDriver driver) {
						try {
							return getElement(driver, locator);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							log.error("can't find element " + locator.getElement());
							return null;
						}

					}

				});*/
		
		/*List<WebElement> elements=(new WebDriverWait(driver,localLocator.getWaitSec())).until(new ExpectedCondition<List<WebElement>>(){

			@Override
			public List<WebElement> apply(WebDriver driver) {
				// TODO Auto-generated method stub
				try {
					return getElements(driver, locator);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		});*/
		
	
		WebDriverWait wait=new WebDriverWait(driver,localLocator.getWaitSec());
		List<WebElement> elements=null;
		try{
			elements=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(localLocator.getElement())));
		}catch(Exception e)
		{
			//ignore
		}
		/*
		List<WebElement> elements=driver.findElements(By.xpath(localLocator.getElement()));*/
		return elements;
		
	}
	

	public boolean elementNotDisplay(final Locator locator) throws IOException {
		
		Locator localLocator=getLocator(locator.getElement());
		
		boolean notDisplay=false;
		
		try
		{
			notDisplay=(new WebDriverWait(driver,localLocator.getWaitSec()).until(new ExpectedCondition<Boolean>(){

			@Override
			public Boolean apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return ExpectedConditions.invisibilityOfElementLocated(By.xpath(localLocator.getElement())).apply(driver);
			}
			
		}));
		}catch(Exception e)
		{
			notDisplay=false;
		}
		if(notDisplay)
		{
			System.out.println(locator.getElement()+" get disappear.");
		}
		return notDisplay;
		
	}
	

	
	
	public WebElement findHideElement(final Locator locator) throws IOException {
		Locator localLocator=getLocator(locator.getElement());
		System.out.println("Wait second in the config file: "+localLocator.getWaitSec());
		WebElement element = (new WebDriverWait(driver, localLocator.getWaitSec()))
				.until(new ExpectedCondition<WebElement>() {

					@Override
					public WebElement apply(WebDriver driver) {
						try {
							return getElement(driver, locator);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							log.error("can't find element " + locator.getElement());
							return null;
						}

					}

				});
		
		return element;
		
	}
	
	protected boolean alertShowup() throws Exception
	{	
		try{
			Alert alert = driver.switchTo().alert();
			return true;
		}catch(Exception notFindAlert){
			return false;
		}	
	} 
	
	/**
	 * @param locatorName
	 * @return
	 * @throws IOException
	 */
	public Locator getLocator(String locatorName) throws IOException {

		Locator locator;
		// for (int i = 0; i < locatorMap.length; i++) {
		// if (locatorMap[i][0].endsWith(locatorName)) {
		// return locator = new Locator(locatorMap[i][1]);
		// }
		// }
		// return locator = new Locator(locatorName);
		locator = locatorMap.get(locatorName);
		if (locator == null) {
			locator = new Locator(locatorName);
		}
		return locator;

	}

}
