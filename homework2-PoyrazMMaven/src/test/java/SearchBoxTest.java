import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//DriverSetUp classımın özelliklerini kullanabilmek için miras alırım.
public class SearchBoxTest extends DriverSetUp{

    //Buradaki locatorların ikisi de searchbox içindir , sadece birisi id , birisi xpath tanımlanmıştır.
    //Ben testimde xpathi kullandım ama id'de kullansam fark etmezdi çünkü ikisi de aynı elementin locatorudur.
    By searchBox = By.id("searchBox");
    By searchBoxXpath = By.xpath("//*[@type='text']");

    //Bu anatasyonu kullanarak metotumun test olduğunu tanımladım ve metotumu çalışabilir hale getirdim
    @Test
    public void searchBoxTest(){

        //Burada search box'un yerinde olup olmadığını ekrana yazdırmak istedim ve isDisplayed() komutuyla eğer search box orada ise
        // bana true cevabını verir
        System.out.println("Search Box is Displayed : "+driver.findElement(searchBoxXpath).isDisplayed());

        //Beklediğim ve mevcut sonucu karşılaştırmak için actual tanımlaması yaptım. "isDisplayed()" komutu true , false döndüren bir komut olduğu için
        //boolean olarak tanımlanması gerekir ama ben String olarak alıp , kullanmak istedim onu. String.valueOf(boolean) yardımıyla bunu sağlarım.
        String searchBoxActual = String.valueOf((boolean) driver.findElement(searchBoxXpath).isDisplayed());

        //Burada da beklenen sonucum true döndürmesi ve bunu actualla doğrulayıp karşılaştırmak için birim testi yazdım
        //Ve en baştaki SearchBox is not found mesajı ise , testimin patlaması durumunda bana döndüreceği mesajdır.
        Assert.assertEquals("SearchBox is not found ","true",searchBoxActual );

        //Burada ise Assert'in farklı bir yöntemi olan assertTrue kullanarak aynı işlemi test ettim.
        // Dedim ki eğer search box orada bulunuyorsa bana true döndür. Eğer orada bulunmazsa testim hata verir.
        WebElement searchBoxActual2 = driver.findElement(searchBoxXpath);
        Assert.assertTrue(searchBoxActual2.isDisplayed());
        
        // Burada da Type to search textinin , search box'un içinde yazıp yazmadığını kontrol ettim.
        // Ve assertle doğrulamasını yaptım.
        String search =  driver.findElement(searchBoxXpath).getAttribute("placeholder");
        System.out.println("Search box text is : "+search);
        Assert.assertEquals("Type to search",search);
    }
}
