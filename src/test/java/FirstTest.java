import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

public class FirstTest extends PlaywrightRunner {

    @Test
    public void firstTest() {
        page.navigate(getProperty("url"));
//            page.locator("//a[@data-lid='hdr_dotd']").click();  // search element by xpath
//            page.locator("[data-lid=hdr_dotd]").click();  // search element by css
//            page.locator("a:has-text(\"Deal of the Day\")").click();  // search element by text
        page.locator(".bottom-nav-left a", new Page.LocatorOptions().setHasText("Deal of the Day")).click();

        Locator menuItems = page.locator(".bottom-nav-left a");
//            System.out.println(menuItems.allTextContents());

        for (int i = 0; i < menuItems.count(); i++) {
            System.out.println(menuItems.nth(i).textContent());
        }
    }
}
