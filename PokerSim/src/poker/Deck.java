package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
        int suit = 0;
        int value = 0;

        String suitName = "S";

        String valueName;

        while (suit < 4) {
            while (value < 13) {
                valueName = Integer.toString(value + 2);
                if (value == 9) {
                    valueName = "J";
                } else if (value == 10) {
                    valueName = "Q";
                }
                else if (value == 11) {
                    valueName = "K";
                }
                else if (value == 12){
                    valueName = "A";
                }
                Card card = new Card(suitName, valueName, value);
                deck.add(card);
                ++value;
            }

            value = 0;
            ++suit;
            if (suit == 1) {
                suitName = "H";
            } else if (suit == 2) {
                suitName = "C";
            } else {
                suitName = "D";
            }
        }
    }

    public void printDeck() {
        for (Card card:deck) {
            card.printCard();
            System.out.print(" ");
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void dealToHand(Hand hand) {
        hand.addCard(deck.get(0));
        deck.remove(0);
    }

    public void dealToBoard(Board board) {
        board.addCard(deck.get(0));
        deck.remove(0);
    }

    public void flop(Board board) {
        for (int i = 0; i < 3; i++) {
            dealToBoard(board);
        }
    }

    public void turn(Board board) {
        dealToBoard(board);
    }

    public void river(Board board) {
        dealToBoard(board);
    }

    public Card dealCard() {
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }
}
