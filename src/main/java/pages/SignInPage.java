package pages;

import com.microsoft.playwright.Page;
import io.github.cdimascio.dotenv.Dotenv;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignInPage {
    private final Page signInPage;
    Dotenv dotenv = Dotenv.load();

    private static final String emailAddressInput = "#fld-e";
    private static final String passwordInput = "#fld-p1";
    private static final String signInButton = "[data-track=\"Sign In\"]";

    public SignInPage(Page page) {
        this.signInPage = page;
    }

    public void fillSignInForm() {
        signInPage.locator(emailAddressInput).fill(dotenv.get("emailAddress"));
        signInPage.locator(passwordInput).fill(dotenv.get("password"));
        assertThat(signInPage.locator(signInButton)).isEnabled();
    }
}
