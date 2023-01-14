package JavaBlackjack;

import java.util.ArrayList;

public class View {

    /******************** CONSTRUCTORS ********************/

    /**
     * Default constructor
     */
    public View() {

    }

    /******************** PUBLIC METHODS ********************/

    /**
     * Prints the values of a card
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
     * Prints the contents of a deck
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
        System.out.print(" ]");
    }

    /**
     * Prints a players hand
     * 
     * @param player the player whose hand to print
     */
    public void printPlayerHand(Player player) {
        System.out.print(player.getName() + ": [ ");
        printCard(player.getHand().get(0));
        for (int i = 1; i < player.getHand().size(); i++) {
            System.out.print(", ");
            printCard(player.getHand().get(i));
        }
        System.out.print(" ]");
    }

    /**
     * Prints the first card of the dealer's hand, followed by asterisks
     * 
     * @param dealerHand the dealer's hand
     */
    public void printPartialDealerHand(ArrayList<Card> dealerHand) {
        System.out.print("Dealer: [ ");
        printCard(dealerHand.get(0));
        System.out.print(", -- ]");
    }

    /**
     * Prints the entire dealer's hand
     * 
     * @param dealerHand the dealer's hand
     */
    public void printFullDealerHand(ArrayList<Card> dealerHand) {
        System.out.print("Dealer: [ ");
        printCard(dealerHand.get(0));
        for (int i = 1; i < dealerHand.size(); i++) {
            System.out.print(", ");
            printCard(dealerHand.get(i));
        }
        System.out.println(" ]");
    }

    /**
     * Prints the dealer's partial hand, then all of the player's hands one after
     * the other
     * 
     * @param playerList the list of players
     * @param dealerHand the dealers hand
     */
    public void printAllHands(ArrayList<Player> playerList, ArrayList<Card> dealerHand) {
        printPartialDealerHand(dealerHand);
        for (int i = 0; i < playerList.size(); i++) {
            System.out.print(", ");
            printPlayerHand(playerList.get(i));
        }
        System.out.println();
    }

    /**
     * Prints all of the player's names and their current total money one after the
     * other
     * 
     * @param playerList the list of players
     */
    public void printAllMoney(ArrayList<Player> playerList) {
        System.out.print(playerList.get(0).getName() + " ($" + playerList.get(0).getMoney() + ")");
        for (int i = 1; i < playerList.size(); i++) {
            System.out.print(", ");
            System.out.print(playerList.get(i).getName() + " ($" + playerList.get(i).getMoney() + ")");
        }
        System.out.println();
    }

    /**
     * Prints all of the player's names, current total money, and bets one after the
     * other
     * 
     * @param playerList the list of players
     */
    public void printAllBets(ArrayList<Player> playerList) {
        System.out.print(
                playerList.get(0).getName() + " ($" + playerList.get(0).getMoney() + "): $"
                        + playerList.get(0).getBet());
        for (int i = 1; i < playerList.size(); i++) {
            System.out.print(", ");
            System.out.print(
                    playerList.get(i).getName() + " ($" + playerList.get(i).getMoney() + "): $"
                            + playerList.get(i).getBet());
        }
        System.out.println();
    }
}
