package ohtu;

import ohtu.verkkokauppa.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Kauppa kauppa = ctx.getBean(Kauppa.class);


        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Jani Toivola", "1234-12345");


        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Seppo Taalasmaa", "3425-1652");

        // kirjanpito
        for (String tapahtuma : ctx.getBean(Kirjanpito.class).getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
