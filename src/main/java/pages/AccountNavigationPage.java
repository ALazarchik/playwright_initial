package pages;

import com.microsoft.playwright.Page;
import constants.AccountEntries;

public class AccountNavigationPage {

    private final Page accountNavigationPage;

    private static final String accountButton = "[data-lid=hdr_signin]";
    private static final String ACCOUNTS_LINKS_TEMPLATE = "//div[@class='account-menu']//a[text()='%s']";

    public AccountNavigationPage(Page page) {
        this.accountNavigationPage = page;
    }

    public void navigateTo(AccountEntries name) {
        accountNavigationPage.locator(accountButton).click();
        accountNavigationPage.locator(String.format(ACCOUNTS_LINKS_TEMPLATE, name.getDisplayName())).click();
    }
}
