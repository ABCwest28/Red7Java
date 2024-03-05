import java.util.ArrayList;

public class Hand {
    protected ArrayList<Card> cards = new ArrayList<>(7);
    protected int numOfCards = 0;

    protected void addCard(Card card) {
        cards.add(card);
        ++numOfCards;
    }

    protected void removeCard(Card card) {
        cards.remove(card);
        --numOfCards;
    }

    protected void removeCard(int index) {
        cards.remove(index);
        --numOfCards;
    }

    @Override
    public String toString() {
        return "Hand:\n" + cards;
    }
}
