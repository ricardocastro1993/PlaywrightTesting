package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DeletedAccountPage {

	private Page page;
	private Locator accountDeletedMessage;
	private Locator continueButton;

	public DeletedAccountPage(Page page) {
		this.page = page;
		this.accountDeletedMessage = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("ACCOUNT DELETED!"));
		this.continueButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Continue"));
	}

	public Locator getAccountDeletedMessage() {
		return accountDeletedMessage;
	}

	public HomePage clickContinueButton() {
		continueButton.click();
		return new HomePage(page);
	}

}
