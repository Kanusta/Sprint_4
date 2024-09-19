package praktikum;




import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class HomePageTest {

    @Parameterized.Parameters
    public static Object[][] ReturnData(){
        String[] rules = {
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."

        };
        Object[][] data = new Object[8][];
        for (int i = 0 ;i < 8 ; i++){
            data[i]=new Object[]{i,rules[i],true};
        }
        return data;
    }
    WebDriver driver;
    int panelNumber;
    boolean result;
    String stringСheck;

    public HomePageTest(int panelNumber,String stringСheck , boolean result){
        this.panelNumber=panelNumber;
        this.result =result;
        this.stringСheck=stringСheck;
    }

    @Before
    public void startUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void test(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver,panelNumber);
        objHomePage.closeCookies();
        objHomePage.clickPanel(panelNumber);
        boolean result = stringСheck.equals(objHomePage.GetPanelText());
        Assert.assertEquals(this.result,result);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

