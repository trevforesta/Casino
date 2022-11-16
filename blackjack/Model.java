package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {

    // the list of players at the table
    private ArrayList<Player> table;
    // the deck of cards
    private ArrayList<Card> deck;
    // the dealer's hand
    private ArrayList<Card> dealerHand;

    /**
     * Constructs a new Model
     */
    public Model() {
        this.deck = new ArrayList<Card>();
        this.table = new ArrayList<Player>();
        fillDeck();
    }

    /**
     * Fills the deck with cards
     * A-K of spades, A-K of hearts, K-A of clubs, K-A of diamonds
     */
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

    /**
     * Get the list of players at the table
     * 
     * @return the list of players at the table
     */
    public ArrayList<Player> getTable() {
        return this.table;
    }

    /**
     * Get the deck of cards
     * 
     * @return the deck of cards
     */
    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    /**
     * Get the dealer's hand
     * 
     * @return the dealer's hand
     */
    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    /**
     * Adds a new player to the table
     * 
     * @param player the new player
     */
    public void addPlayer(Player player) {
        this.table.add(player);
    }

    /**
     * Shuffles the deck of cards
     */
    public void shuffleDeck() {
        Random random = new Random();
        for (int i = (this.deck.size() - 1); i > 0; i--) {
            Collections.swap(this.deck, i, random.nextInt(i + 1));
        }
    }

    /**
     * Deals the first card in the deck to a player
     * 
     * @param player the player to deal a card to
     */
    public void dealPlayerCard(Player player) {
        player.getHand().add(this.deck.get(0));
        this.deck.remove(0);
    }

    /**
     * Deals the first card in the deck to the dealer
     */
    public void dealDealerCard() {
        dealerHand.add(this.deck.get(0));
        this.deck.remove(0);
    }

    /**
     * Checks to see if a player has gone bust
     * 
     * @param player the player to check
     * @return true if the player has gone bust, otherwise false
     */
    public boolean isBust(Player player) {
        int totalValue = 0;
        for (Card card : player.getHand()) {
            if (card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
                totalValue += 10;
            } else if (card.getValue() == 1) {
                totalValue += 11;
            } else {
                totalValue += card.getValue();
            }
        }
        if (totalValue > 21) {
            return true;
        }
        return false;
    }

}
