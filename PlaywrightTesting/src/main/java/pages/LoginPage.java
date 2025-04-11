package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	private Page page;
	private Locator loginEmailAddress;
	private Locator passwordField;
	private Locator loginBtn;
	private Locator signUpName;
	private Locator signUpEmailAddress;
	private Locator signUpnBtn;
	private Locator newUserSignUpLabel;

	public LoginPage(Page page) {
		this.page = page;
		this.loginEmailAddress = page.locator("//input[@data-qa='login-email']");
		this.passwordField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
		this.loginBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
		this.signUpName = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name"));
		this.signUpEmailAddress = page.locator("//input[@data-qa='signup-email']");
		this.signUpnBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Signup"));
		this.newUserSignUpLabel = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("New User Signup!"));
	}

	public Locator getUserSignUpLabel() {
		return newUserSignUpLabel;
	}

	public void login(String email, String password) {
		loginEmailAddress.fill(email);
		passwordField.fill(password);
		loginBtn.click();
	}

	public SignUpPage signUp(String name, String email) {
		signUpName.fill(name);
		signUpEmailAddress.fill(email);
		signUpnBtn.click();
		return new SignUpPage(page);
	}
}