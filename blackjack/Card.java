package blackjack;

public class Card {

    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return this.value;
    }

    public String getSuit() {
        return this.suit;
    }

}
