package blackjack;

import java.util.ArrayList;

public class View {

    /**
     * Constructs a new View
     */
    public View() {

    }

    /**
     * Prints a card in a readable format
     * 
     * @param card the card to print
     */
    public void printCard(Card card) {
        if (card.getValue() == 1) {
            System.out.print("A" + card.getSuit());
        } else if (card.getValue() == 11) {
            System.out.print("J" + card.getSuit());
        } else if (card.getValue() == 12) {
            System.out.print("Q" + card.getSuit());
        } else if (card.getValue() == 13) {
            System.out.print("K" + card.getSuit());
        } else {
            System.out.print(card.getValue() + card.getSuit());
        }
    }

    /**
     * Prints a deck in a readable format
     * 
     * @param deck the deck to print
     */
    public void printDeck(ArrayList<Card> deck) {
        System.out.print("Deck: [ ");
        printCard(deck.get(0));
        for (int i = 1; i < deck.size(); i++) {
            System.out.print(", ");
            printCard(deck.get(i));
        }
        System.out.println(" ]");
    }

    /**
     * Prints a player's hand in a readable format
     * 
     * @param player
     */
    public void printHand(Player player) {
        System.out.print(player.getName() + ": [ ");
        printCard(player.getHand().get(0));
        for (int i = 1; i < player.getHand().size(); i++) {
            System.out.print(", ");
            printCard(player.getHand().get(i));
        }
        System.out.print(" ]");
    }

    /**
     * Prints a player's money in a readable format
     * 
     * @param player
     */
    public void printMoney(Player player) {
        System.out.print(player.getName() + ": $" + player.getMoney());
    }
}
