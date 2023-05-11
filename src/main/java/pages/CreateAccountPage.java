package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import io.github.cdimascio.dotenv.Dotenv;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static services.EnvironmentReaderService.getProperty;

public class CreateAccountPage {

    Dotenv dotenv = Dotenv.load();

    private final Page createAccountPage;
    private static final String firstNameInput = "#firstName";
    private static final String lastNameInput = "#lastName";
    private static final String emailInput = "#email";
    private static final String passwordInput = "#fld-p1";
    private static final String confirmPasswordInput = "#reenterPassword";
    private static final String passwordErrorMessage = ".c-input-error-message";
    private static final String phoneNumberInput = "#phone";
    private static final String recoveryCheckbox = "#is-recovery-phone";
    private static final String createAccountButton = "[data-track='Create Account']";

    public CreateAccountPage(Page page) {
        this.createAccountPage = page;
    }

    public void fillCreateAccountForm() {

        createAccountPage.locator(firstNameInput).fill(dotenv.get("firstName"));  // fill first name input
        createAccountPage.locator(lastNameInput).fill(dotenv.get("lastName"));  // fill last name input
        createAccountPage.locator(emailInput).fill(dotenv.get("emailAddress"));  // fill email input
        createAccountPage.locator(passwordInput).fill(dotenv.get("password"));  // fill password input
        createAccountPage.locator(confirmPasswordInput).fill(dotenv.get("password"));  // fill confirm password input

        assertThat(createAccountPage.locator(passwordErrorMessage))
            .containsText("Your passwords match!", new LocatorAssertions.ContainsTextOptions().setTimeout(10000));  // assert that element contains text and set timeout for the assertion

        createAccountPage.locator(phoneNumberInput).fill(dotenv.get("phoneNumber"));  // fill phone number input
        createAccountPage.locator(recoveryCheckbox).check();  // check recovery checkbox

        assertThat(createAccountPage.locator(createAccountButton))       // assert element is enabled and set timeout for the assertion
            .isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(15000));
    }
}
