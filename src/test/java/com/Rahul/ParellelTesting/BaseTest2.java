package com.Rahul.ParellelTesting;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.offset.PointOption;

public class BaseTest2 {
		
		public AndroidDriver driver;
		public AppiumDriverLocalService service;
		
		@BeforeClass
		public void ConfigureAppium() throws MalformedURLException {
			 
//			service = new AppiumServiceBuilder().withAppiumJS(new File ("C:\\Users\\OnilVakharia\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//			.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
//			service.start();
			
			
			
			UiAutomator2Options options = new UiAutomator2Options();
			
			options.setCapability("udid", "24201FDF6008KX");
			
			options.setChromedriverExecutable("C:\\Users\\OnilVakharia\\Downloads\\chromedriver_win32\\chromedriver.exe");
			options.setApp("C:\\Users\\OnilVakharia\\eclipse-workspace\\ParellelTesting\\src\\test\\java\\resources\\General-Store.apk");
			
			
			
			driver = new AndroidDriver(new URL("http://127.0.0.1:4733"), options);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		public void longPressAction(WebElement peopleNames) {
			((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement)peopleNames).getId(), "duration", 2000));
		}
		
		public void scrollToEndAction(/*String searchText*/) {
			boolean canScrollMore;
			do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
			} while(canScrollMore);
			
			
			//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + searchText + "\"))"));

			
		}
		public double stringConverstion(String value) {
			String revisedText = value.substring(1);
			double convertedValue = Double.parseDouble(revisedText);
			return convertedValue;
			
		}
		public void swipeAction(WebElement element, String direction) {
			
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					"elementId", ((RemoteWebElement)element).getId(),
					
				    "direction", direction,
				    "percent", 0.75
				));
		}
		
		@AfterClass
		public void tearDown() {
			driver.quit();
			service.stop();
		}
	}

