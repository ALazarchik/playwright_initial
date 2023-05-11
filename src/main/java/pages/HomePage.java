package pages;

import com.microsoft.playwright.Page;

import static services.EnvironmentReaderService.getProperty;

public class HomePage {

    private final Page homePage;

    public HomePage(Page page) {
        this.homePage = page;
    }

    public void openHomePage() {
        homePage.navigate(getProperty("url"));
    }
}
