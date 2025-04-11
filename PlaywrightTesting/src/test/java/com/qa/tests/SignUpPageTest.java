package com.qa.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import pages.CreatedAccountPage;
import pages.DeletedAccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

public class SignUpPageTest {

	private Playwright playwright;
	private Page page;
	private BrowserContext context;
	private Browser browser;

	@BeforeClass
	void launchBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
	}

	@AfterClass
	void closeBrowser() {
		playwright.close();
	}

	@BeforeMethod
	void createContextAndPage() {
		context = browser.newContext();
		page = context.newPage();
		page.navigate("https://www.automationexercise.com/");
	}

	@AfterMethod
	void closeContext() {
		context.close();
	}

	@Test (groups = { "regression", "user-story-QS-29" })
	public void registerAccount() {
		// Navigate to Home Page and verify logo visibility
		HomePage homePage = new HomePage(page);
		assertThat(homePage.getLogotipo()).isVisible();

		// Navigate to Login Page and verify "New User Signup" label visibility
		LoginPage loginPage = homePage.navigateToSignUpLoginPage();
		assertThat(loginPage.getUserSignUpLabel()).isVisible();

		// Sign up with user details and verify "Enter Account Information" label visibility
		SignUpPage signUpPage = loginPage.signUp("User1004", "user1004_pt@gmail.com");
		assertThat(signUpPage.getEnterAccountInformationLabel()).isVisible();

		// Enter account information
		signUpPage.enterAccountInformation("Mrs", "Anna Morison", "critical123", "10", "June", "2005");
		signUpPage.checkNewsletter();
		signUpPage.checkOffers();

		// Fill address information
		signUpPage.fillAddressInformation("Tom", "Riddle", "WB Studios", "4000 Warner Blvd., Burbank, CA 91522",
				"Greenhouse Hollywood Top", "United States", "California", "Los Angeles", "90002", "111-222-333");

		// Create account and verify account creation message
		CreatedAccountPage accountCreatedPage = signUpPage.clickCreateAccountButton();
		assertThat(accountCreatedPage.getAccountCreatedMessage()).isVisible();

		// Continue to Home Page and verify "Logged in as" label
		HomePage userHomePage = accountCreatedPage.clickContinueButton();
		assertThat(userHomePage.getLoggedInAsLabel()).containsText("Logged in as Anna Morison");

		// Delete account and verify account deletion message
		DeletedAccountPage deleteAccountPage = userHomePage.clickDeleteAccountButton();
		assertThat(deleteAccountPage.getAccountDeletedMessage()).isVisible();

		// Continue after account deletion
		deleteAccountPage.clickContinueButton();
	}

}
