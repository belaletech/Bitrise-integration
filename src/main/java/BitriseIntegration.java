import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BitriseIntegration {

//    private final String userName = "belalahmad";
//    private final String accessKey = "4FulmpNtc5PUTEqK5FL9f7zN2UyL1tYu185gDaqylC2YLngKxH";
////String userName = System.getenv("LT_USERNAME") == null ?
////        "LT_USERNAME" : System.getenv("LT_USERNAME");
//    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
//            "LT_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

private final String userName = System.getenv("LAMBDATEST_USERNAME");
private final String accessKey = System.getenv("LAMBDATEST_ACCESS_KEY");
private final String gridUrl = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");
private final String appId = System.getenv("LAMBDATEST_APP_URL");

    private IOSDriver driver;
    private static String status = "passed";

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "[Build-plugin] BitriseIntegration1");
        ltOptions.put("name", "[Name] BitRiseIntegration-with plugin0");
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("autoAcceptAlerts", true);
//        ltOptions.put("autoGrantPermissions", true);
//        ltOptions.put("settings['respectSystemAlerts']", true);
//        ltOptions.put("appium:includeSafariInWebviews", true);
//        ltOptions.put("appium:settings[snapshotMaxDepth]", true);
        ltOptions.put("resignApp",true);
        ltOptions.put("privateCloud",false);
        ltOptions.put("w3c", true);
        ltOptions.put("app", appId);
//        ltOptions.put("app", "lt://APP10160501071738660716471452");
        capabilities.setCapability("lt:options", ltOptions);

        String hub = "https://" + userName + ":" + accessKey + "@" + gridUrl + "/wd/hub";
        driver = new IOSDriver(new URL(hub), capabilities);
    }

    @Test
    public void testHerokuAppFlow() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("Sm automating");


        WebElement el1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"btn_next\"]")));
        System.out.println("1. Enter eamil");
//        el1.sendKeys("vasyl+cmobile@slateteams.com");
        el1.click();
        Thread.sleep(1000);
//        driver.hideKeyboard();
//        hideKeyboard();

        WebElement el2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"Activate account\"]")));
        System.out.println("2.password");
//        el2.sendKeys("PSQYmRvDUi1");
        el2.click();
        Thread.sleep(5000);

//
//        WebElement el3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"Logging in\"]")));
//        System.out.println("3. login");
//        el3.click();
//        Thread.sleep(5000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
