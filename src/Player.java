import java.util.ArrayList;

public class Player {
    protected Game game;
    protected int id;
    protected Palette palette;
    protected Hand hand;
    protected Card intentPlayCard;
    protected Card intentDiscardCard;
    protected boolean isWinIntentPlayCard;
    protected boolean isWinIntentDiscardCard;

    public Player(int id, Game game) {
        this.id = id;
        this.game = game;
        palette = new Palette();
        hand    = new Hand();

        palette.addCard(game.deck.returnCard());
        for (int i = 0; i < 7; ++i) hand.addCard(game.deck.returnCard());
    }

    public void playCardFromHandToPalette(int indexOfCard) {
        if (0 <= indexOfCard && indexOfCard < hand.cards.size()) {
            palette.addCard(hand.cards.get(indexOfCard));
            hand.removeCard(indexOfCard);
        }
        else
            System.out.println("There isn't card in hand with index : " + indexOfCard);
    }

    public void playCardFromHandToPalette(Card card) {
        if (hand.cards.contains(card)) {
            palette.addCard(card);
            hand.removeCard(card);
        }
        else
            System.out.println("There isn't such card in hand : " + card);
    }

    public void discardCardFromHandToRulesPile(int indexOfCard) {
        if (0 <= indexOfCard && indexOfCard < hand.cards.size()) {
            game.rulesPile = hand.cards.get(indexOfCard);
            hand.removeCard(indexOfCard);
        }
        else
            System.out.println("There isn't card in hand with index : " + indexOfCard);
    }

    public void discardCardFromHandToRulesPile(Card card) {
        if (hand.cards.contains(card)) {
            game.rulesPile = card;
            hand.removeCard(card);
        }
        else
            System.out.println("There isn't such card in hand : " + card);
    }

    public void tryToPlayCard(int indexOfCard) {
        if (0 <= indexOfCard && indexOfCard < hand.cards.size()) {
            this.intentPlayCard = hand.cards.get(indexOfCard);
            palette.addCard(this.intentPlayCard);

            if (game.getIdWinner() == id) {
                System.out.println("Player " + id + " will win with " + intentPlayCard);
                isWinIntentPlayCard = true;
            }
            else {
                System.out.println("Player " + id + " will not win with " + intentPlayCard);
                isWinIntentPlayCard = false;
            }
            palette.removeCard(intentPlayCard);
        }
        else {
            System.out.println("There isn't card in hand with index : " + indexOfCard);
        }
    }

    public void tryToPlayCard(Card intentCard) {
        if (hand.cards.contains(intentCard)) {
            this.intentPlayCard = intentCard;
            palette.addCard(this.intentPlayCard);

            if (game.getIdWinner() == id) {
                System.out.println("Player " + id + " will win with " + intentCard);
                isWinIntentPlayCard = true;
            }
            else {
                System.out.println("Player " + id + " will not win with " + intentCard);
                isWinIntentPlayCard = false;
            }

            palette.removeCard(intentCard);
        }
        else {
            System.out.println("There isn't such card in hand : " + intentCard);
        }
    }

    public void tryToDiscardCard(int indexOfCard) {
        if (0 <= indexOfCard && indexOfCard < hand.cards.size()) {
            intentDiscardCard = hand.cards.get(indexOfCard);
            Card previousCard = game.rulesPile;
            game.rulesPile = intentPlayCard;

            if (game.getIdWinner() == id) {
                System.out.println("Player " + id + " will win with new rule " + game.rulesPile);
                isWinIntentDiscardCard = true;
            }
            else {
                System.out.println("Player " + id + " will not win with new rule " + game.rulesPile);
                isWinIntentDiscardCard = false;
            }
            game.rulesPile = previousCard;
        }
        else {
            System.out.println("There isn't card in hand with index : " + indexOfCard);
        }
    }

    public void tryToDiscardCard(Card intentCard) {
        if (hand.cards.contains(intentCard)) {
            intentDiscardCard = intentCard;
            Card previousCard = game.rulesPile;
            game.rulesPile = intentCard;

            if (game.getIdWinner() == id) {
                System.out.println("Player " + id + " will win with new rule " + game.rulesPile);
                isWinIntentDiscardCard = true;
            }
            else {
                System.out.println("Player " + id + " will not win with new rule " + game.rulesPile);
                isWinIntentDiscardCard = false;
            }

            game.rulesPile = previousCard;
        }
        else {
            System.out.println("There isn't such card in hand : " + intentCard);
        }
    }

    //TODO нужно придумать как проверять и кто проверять должен
    public ArrayList<Card> getRuledCards(Card cardFromRulesPile) {
        return palette.getRuledCards(cardFromRulesPile);
    }

    @Override
    public String toString() {
        return "Player id = " + id + ":\n"
                + palette + "\n"
                + hand;
    }
}
