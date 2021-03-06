package viikko1.ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import viikko1.ohtuesimerkki.Player;
import viikko1.ohtuesimerkki.Reader;
import viikko1.ohtuesimerkki.Statistics;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void checkPlayersListIsGenerated() {
        assertFalse(readerStub.getPlayers().isEmpty());
    }

    @Test
    public void searchForPlayersWithName() {
        assertNull(stats.search("Selaaneee"));
        assertNotNull(stats.search("Gretzky"));
    }

    @Test
    public void addPlayersToTeam() {
        assertTrue(stats.team("Jokerit").isEmpty());
        assertFalse(stats.team("EDM").isEmpty());
    }

    @Test
    public void returnTopScorers() {
        assertTrue(stats.topScorers(-1).isEmpty());
        assertFalse(stats.topScorers(2).isEmpty());
    }
}
