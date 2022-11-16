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

    public void printCard() {
        if (value == 1) {
            System.out.print("A" + suit);
        } else if (value == 11) {
            System.out.print("J" + suit);
        } else if (value == 12) {
            System.out.print("Q" + suit);
        } else if (value == 13) {
            System.out.print("K" + suit);
        } else {
            System.out.print(value + suit);
        }
    }

}
