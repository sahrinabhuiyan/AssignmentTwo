package automation.pages;

import automation.utils.TestBase;
import automation.utils.TestUtil;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TripAdvisorHomePage extends TestBase {
    By searchBox = By.xpath("//*[@id='lithium-root']/main/div[3]/div/div/div[2]/form/input[1]");
    By searchIcon = By.xpath("//*[@id='lithium-root']/main/div[3]/div/div/div[2]/form/button[3]");
    By firstOption = By.xpath("//div[@class='ui_column is-9-desktop is-8-mobile is-9-tablet content-block-column'][1]");
    By reviewButton = By.xpath("//button[@class='dfuux u j z _F ddFHE bVTsJ dQDUG bsyQH']");
    By writeAReviewOption = By.id("menu-item-0");
    By reviewTitle = By.id("ReviewTitle");

    TestUtil ut = new TestUtil();
    public static String url = "https://www.tripadvisor.in/";
    public static String placeToSearch = "Club Mahindra";

    @Test
   // i have the test here for now
    public void goToUrl() {
        ut.navigateTo(url);
        ut.sendText(searchBox,placeToSearch);
        ut.clickElement(searchIcon);
        ut.clickElement(firstOption);
        ut.clickElement(reviewButton); //doesn't show it clicked on the review button but test passsed
        ut.clickElement(writeAReviewOption);

    }
}
