import java.util.ArrayList;

public class Game {
    protected ArrayList<Player> players;
    protected Deck deck;
    protected Card rulesPile;

    public Game(int numOfPlayers) {
        players = new ArrayList<>(numOfPlayers);
        deck = new Deck();
        rulesPile = new Card("Red", 0);

        for (int i = 0; i < numOfPlayers; ++i) {
            players.add(new Player(i, this));
        }
    }
}
