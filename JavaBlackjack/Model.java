package JavaBlackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {

    /******************** FIELDS ********************/

    private ArrayList<Card> deck;
    private ArrayList<Player> playerList;
    private ArrayList<Card> dealerHand;

    /******************** CONSTRUCTORS ********************/

    public Model() {
        this.deck = new ArrayList<Card>();
        fillDeck();
        shuffleDeck();
        this.playerList = new ArrayList<Player>();
        this.dealerHand = new ArrayList<Card>();
    }

    /******************** GETTERS AND SETTERS ********************/

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    /******************** PUBLIC METHODS ********************/

    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    public void removePlayer(Player player) {
        this.playerList.remove(player);
    }

    public void dealPlayerCard(Player player) {
        player.getHand().add(this.deck.get(0));
        this.deck.remove(0);
    }

    public void dealDealerCard() {
        dealerHand.add(this.deck.get(0));
        this.deck.remove(0);
    }

    public void dealEveryoneCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : this.playerList) {
                dealPlayerCard(player);
            }
            dealDealerCard();
        }
    }

    public int getDealerHandTotal() {
        int dealerHandTotal = 0;
        boolean hasAce = false;
        for (Card card : this.dealerHand) {
            if (card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
                dealerHandTotal += 10;
            } else if (card.getValue() == 1) {
                dealerHandTotal += 11;
                hasAce = true;
            } else {
                dealerHandTotal += card.getValue();
            }
        }
        if (dealerHandTotal > 21 && hasAce) {
            dealerHandTotal -= 10;
        }
        return dealerHandTotal;
    }

    public boolean isDealerHandBust() {
        if (getDealerHandTotal() > 21) {
            return true;
        }
        return false;
    }

    public boolean isDealerHand21() {
        if (getDealerHandTotal() == 21) {
            return true;
        }
        return false;
    }

    public boolean isDealerHand17() {
        if (getDealerHandTotal() > 16) {
            return true;
        }
        return false;
    }

    public void resetAllHands() {
        for (Player player : this.playerList) {
            player.getHand().clear();
        }
        this.dealerHand.clear();
    }

    public void resetDeck() {
        this.deck.clear();
        fillDeck();
        shuffleDeck();
    }

    /******************** PRIVATE METHODS ********************/

    private void fillDeck() {
        for (int i = 1; i < 14; i++) {
            Card newCard = new Card(i, "S");
            this.deck.add(newCard);
        }
        for (int i = 1; i < 14; i++) {
            Card newCard = new Card(i, "H");
            this.deck.add(newCard);
        }
        for (int i = 13; i > 0; i--) {
            Card newCard = new Card(i, "C");
            this.deck.add(newCard);
        }
        for (int i = 13; i > 0; i--) {
            Card newCard = new Card(i, "D");
            this.deck.add(newCard);
        }
    }

    private void shuffleDeck() {
        Random random = new Random();
        for (int i = (this.deck.size() - 1); i > 0; i--) {
            Collections.swap(this.deck, i, random.nextInt(i + 1));
        }
    }

}
