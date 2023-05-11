import constants.AccountEntries;
import org.junit.jupiter.api.Test;

public class SignInTest extends PlaywrightRunner{
    @Test
    public void signInTest() {
        homePage.openHomePage();
        accountNavigationPage.navigateTo(AccountEntries.SIGN_IN);
        signInPage.fillSignInForm();
    }
}
