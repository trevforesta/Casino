package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
        fillDeck();
    }

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

    public Card get(int index) {
        return this.deck.get(index);
    }

    public void shuffleDeck() {
        Random random = new Random();
        for (int i = 51; i > 0; i--) {
            Collections.swap(this.deck, i, random.nextInt(i + 1));
        }
    }

    public void dealCard(Player player) {
        player.getHand().add(this.deck.get(0));
        this.deck.remove(0);
    }
}
