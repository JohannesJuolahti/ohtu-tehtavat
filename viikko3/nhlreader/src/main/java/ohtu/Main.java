package ohtu;

import com.google.gson.Gson;

import java.io.IOException;

import org.apache.http.client.fluent.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText);

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        ArrayList<Player> finnishPlayers = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Players from FIN " + dtf.format(now));
        for (Player player : players) {
            if (player.getNationality().contentEquals("FIN")) {
                finnishPlayers.add(player);
            }
        }
        Collections.sort(finnishPlayers, Player::compareTo);
        for (Player p : finnishPlayers) {
            System.out.println(p.toString());
        }
    }

}