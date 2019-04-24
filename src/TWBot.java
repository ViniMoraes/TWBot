import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import database.DB;
import util.WorldData;

public class TWBot {

    public static void main(String[] args) throws IOException{
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        
        DB db = new DB();
        WorldData wd = new WorldData("");
        wd.fillVillageTable();
        if (1+1 == 2)
        	return;

        WebDriver chrome = new ChromeDriver();
        login(chrome);
        managePopup(chrome);
        goToMain(chrome);

    }

    public static void goToMain(WebDriver driver){
        WebElement lmain = driver.findElement(By.partialLinkText("Edifício principal"));
        lmain.click();
        sleep();
    }

    private static void managePopup(WebDriver driver){
        sleep();
        if (!checkIfElementExits(driver, By.className("popup_box_header"), true, 10)) {
            System.out.println("Sem Popups!");
            return;
        }
        WebElement popup = driver.findElement(By.className("popup_box_header"));

        String popupName = popup.getText();

        if (popupName.contains("Bônus diário")){
            System.out.println("Popup bônus diário!");
            List<WebElement> chestsUnlockeds = driver.findElements(By.cssSelector("div.chest.unlocked"));
            System.out.println("BaÃºs encontrados: " + chestsUnlockeds.size());
            for (WebElement chest : chestsUnlockeds){
                WebElement parent = chest.findElement(By.xpath(".."));
                WebElement openButton = parent.findElement(By.cssSelector("a.btn.btn-default"));
                openButton.click();
                sleep();
            }
        }
        WebElement closeButton = waitForElement(driver, By.cssSelector("a.popup_box_close"));
        closeButton.click();
        sleep();
    }

    private static void login(WebDriver driver){
        driver.get("https://www.tribalwars.com.br/");
        WebElement loginBox = waitForElement(driver, By.id("user"));
        WebElement passBox = waitForElement(driver, By.id("password"));
        loginBox.sendKeys("bahea");
        passBox.sendKeys("bahea");
        passBox.submit();

        WebElement actualWorldButton = waitForElement(driver, By.className("world_button_active"));
        actualWorldButton.click();
    }

    private static void sleep(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    private static WebElement waitForElement(WebDriver driver, By by){
        checkIfElementExits(driver, by, true);
        return driver.findElement(by);
    }

    private static boolean checkIfElementExits(WebDriver driver, By by, boolean wait) {
        return checkIfElementExits(driver, by, true,0);
    }

    private static boolean checkIfElementExits(WebDriver driver, By by, boolean wait, int secondsToTimeout) {
        try {
            int elements = driver.findElements(by).size();
            int timeoutCounter = 0;
            while (elements <= 0) {
                if (!wait || timeoutCounter > secondsToTimeout * 10)
                    return false;
                Thread.sleep(100);
                if (secondsToTimeout != 0)
                    timeoutCounter++;
                elements = driver.findElements(by).size();
            }
            return true;
        } catch (InterruptedException e){
            throw  new RuntimeException(e);
        }
    }

}
