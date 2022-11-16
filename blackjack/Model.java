package blackjack;

import java.util.ArrayList;

public class Model {
    
    private ArrayList<Player> players;
    private Deck deck;

    public Model() {
        this.players = new ArrayList<Player>();
        this.deck = new Deck();
    }
}
