package JavaBlackjack;

public class Card {

    /******************** FIELDS ********************/

    private int value;
    private String suit;

    /******************** CONSTRUCTORS ********************/

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    /******************** GETTERS AND SETTERS ********************/

    public int getValue() {
        return this.value;
    }

    public String getSuit() {
        return this.suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

}