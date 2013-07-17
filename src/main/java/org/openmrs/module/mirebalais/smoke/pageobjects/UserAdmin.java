/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.mirebalais.smoke.pageobjects;

import java.sql.SQLException;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.openmrs.module.mirebalais.smoke.helper.PatientDatabaseHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class UserAdmin extends AbstractPageObject {
	
	private static final String CLINICAL_ROLE = "clinical";
	
	private static final String DATA_ARCHIVES_ROLE = "dataArchives";
	
	private static final String RADIOLOGY_ROLE = "radiology";
	
	private static final String SYS_ADMIN_ROLE = "sysAdmin";
    private final String CREOLE = "ht";

    private SysAdminPage adminPage;
	
	private String[] PROVIDER_TYPES = { "Teknisyen Laboratwa", "Enfimyè (RN)", "Administratè Jeneral",
	        "Teknisyen Radyoloji", "Famasis", "Anestezist" };

    public UserAdmin(WebDriver driver) {
		super(driver);
		adminPage = new SysAdminPage(driver);
	}
	
	public void createClinicalAccount(String firstName, String lastName, String username, String password) throws Exception {
		createA(CLINICAL_ROLE, firstName, lastName, username, password, CREOLE);
	}
	
	public void createRadiologyAccount(String firstName, String lastName, String username, String password) throws Exception {
		createA(RADIOLOGY_ROLE, firstName, lastName, username, password, CREOLE);
	}
	
	public void createDataArchivesAccount(String firstName, String lastName, String username, String password) throws Exception {
		createA(DATA_ARCHIVES_ROLE, firstName, lastName, username, password, CREOLE);
	}
	
	public void createSysAdminAccount(String firstName, String lastName, String username, String password, String language) throws Exception {
		createA(SYS_ADMIN_ROLE, firstName, lastName, username, password, language);
	}
	
	private void createA(String role, String firstName, String lastName, String username, String password, String language) throws Exception {
		createAccount(firstName, lastName, username, password);
		getRightRole(role).click();
		chooseProviderType();
		chooseLanguage(language);
		clickOnSave();
        PatientDatabaseHandler.addUserForDelete(username);
	}
	
	private void chooseProviderType() {
		Select select = new Select(driver.findElement(By.name("providerRole")));
		select.selectByVisibleText(drawProviderType());
	}
	
	private String drawProviderType() {
		return PROVIDER_TYPES[(int) (Math.random() * PROVIDER_TYPES.length)];
	}
	
	private void chooseLanguage(String language) {
		Select select = new Select(driver.findElement(By.name("defaultLocale")));
        select.selectByValue(language);
    }
	
	private void createAccount(String firstName, String lastName, String username, String password) {
		fillBasicInfo(firstName, lastName);
		fillUserAccountDetails(username, password);
	}
	
	private void fillBasicInfo(String firstName, String lastName) {
		adminPage.openManageAccounts();
		driver.findElement(By.id("create-account-button")).click();
		driver.findElement(By.name("givenName")).sendKeys(firstName);
		driver.findElement(By.name("familyName")).sendKeys(lastName);
	}
	
	private void fillUserAccountDetails(String username, String password) {
		driver.findElement(By.id("createUserAccountButton")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirmPassword")).sendKeys(password);
		
		WebElement select = driver.findElement(By.name("privilegeLevel"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if ("Konplè".equals(option.getText()))
				option.click();
		}
	}
	
	private void clickOnSave() {
		driver.findElement(By.id("save-button")).click();
	}

    private WebElement getRightRole(String role) {
		List<WebElement> options = driver.findElements(By.name("capabilities"));
		for (WebElement element : options) {
			if (element.getAttribute("value").contains(role)) {
				return element;
			}
		}
		throw new ElementNotFoundException(role, role, role);
	}
	
}
