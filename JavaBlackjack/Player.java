package JavaBlackjack;

import java.util.ArrayList;

public class Player {

    /******************** FIELDS ********************/

    // the name of the player
    private String name;
    // the current hand of the player
    private ArrayList<Card> hand;
    // the current money of the player
    private double money;
    // the player's most recent placed bet
    private double bet;

    /******************** CONSTRUCTORS ********************/

    /**
     * Default constructor
     * 
     * @param name  the new player's name
     * @param money the new player's amount of money
     */
    public Player(String name, double money) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.money = money;
        this.bet = 0.0;
    }

    /******************** GETTERS AND SETTERS ********************/

    /**
     * Gets the name of the player
     * 
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the current hand of the player
     * 
     * @return the current hand of the player
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /**
     * Gets the current money of the player
     * 
     * @return the current money of the player
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Gets the player's most recent placed bet
     * 
     * @return the player's most recent placed bet
     */
    public double getBet() {
        return this.bet;
    }

    /**
     * Sets the name of the player
     * 
     * @param name the name to set the player's current name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the current hand of the player
     * 
     * @param hand the hand to set the player's current hand to
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Sets the current money of the player
     * 
     * @param money the money to set the player's current money to
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Sets the player's most recent placed bet
     * 
     * @param bet the bet to set the player's most recent placed bet to
     */
    public void setBet(double bet) {
        this.bet = bet;
    }

    /******************** PUBLIC METHODS ********************/

    /**
     * Sets the bet of the player to the given value and decreases the current money
     * 
     * @param bet the amount of money to bet
     */
    public void placeBet(double bet) {
        this.bet = bet;
        this.money = this.money - this.bet;
    }

    /**
     * Increases the player's money by the given amount
     * 
     * @param money the amount of money to give to the player
     */
    public void addMoney(double money) {
        this.money += money;
    }

}