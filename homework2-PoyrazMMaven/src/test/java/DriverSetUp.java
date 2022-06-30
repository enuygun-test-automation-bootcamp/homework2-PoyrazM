import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

//Burası benim Driver'ımı kurup , diğer classlara dağıttım Base classım
public class DriverSetUp {
    // WebDriver'ı static olarak tanımlayarak , diğer classlarda sürekli constructor(yapıcı) döndürmekten kurtuluyorum.
    static WebDriver driver;

    //WebDriverManager kütüphanesini kullanarak bilgisayarımda çalışmak istediğim driver'ın executable dosyası olmadan
    //Browser kurabiliyorum.
    WebDriverManager manager;

    //Burada url'mi bir String haline getirip temiz görünüm sağlamak istedim kodlarımda.
    String baseUrl = "https://demoqa.com/webtables";

    //JUnit'in Before özelliğini kullanarak , test öncesinde yapmak istediğim operasyonları tanımlayabiliyorum
    //Mesela ben teste başlamadan önce Driver'ımı kurmak istedim ve metotumun Before olacağını söyledim.
    @Before
    public void setUp(){

        //Burada chromeOptions objesi üreterek , testimde browser başladığında , chrome'dan herhangi bir
        //bildirim almak istemediğimi söylüyorum.
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");

        //Burada manager kütüphanesinden yararlanıp chromedriver kuruyorum ve ChromeDriver objesi oluşturup
        //yukarda tanımladığım özelliği kullanmak için objeme tanımlıyorum.
        manager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);

        //Gitmek istediğim url adresini açmak için bu komutu kullanırız.
        driver.get(baseUrl);

        //Browser'ı full ekran yaptım buradaki kod yardımıyla
        driver.manage().window().maximize();

        //Browser'ıma bekleme süresi tanımlayarak , geç açılması durumunda testimin patlamamasını istiyorum
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println("Browser is starting");

        //Selenium komutlarından gittiğim sayfadaki Url adresini alıyorum , doğru mu diye kontrol etmek için
        String actualUrl = driver.getCurrentUrl();

        //Burada beklenen url adresi , yani benim tanımladığım urlle , gittiğim sayfadan aldığım url'i karşılaştırıp
        //birim testi yapmış oluyorum. Eğer iki url birbiriyle aynı olmazsa başta yazdığım hatayı alırım.
        Assert.assertEquals("These URLs are not matching! ",baseUrl,actualUrl);
    }

    //Burada testim bittikten sonra yapılması gerek operasyonları tanımladım. JUnit yardımıyla
    @After
    public void tearDown(){
        //Dedim ki eğer driver boşta değilse , çalışır haldeyse test sonunda browserı kapat.
        if (driver != null){
            driver.quit();
        }
    }
}
