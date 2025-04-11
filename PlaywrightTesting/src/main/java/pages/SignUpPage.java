package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SignUpPage {

	private Page page;
	private Locator enterAccountInformationLabel;
	private Locator genderMrRadio;
	private Locator genderMrsRadio;
	private Locator nameInput;
	private Locator passwordInput;
	private Locator daySelect;
	private Locator monthSelect;
	private Locator yearSelect;
	private Locator newsletterCheckbox;
	private Locator offersCheckbox;
	private Locator firstNameInput;
	private Locator lastNameInput;
	private Locator companyInput;
	private Locator address1Input;
	private Locator address2Input;
	private Locator countrySelect;
	private Locator stateInput;
	private Locator cityInput;
	private Locator zipCodeInput;
	private Locator mobileNumberInput;
	private Locator createAccountButton;

	public SignUpPage(Page page) {
		this.page = page;
		this.enterAccountInformationLabel = page.locator("//b[normalize-space()='Enter Account Information']");
		this.genderMrRadio = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mr."));
		this.genderMrsRadio = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Mrs."));
		this.nameInput = page.locator("//input[@id='name']");
		this.passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password"));
		this.daySelect = page.locator("//select[@id='days']");
		this.monthSelect = page.locator("//select[@id='months']");
		this.yearSelect = page.locator("//select[@id='years']");
		this.newsletterCheckbox = page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Sign up for our newsletter!"));
		this.offersCheckbox = page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Receive special offers from our partners!"));
		this.firstNameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First name"));
		this.lastNameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last name"));
		this.companyInput = page.locator("//input[@id='company']");
		this.address1Input = page.getByLabel("Address").first();
		this.address2Input = page.getByLabel("Address 2");
		this.countrySelect = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Country"));
		this.stateInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("State"));
		this.cityInput = page.locator("//input[@id='city']");
		this.zipCodeInput = page.locator("//input[@id='zipcode']");
		this.mobileNumberInput = page.getByLabel("Mobile Number ");
		this.createAccountButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Create Account"));
	}

	public void enterAccountInformation(String title, String name, String password, String day, String month, String year) {
		selectTitle(title);
		nameInput.fill(name);
		passwordInput.fill(password);
		setDateOfBirth(day, month, year);
	}

	public void selectTitle(String gender) {
		if (gender.equalsIgnoreCase("Mr")) {
			genderMrRadio.check();
		} 
		else if (gender.equalsIgnoreCase("Mrs")) {
			genderMrsRadio.check();
		}
	}

	public void setDateOfBirth(String day, String month, String year) {
		daySelect.selectOption(day);
		monthSelect.selectOption(month);
		yearSelect.selectOption(year);
	}

	public void checkNewsletter() {
		newsletterCheckbox.check();
	}

	public void checkOffers() {
		offersCheckbox.check();
	}

	public void fillAddressInformation(String firstName, String lastName, String company, String address1, String address2,
			String country, String state, String city, String zipCode, String mobileNumber) {
		firstNameInput.fill(firstName);
		lastNameInput.fill(lastName);
		companyInput.fill(company);
		address1Input.fill(address1);
		address2Input.fill(address2);
		countrySelect.selectOption(country);
		stateInput.fill(state);
		cityInput.fill(city);
		zipCodeInput.fill(zipCode);
		mobileNumberInput.fill(mobileNumber);
	}

	public CreatedAccountPage clickCreateAccountButton() {
		createAccountButton.click();
		return new CreatedAccountPage(page);
	}
	
	public Locator getEnterAccountInformationLabel() {
		return enterAccountInformationLabel;
	}
}
