package JavaBlackjack;

import java.util.ArrayList;

public class View {

    /******************** CONSTRUCTORS ********************/

    public View() {

    }

    /******************** PUBLIC METHODS ********************/

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

    public void printDeck(ArrayList<Card> deck) {
        System.out.print("Deck: [ ");
        printCard(deck.get(0));
        for (int i = 1; i < deck.size(); i++) {
            System.out.print(", ");
            printCard(deck.get(i));
        }
        System.out.print(" ]");
    }

    public void printPlayerHand(Player player) {
        System.out.print(player.getName() + ": [ ");
        printCard(player.getHand().get(0));
        for (int i = 1; i < player.getHand().size(); i++) {
            System.out.print(", ");
            printCard(player.getHand().get(i));
        }
        System.out.print(" ]");
    }

    public void printPartialDealerHand(ArrayList<Card> dealerHand) {
        System.out.print("Dealer: [ ");
        printCard(dealerHand.get(0));
        System.out.print(", -- ]");
    }

    public void printFullDealerHand(ArrayList<Card> dealerHand) {
        System.out.print("Dealer: [ ");
        printCard(dealerHand.get(0));
        for (int i = 1; i < dealerHand.size(); i++) {
            System.out.print(", ");
            printCard(dealerHand.get(i));
        }
        System.out.println(" ]");
    }

    public void printAllHands(ArrayList<Player> playerList, ArrayList<Card> dealerHand) {
        printPartialDealerHand(dealerHand);
        for (int i = 0; i < playerList.size(); i++) {
            System.out.print(", ");
            printPlayerHand(playerList.get(i));
        }
        System.out.println();
    }

    public void printAllMoney(ArrayList<Player> playerList) {
        System.out.print(playerList.get(0).getName() + " ($" + playerList.get(0).getMoney() + ")");
        for (int i = 1; i < playerList.size(); i++) {
            System.out.print(", ");
            System.out.print(playerList.get(i).getName() + " ($" + playerList.get(i).getMoney() + ")");
        }
        System.out.println();
    }

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
