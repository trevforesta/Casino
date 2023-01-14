package JavaBlackjack;

public class Card {

    /******************** FIELDS ********************/

    // the value of the card
    // 1 is ace, 11 is jack, 12 is queen, 13 is king
    private int value;
    // the suit of the card
    // S for spades, D for diamonds, C for clubs, H for hearts
    private String suit;

    /******************** CONSTRUCTORS ********************/

    /**
     * Default constructor
     * 
     * @param value the new card's value
     * @param suit  the new card's suit
     */
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    /******************** GETTERS AND SETTERS ********************/

    /**
     * Gets the value of the card
     * 
     * @return the value of the card
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Gets the suit of the card
     * 
     * @return the suit of the card
     */
    public String getSuit() {
        return this.suit;
    }

    /**
     * Sets the value of the card
     * 
     * @param value the value to set the card's current value to
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the suit of the card
     * 
     * @param suit the suit to set the card's current suit to
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

}