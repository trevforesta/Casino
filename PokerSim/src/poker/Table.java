package poker;

import java.util.ArrayList;

public class Table {
    ArrayList<Hand> hands = new ArrayList<>();
    Deck deck = new Deck();
    Board board = new Board();

    public Table(int numOfOpponents, Deck deck) {
        while (numOfOpponents > 0) {
            Hand hand = new Hand();
            hands.add(hand);
            --numOfOpponents;
        }
        this.deck = deck;
    }

    public void addPlayer(Hand player) {
        hands.add(0, player);
    }

    public void dealToPlayers() {
        int holeCards = 2;
        while (holeCards > 0) {

            for (Hand hand:hands) {
                deck.dealToHand(hand);
            }
            --holeCards;
        }
    }

    public void showHands() {
        for (Hand hand:hands) {
            hand.showHand();
            System.out.print("\n");
        }
    }
}
