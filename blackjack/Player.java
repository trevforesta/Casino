package blackjack;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private String name;
    private int money;

    public Player(String name, int money) {
        this.hand = new ArrayList<Card>();
        this.name = name;
        this.money = money;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public String getName() {
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
