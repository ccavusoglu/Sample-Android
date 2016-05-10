package tr.org.sample.app.presentation.models;

/**
 * Created by ccavusoglu on 16.04.2016.
 */
public class Items {
    public String image;
    public String name;
    public String title;
    public String koNo;
    public String siNo;
    public String firmaAdi;
    public String adres;

    public Items(String image, String name, String title, String koNo, String siNo, String firmaAdi, String adres) {
        this.name = name;
        this.title = title;
        this.koNo = koNo;
        this.siNo = siNo;
        this.image = image;
        this.firmaAdi = firmaAdi;
        this.adres = adres;
    }
}
