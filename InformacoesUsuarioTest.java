package Tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class InformacoesUsuarioTest {
    private WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/usertqi/Drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        driver.get("http://www.juliodelima.com.br/taskit/");

        //clicar no link que possui o texto Sign in
        driver.findElement(By.linkText("Sign in")).click();

        //identificando o formuário de login
        WebElement formularioSignInBox = driver.findElement(By.id("signinbox"));

        //Digitar no campo com name login que está dentro do formulário id signinbox o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        //Digitar no campo com name password que está dentro do formulário id signinbox o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        //clicar no link com o texto "SIGN IN"
        driver.findElement(By.linkText("SIGN IN")).click();

            /*validar que dentro do elemento class "me" está o texto "Hi, Júlio"
            WebElement me = driver.findElement(By.className("me"));
            String textoNoElementoMe = me.getText();
            assertEquals("Hi, Julio", textoNoElementoMe);*/

        //clicar em um link que possui a class me
        driver.findElement(By.className("me")).click();

        //clicar em um link que possui "more data about"
        driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    private Object InterruptedException;

    //@Test
    public void AdicionarInformaçaoAdicionalDoUsuario() throws InterruptedException {

            //clicar no botão através do seu xpath //button[@data-target="addmoredata"]
            driver.findElement(By.xpath("//button[@data-target='addmoredata']")).click();

            //identificar a popup onde está o formulário "addmoredata"
            WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));

            //no combo de name type escolha a opção "Phone"
            WebElement campoType = popupAddMoreData.findElement(By.name("type"));
            new Select(campoType).selectByVisibleText("Phone");

            //no campo de name "contact" digitar "+5534999999999"
            popupAddMoreData.findElement(By.name("contact")).sendKeys("+5534999999999");

            //clicar no link "SAVE" que está no popup
            popupAddMoreData.findElement(By.linkText("SAVE")).click();

            //na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
            WebElement mensagemPopup = driver.findElement(By.id("toast-container"));
            String mensagem = mensagemPopup.getText();
            assertEquals("Your contact has been added!", mensagem);



            Thread.sleep(1000);

            if (driver.getPageSource().contains("I'm Feeling Lucky")) {
                System.out.println("Pass");
            } else {
                System.out.println("Fail");
            }

        }
        @Test
        public void removerUmContatoDeUmUsuario(){
            // clicar no elemento com xpath //*[@id="moredata"]/div[1]/ul/li[1]/a/i
            driver.findElement(By.xpath("//*[@id=\"moredata\"]/div[1]/ul/li[1]/a/i")).click();

            // confirmar a janela javascript
            driver.switchTo().alert().accept();

            //validar que a mensagem apresentada foi "Rest in peace, dear phone!"
            WebElement mensagemPopup = driver.findElement(By.id("toast-container"));
            String mensagem = mensagemPopup.getText();
            assertEquals("Rest in peace, dear phone!", mensagem);

            //Aguardar até 10 segundos para que a janela desapareça
            WebDriverWait aguardar = new WebDriverWait(driver,10);
            aguardar.until(ExpectedConditions.stalenessOf(mensagemPopup));

            //Clicar no link com texto logout
            driver.findElement(By.linkText("Logout")).click();

        }

        @After
    public void teardown(){
            driver.quit();
        }
    }

