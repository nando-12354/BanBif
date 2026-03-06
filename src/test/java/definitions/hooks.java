package definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import support.WordReportGenerator;

public class hooks {

    public static WebDriver driver;
    public static Scenario escenarioActual;
    public static WordReportGenerator word;

    @Before
    public void setUp(Scenario scenario) {
        ChromeOptions options = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        //options.addArguments("user-data-dir=C:/Users/Nando/AppData/Local/Google/Chrome/User Data/Profile 2");
        //options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        //options.addArguments("--disable-web-security");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        //options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        escenarioActual = scenario;

        String nombreEscenario = scenario.getName().replaceAll(" ", "_");
        word = new WordReportGenerator("target/reporte-word/" + nombreEscenario + ".docx");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (word != null) {
            try {
                word.guardar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (driver != null) {
            driver.manage().deleteAllCookies();
            //driver.close();
            driver.quit();
        }
    }
}