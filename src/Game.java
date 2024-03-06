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

    public int getIdWinner(Card intentCard) {
        class DataToCheck {
            int id;
            ArrayList<Card> cards;
            boolean isExcluded;

            DataToCheck(int id, ArrayList<Card> cards) {
                this.id = id;
                this.cards = cards;
                isExcluded = false;
            }
        }
        ArrayList<DataToCheck> listDataToChecks = new ArrayList<>(players.size());

        for (int i = 0; i < players.size(); ++i) {
            listDataToChecks.add(new DataToCheck(i, players.get(i).getRuledCards(rulesPile, intentCard)));
        }

        // Проверяем по кол-ву карт - исключаем у кого меньше карт
        int maxCount = 0;
        for (DataToCheck dataToCheck : listDataToChecks)
            if (dataToCheck.cards.size() > maxCount) maxCount = dataToCheck.cards.size();
        for (DataToCheck dataToCheck : listDataToChecks)
            if (dataToCheck.cards.size() < maxCount) dataToCheck.isExcluded = true;


        int maxHashCode = 0;
        int idOfPlayerWithHighestCard = 0;
        for (int i = 0; i < listDataToChecks.size(); ++i) {
            if (!listDataToChecks.get(i).isExcluded) {
                for (Card card : listDataToChecks.get(i).cards) {
                    if (card.hashCode() > maxHashCode) {
                        maxHashCode = card.hashCode();
                        idOfPlayerWithHighestCard = i;
                    }
                }
            }
        }

        return idOfPlayerWithHighestCard;
    }


}
