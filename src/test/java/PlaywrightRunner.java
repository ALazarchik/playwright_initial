import annotations.PlaywrightPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.AccountNavigationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.SignInPage;
import services.EnvironmentReaderService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class PlaywrightRunner {
    protected Page page;
    protected BrowserContext browserContext;
    protected Browser browser;
    protected static Playwright playwright;
    @PlaywrightPage
    protected CreateAccountPage createAccountPage;
    @PlaywrightPage
    protected AccountNavigationPage accountNavigationPage;
    @PlaywrightPage
    protected SignInPage signInPage;

    @PlaywrightPage
    protected HomePage homePage;

    @BeforeAll
    public static void init() {
        playwright = Playwright.create();
    }

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser
            .newContext(new Browser.NewContextOptions().setPermissions(Arrays.asList("geolocation")));
        page = browserContext.newPage();

        initPage(this, page);
    }

    @AfterEach
    public void tearDown() {
        browserContext.close();
        browser.close();
    }

    private void initPage(Object object, Page page) {
        Class<?> clazz = object.getClass().getSuperclass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlaywrightPage.class)) {
                Class<?>[] type = {Page.class};
                try {
                    field.set(this, field.getType().getConstructor(type).newInstance(page));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    System.out.printf("Did not manage to call constructor for '%s' playwright page%n", field.getName());;
                }
            }
        }
    }

    protected String getProperty(String key) {
        return EnvironmentReaderService.getProperty(key);
    }
}
