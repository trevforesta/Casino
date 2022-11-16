package blackjack;

public class View {
    
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
    public void printDeck(Deck deck) {
        System.out.print("[ ");
        printCard(deck.get(0));
        for (int i = 1; i < 52; i++) {
            System.out.print(", ");
            printCard(deck.get(i));
        }
        System.out.println(" ]");
    }
}
