package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Главная страница ЯндексСамокат
class  HomePage {
    private final WebDriver driver;
    //Локатор куки
    private final By cookie = By.id("rcc-confirm-button");
    //Локатор верхняя кнопка "Заказать"
    private final By orderButton = By.className("Button_Button__ra12g");
    //Локатор нижняя кнопка "Заказть"
    private final By bottomButtonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    By questionText;
    private By answerText;
    private By panelText = By.tagName("p");

    public HomePage(WebDriver driver,int panelNumber) {
        this.driver = driver;
        String id1 = String.format("accordion__heading-%d",panelNumber);
        String id2 = String.format("accordion__panel-%d",panelNumber);

        String xpath = String.format("//div[@id='%s']/parent::*/parent::*",id1);
        this.questionText = By.xpath(xpath);
        this.answerText = By.id(id2);
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажать на панель с вопросом
    public void clickPanel(int i) {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(questionText));
        driver.findElement(questionText).click();
    }

    //Проверка,открыта панель с вопросами или нет
    public Boolean isPanelOpened() {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(answerText));
        return driver.findElement(answerText).isDisplayed();
    }

    //Если панель с вопросами открыта,вернуть текст ответа
    public String GetPanelText() {
        if (isPanelOpened()) {
            return driver.findElement(answerText).findElement(panelText).getText();
        }
        return "";
    }


    //Нажать на кнопку закрытия куки
    public void closeCookies() {
        new WebDriverWait(driver,Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(cookie));
        driver.findElement(cookie).click();
    }

    //Нажать на верхнюю кнопку "Заказать"
    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Прокрутить до нижней кнопки "Заказать" и кликнуть
    public void clickBottomButtonOrder() {
        WebElement element = driver.findElement(bottomButtonOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",element);
        driver.findElement(bottomButtonOrder).click();
    }

}

class OrderPage {
    private final WebDriver driver;
    //Локатор поля "Имя"
    private final By userName = By.cssSelector("input[placeholder='* Имя']");
    //Локатор поля "Фамилия"
    private final By UserSecondName = By.cssSelector("input[placeholder='* Фамилия']");
    //Локатор поля "Адрес"
    private final By userAdress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    //Локатор поля "Станция метро"
    private final By metroStation = By.xpath("//input[@placeholder='* Станция метро']");
    //Индекс станции метро
    private final By metroStationIndex = By.cssSelector("[data-index]");
    //Локатор поля "Телефон"
    private final By userPhone = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    //Локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //Локатор поля "Когда привезти самокат"
    private final By whenToBringTheScooter = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    //Локатор поля "Срок аренды"
    private final By dropDownRentalPeriod = By.xpath(".//div[@class='Dropdown-control']");
    //Локатор выпадающего списка "Скор аренды"
    private final By dropDownSelect = By.xpath(".//div[@role='option' and text()]");
    //Локатор чек-бокса "Выпадающий список"
    private final By selectColor = By.xpath(".//input[@id]");
    //Локатор поля "Комментарий для курьера"
    private final By commitForTheСourier = By.cssSelector("input[placeholder='Комментарий для курьера']");
    //Локатор средней кнопки "Заказать"
    private final By orderButtonMiddle = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Локатор кнопки "Да" ,в окне "Хотите оформить заказ"
    private final By orderYesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Локатор окна "Заказ оформлен"
    private final By windowOrderPlaced = By.xpath(".//div[@class='Order_Modal__YZ-d3']");
    //Локатор кнопки "Посмотреть статус",в окне "Заказ оформлен"
    private final By buttonViewStatus = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Посмотреть статус']");
    //Локатор страницы просмотра заказа
    private final By orderViewPage = By.xpath(".//div[@class='App_App__15LM-']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //найти и нажать на кнопку "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Найти и заполнить поле "Имя"
    public void setUserName(String username) {
        driver.findElement(userName).sendKeys(username);
    }

    //Найти и заполнить поле "Фамилия"
    public void setUserSecondName(String usersecondname) {
        driver.findElement(UserSecondName).sendKeys(usersecondname);
    }

    //Найти и заполнить поле "Адрес"
    public void setUserAdress(String useradress) {
        driver.findElement(userAdress).sendKeys(useradress);
    }

    //Найти поле "Станция метро", кликнуть по полю,подождать пока появится выпадающий список,выбрать странцию по индексу
    public void setMetroStation(String metrostationid) {
        driver.findElement(metroStation).click();
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(metroStationIndex));
        driver.findElement(metroStationIndex).click();
    }

    //Найти и заполнить поле "Телефон"
    public void setUserPhone(String userphone) {
        driver.findElement(userPhone).sendKeys(userphone);
    }

    //Найти и заполнить поле "Когда привезти самокат"
    public void setWhenToBringTheScooter(String testdata) {
        driver.findElement(whenToBringTheScooter).sendKeys(testdata);
    }

    //Найти поле "Скор аренды",кликнуть по полю,подождать пока появится выпадающий список,выбрать срок аренды по тексту
    public void setDropDown() {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(dropDownRentalPeriod));
        driver.findElement(dropDownRentalPeriod).click();
        driver.findElement(dropDownSelect).click();
    }

    //Найти и нажать на чек-бокс ,в поле "Цвет самоката"
    public void setSelectColor(String selectcolor) {
        driver.findElement(selectColor).click();
        driver.findElement(selectColor).sendKeys(selectcolor);
        driver.findElement(selectColor).click();
    }

    //Найти и заполнить поле "Комментарий для курьера"
    public void setСommitForTheСourier(String commitcurier) {
        driver.findElement(commitForTheСourier).sendKeys(commitcurier);
    }

    //Найти и нажать на кнопку "Заказать"
    public void clickOrderButtonMiddle() {
        driver.findElement(orderButtonMiddle).click();
    }

    //Найти и нажать на кнопку "Да" в окне "Хотите оформить заказ"
    public void clickOrderYesButton() {
        driver.findElement(orderYesButton).click();
    }

    //Окно "Заказ оформлен" появилось,нажать на кнопку "Посмотреть статус"
    public void windowOrderPlacedIsEnabled() {
        driver.findElement(windowOrderPlaced).isDisplayed();
        driver.findElement(buttonViewStatus).click();
    }

    //Открывается страница просмотра заказа
    public void orderViewPage() {
        driver.findElement(orderViewPage).isDisplayed();
    }


    public void fillRenterInfoForm(String username,String secondName,String adress,String metrostationid,String userPhone,String testdata,String dropdownselect,String selectcolor,String commitcurier) {
        setUserName(username);
        setUserSecondName(secondName);
        setUserAdress(adress);
        setMetroStation(metrostationid);
        setUserPhone(userPhone);
        clickNextButton();
        setWhenToBringTheScooter(testdata);
        setSelectColor(selectcolor);
        setСommitForTheСourier(commitcurier);
        setDropDown();
        clickOrderButtonMiddle();
        clickOrderYesButton();
        windowOrderPlacedIsEnabled();
        orderViewPage();
    }
}

