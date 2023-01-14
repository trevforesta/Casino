package JavaBlackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Model {

    /******************** FIELDS ********************/

    // the deck of cards
    private ArrayList<Card> deck;
    // the list of players currently playing
    private ArrayList<Player> playerList;
    // the hand of the dealer
    private ArrayList<Card> dealerHand;

    /******************** CONSTRUCTORS ********************/

    /**
     * Default constructor
     */
    public Model() {
        this.deck = new ArrayList<Card>();
        fillDeck();
        shuffleDeck();
        this.playerList = new ArrayList<Player>();
        this.dealerHand = new ArrayList<Card>();
    }

    /******************** GETTERS AND SETTERS ********************/

    /**
     * Gets the deck of cards
     * 
     * @return the deck of cards
     */
    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    /**
     * Gets the list of players currently playing
     * 
     * @return the list of players currently playing
     */
    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * Gets the hand of the dealer
     * 
     * @return the hand of the dealer
     */
    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    /**
     * Sets the deck of cards
     * 
     * @return the deck of cards to set the current deck to
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Sets the list of players currently playing
     * 
     * @return the list of players to set the current list of players to
     */
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * Sets the hand of the dealer
     * 
     * @return the hand of the dealer to set the current hand to
     */
    public void setDealerHand(ArrayList<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    /******************** PUBLIC METHODS ********************/

    /**
     * Adds a player to the player list
     * 
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        this.playerList.add(player);
    }

    /**
     * Removes a player from the list
     * 
     * @param player the player to remove
     */
    public void removePlayer(Player player) {
        this.playerList.remove(player);
    }

    /**
     * Deals a given player the next card in the deck
     * 
     * @param player the player to deal the card to
     */
    public void dealPlayerCard(Player player) {
        player.getHand().add(this.deck.get(0));
        this.deck.remove(0);
    }

    /**
     * Deals the dealer the next card in the deck
     */
    public void dealDealerCard() {
        dealerHand.add(this.deck.get(0));
        this.deck.remove(0);
    }

    /**
     * Deals everyone two cards
     */
    public void initializeHands() {
        for (Player player : this.playerList) {
            dealPlayerCard(player);
        }
        dealDealerCard();
        for (Player player : this.playerList) {
            dealPlayerCard(player);
        }
        dealDealerCard();
    }

    /**
     * Calculates the total value of the given hand
     * 
     * @param hand the given hand
     * @return the total value of the hand
     */
    public int getHandTotal(ArrayList<Card> hand) {
        int handTotal = 0;
        boolean hasAce = false;
        for (Card card : hand) {
            if (card.getValue() == 11 || card.getValue() == 12 || card.getValue() == 13) {
                handTotal += 10;
            } else if (card.getValue() == 1) {
                handTotal += 11;
                hasAce = true;
            } else {
                handTotal += card.getValue();
            }
        }
        if (handTotal > 21 && hasAce) {
            handTotal -= 10;
        }
        return handTotal;
    }

    /**
     * Checks to see if the given hand is greater than 21
     * 
     * @param hand the hand to check
     * @return true if it is greater than 21, otherwise false
     */
    public boolean isHandBust(ArrayList<Card> hand) {
        if (getHandTotal(hand) > 21) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the given hand is equal to 21
     * 
     * @param hand the hand to check
     * @return true if it is equal to 21, otherwise false
     */
    public boolean isHand21(ArrayList<Card> hand) {
        if (getHandTotal(hand) == 21) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the given hand is blackjack
     * 
     * @param hand the hand to check
     * @return true if it is blackjack, otherwise false
     */
    public boolean isHandBlackjack(ArrayList<Card> hand) {
        if (getHandTotal(hand) == 21 && hand.size() == 2) {
            return true;
        }
        return false;
    }

    /**
     * Calculates the total value of the dealer's hand
     * 
     * @return the total value of the dealer's hand
     */
    public int getDealerHandTotal() {
        return this.getHandTotal(dealerHand);
    }

    /**
     * Checks to see if the dealer's hand is greater than 21
     * 
     * @return true if it is greater than 21, otherwise false
     */
    public boolean isDealerHandBust() {
        return isHandBust(dealerHand);
    }

    /**
     * Checks to see if the dealer's hand is equal to 21
     * 
     * @return true if it is equal to 21, otherwise false
     */
    public boolean isDealerHand21() {
        return isHand21(dealerHand);
    }

    /**
     * Checks to see if the dealer's hand is 17 or more
     * 
     * @return true if it is more than 17, otherwise false
     */
    public boolean isDealerHand17() {
        if (getHandTotal(this.dealerHand) > 16) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the dealer's hand is blackjack
     * 
     * @return true if it is blackjack, otherwise false
     */
    public boolean isDealerHandBlackjack() {
        if (isHandBlackjack(this.dealerHand)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the dealer is showing an ace
     * 
     * @return true if it is showing an ace, otherwise false
     */
    public boolean isDealerShowingAce() {
        if (this.dealerHand.get(0).getValue() == 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the dealer is showing a 10 or face card
     * 
     * @return true if it is showing a 10, otherwise false
     */
    public boolean isDealerShowing10() {
        if (this.dealerHand.get(0).getValue() == 10
                || this.dealerHand.get(0).getValue() == 11
                || this.dealerHand.get(0).getValue() == 12
                || this.dealerHand.get(0).getValue() == 13) {
            return true;
        }
        return false;
    }

    /**
     * Resets all of the player's hands and the dealer's hand
     */
    public void resetAllHands() {
        for (Player player : this.playerList) {
            player.getHand().clear();
        }
        this.dealerHand.clear();
    }

    /**
     * Resets the deck
     */
    public void resetDeck() {
        this.deck.clear();
        fillDeck();
        shuffleDeck();
    }

    /******************** PRIVATE METHODS ********************/

    /**
     * Fills the deck of cards with newly created cards
     * AS-KS, AD-KD, KC-AC, KH-AH
     */
    private void fillDeck() {
        for (int i = 1; i < 14; i++) {
            Card newCard = new Card(i, "S");
            this.deck.add(newCard);
        }
        for (int i = 1; i < 14; i++) {
            Card newCard = new Card(i, "D");
            this.deck.add(newCard);
        }
        for (int i = 13; i > 0; i--) {
            Card newCard = new Card(i, "C");
            this.deck.add(newCard);
        }
        for (int i = 13; i > 0; i--) {
            Card newCard = new Card(i, "H");
            this.deck.add(newCard);
        }
    }

    /**
     * Shuffles the deck
     */
    private void shuffleDeck() {
        Random random = new Random();
        for (int i = (this.deck.size() - 1); i > 0; i--) {
            Collections.swap(this.deck, i, random.nextInt(i + 1));
        }
    }

}
