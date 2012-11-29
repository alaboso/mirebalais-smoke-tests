package org.openmrs.module.mirebalais.smoke;

import org.openmrs.module.mirebalais.smoke.pageobjects.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends AbstractPageObject {

	public HeaderPage(WebDriver driver) {
		super(driver);
	}

	public void logOut() {
		driver.findElement(By.linkText("Logout")).click();
	}
	
	public String getLocation() {
		return driver.findElement(By.tagName("strong")).getText();
	}

}
