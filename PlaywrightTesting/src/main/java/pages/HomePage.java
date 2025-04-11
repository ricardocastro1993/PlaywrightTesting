package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

	private Page page;
	private Locator signUpLoginBtn;
	private Locator logotipo;
	private Locator loggedInAsLabel;
	private Locator deleteAccountBtn;
	private Locator modalWindow;

	public HomePage(Page page) {
		this.page = page;
		this.signUpLoginBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login"));
		this.logotipo = page.locator("img[alt='Website for automation practice']");
		this.loggedInAsLabel = page.locator("text=Logged in as");
		this.deleteAccountBtn = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Delete Account"));
		this.modalWindow = page.locator("//div[@class='fc-dialog-container']");

		if (modalWindow.isVisible()) {
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Consent")).click();
		}
	}

	public LoginPage navigateToSignUpLoginPage() {
		signUpLoginBtn.click();
		return new LoginPage(page);
	}

	public Locator getLogotipo() {
		return logotipo;
	}

	public Locator getLoggedInAsLabel() {
		return loggedInAsLabel;
	}

	public DeletedAccountPage clickDeleteAccountButton() {
		deleteAccountBtn.click();
		return new DeletedAccountPage(page);
	}

}
