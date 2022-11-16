package blackjack;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private String name;

    public Player(String name) {
        this.hand = new ArrayList<Card>();
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public void hit(Card card) {
        this.hand.add(card);
    }

    public void printHand() {
        System.out.print(name + " has: ");
        for (Card card : this.hand) {
            card.printCard();
            System.out.print(" ");
        }
    }

}
