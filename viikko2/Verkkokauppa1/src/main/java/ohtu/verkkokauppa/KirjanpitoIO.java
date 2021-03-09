package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoIO {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
