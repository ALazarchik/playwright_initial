import constants.AccountEntries;
import org.junit.jupiter.api.Test;

public class SignUpTest extends PlaywrightRunner {

    @Test
    public void signUpTest() {
        homePage.openHomePage();
        accountNavigationPage.navigateTo(AccountEntries.CREATE_ACCOUNT);
        createAccountPage.fillCreateAccountForm();
    }
}
