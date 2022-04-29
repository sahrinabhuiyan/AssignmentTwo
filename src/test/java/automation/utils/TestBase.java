package automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver = null;
    public static WebDriverWait waitVar = null;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
  //  public static WebEventListener eventListener;

    public TestBase(){
        try {
            prop = new Properties();
            //Reading from property file
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
                    + "/src/test/java/automation/config/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  This function is to invoke
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */

    @BeforeClass
    public void createDriver() throws MalformedURLException, InterruptedException {
        //Reading browser property from config file
        String browserName = prop.getProperty("browser");

        //This declares the webdriver we will be using
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    +"/src/test/java/automation/config/chromedriver");
            driver = new ChromeDriver();
        }
        else if(browserName.equals("FF")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
                    +"/src/test/java/automation/config/geckodriver");
            driver = new FirefoxDriver();
        }
        else if(browserName.equals("SF")){
            driver = new SafariDriver();
        }else{
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    +"/src/test/java/automation/config/chromedriver");
            driver = new ChromeDriver();
        }

        //Toggle between maximize and screen size
        //driver.manage().window().maximize();
        //Dimension d = new Dimension(375, 812); // iPhone
        //driver.manage().window().setSize(d);

        driver.manage().window().maximize();


//    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Passing url from property file
//        driver.get(prop.getProperty("url"));
        waitVar = new WebDriverWait(driver, 15);
    }

    /**
     * This function is to close driver instance
     */

    @AfterClass
    public void teardown() {
       driver.quit();
    }
}