import java.util.ArrayList;

public class Player {
    protected int id;
    protected Palette palette;
    protected Hand hand;
    protected Game game;
    protected Card intentCard;

    public Player(int id, Game game) {
        this.id = id;
        this.game = game;
        palette = new Palette();
        hand    = new Hand();

        palette.addCard(game.deck.returnCard());
        for (int i = 0; i < 7; ++i) hand.addCard(game.deck.returnCard());
    }

    public void playCardFromHandToPalette(int index) {
        if (index < hand.cards.size()) {
            palette.addCard(hand.cards.get(index));
            hand.removeCard(index);
        }
        else
            System.out.println("There isn't card in hand with index : " + index);
    }

    public void playCardFromHandToPalette(Card card) {
        if (hand.cards.contains(card)) {
            palette.addCard(card);
            hand.removeCard(card);
        }
        else
            System.out.println("There isn't such card in hand : " + card);
    }

    public void discardCardFromHandToRulesPile(int index) {
        if (index < hand.cards.size()) {
            game.rulesPile = hand.cards.get(index);
            hand.removeCard(index);
        }
        else
            System.out.println("There isn't card in hand with index : " + index);
    }

    public void discardCardFromHandToRulesPile(Card card) {
        if (hand.cards.contains(card)) {
            game.rulesPile = card;
            hand.removeCard(card);
        }
        else
            System.out.println("There isn't such card in hand : " + card);
    }

    //TODO нужно придумать как проверять и кто проверять должен
    public ArrayList<Card> getRuledCards(Card cardFromRulesPile, Card intentCard) {
        this.intentCard = intentCard;
        return palette.getRuledCards(cardFromRulesPile);
    }

    @Override
    public String toString() {
        return "Player id = " + id + ":\n"
                + palette + "\n"
                + hand;
    }
}
