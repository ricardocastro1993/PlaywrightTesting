package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class CreatedAccountPage {

	private Page page;
	private Locator accountCreatedMessage;
	private Locator continueButton;

	public CreatedAccountPage(Page page) {
		this.page = page;
		this.accountCreatedMessage = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("ACCOUNT CREATED!"));
		this.continueButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue"));
	}

	public Locator getAccountCreatedMessage() {
		return accountCreatedMessage;
	}

	public HomePage clickContinueButton() {
		continueButton.click();
		return new HomePage(page);
	}

}
