import java.util.ArrayList;
import java.util.Collection;

public class Void {
    public static void main(String[] args) {
        Game game = new Game(3);

        game.rulesPile = new Card("Blue", 0);

        game.players.get(0).playCardFromHandToPalette(0);
        game.players.get(0).playCardFromHandToPalette(0);

        game.players.get(1).playCardFromHandToPalette(0);
        game.players.get(1).playCardFromHandToPalette(0);

        game.players.get(2).playCardFromHandToPalette(0);
        game.players.get(2).playCardFromHandToPalette(0);

        System.out.println(game.players.get(0));
        System.out.println(game.players.get(1));
        System.out.println(game.players.get(2));
        System.out.println("Id of winner: " + game.getIdWinner());

        game.players.get(2).tryToDiscardCard(0);

    }
}