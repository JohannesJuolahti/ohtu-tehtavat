package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        createUser(driver, "onnistunut", "luonti12");
        driver.get("http://localhost:4567");
        failedLogin(driver);
        driver.get("http://localhost:4567");
        createUserAndLogout(driver);
        driver.quit();

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void createUser(WebDriver driver, String username, String password) {
        // Uuden käyttäjätunnuksen luominen
        sleep(2);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        sleep(2);
        element.submit();
        sleep(3);
    }

    private static void failedLogin(WebDriver driver) {
        // Epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        sleep(2);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("onnistunut");
        element = driver.findElement(By.name("password"));
        element.sendKeys("luonti11");
        element = driver.findElement(By.name("login"));
        sleep(2);
        element.submit();
        sleep(3);
    }

    private static void createUserAndLogout(WebDriver driver) {
        // Uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        createUser(driver, "onnistunyt", "luonti13");
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
