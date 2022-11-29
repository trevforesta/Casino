package JavaBlackjack;

import java.util.ArrayList;

public class Player {

    /******************** FIELDS ********************/

    private String name;
    private ArrayList<Card> hand;
    private double money;
    private double bet;

    /******************** CONSTRUCTORS ********************/

    public Player(String name, double money) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.money = money;
        this.bet = 0.0;
    }

    /******************** GETTERS AND SETTERS ********************/

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public double getMoney() {
        return this.money;
    }

    public double getBet() {
        return this.bet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    /******************** PUBLIC METHODS ********************/

    public void placeBet(double bet) {
        this.bet = bet;
        this.money = this.money - this.bet;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public int getHandTotal() {
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

    public boolean isHandBust() {
        if (getHandTotal() > 21) {
            return true;
        }
        return false;
    }

    public boolean isHand21() {
        if (getHandTotal() == 21) {
            return true;
        }
        return false;
    }

}