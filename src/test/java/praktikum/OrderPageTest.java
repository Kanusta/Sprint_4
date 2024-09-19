package praktikum;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;

    private final String userName;
    private final String secondName;
    private final String adress;
    private final String metrostationid;
    private final String userPhone;
    private final String testdata;
    private final String selectcolor;
    private final String dropdownselect;
    private final String commitcurier;

    public OrderPageTest(String userName,String secondName,String adress,String metrostationid,String userPhone,String testdata,String dropdownselect,String selectcolor,String commitcurier) {
        this.userName = userName;
        this.secondName = secondName;
        this.adress = adress;
        this.metrostationid = metrostationid;
        this.userPhone = userPhone;
        this.testdata = testdata;
        this.dropdownselect = dropdownselect;
        this.selectcolor = selectcolor;
        this.commitcurier = commitcurier;
    }
 @Before
public void startUpChrome() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}

    @Parameterized.Parameters
    public static Object[][] detCustomerData() {
        return new Object[][]{
                {"Иван","Иванович","Москва","0","12345641245","24.03.2024","сутки","чёрный жемчуг","Какой-то комментарий"},
                {"Петр","Иванович","Москва2","8","12345641247","25.03.2024","двое суток","серая безысходность","Какой-то комментарий 1"}
        };
    }
    //Тест с верхней кнопкой "Заказать"
    @Test
    public void openMainPage() throws Exception {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);
        objHomePage.closeCookies();
        objHomePage.clickOnOrderButton();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillRenterInfoForm(userName,secondName,adress,metrostationid,userPhone,testdata,dropdownselect,selectcolor,commitcurier);

    }
    //Тест с нижней кнопкой "Заказать"
    @Test
    public void openMainPageTest() throws Exception {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);
        objHomePage.closeCookies();
        objHomePage.clickBottomButtonOrder();
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.fillRenterInfoForm(userName,secondName,adress,metrostationid,userPhone,testdata,dropdownselect,selectcolor,commitcurier);

    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}







