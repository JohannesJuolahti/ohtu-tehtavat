package viikko1.ohtu;

import viikko1.ohtu.laskin.KonsoliIO;
import viikko1.ohtu.laskin.Laskin;

public class Main {
    public static void main(String[] args) {
        new Laskin(new KonsoliIO()).suorita();
    }
}
