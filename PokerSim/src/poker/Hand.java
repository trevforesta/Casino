package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> combinedHand = new ArrayList<>();

    public Hand() {
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void showHand() {
        for (Card card : hand) {
            card.printCard();
            System.out.print(" ");
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void makeCombinedHand(Board board) {
        combinedHand.addAll(board.getBoard());
    }

    public void resetCombinedHand(){
        combinedHand = hand;
    }

    public void sortCombinedHand() {
        combinedHand.sort(Comparator.comparing((Card::getValue)));
    }


    // evaluates the hand
    public void evaluate(Board board) {
        makeCombinedHand(board);
        this.sortCombinedHand();

        if (this.royalFlush() == 1) {
            System.out.println("You have a royal flush!");
        } else if (this.straightFlush() == 1) {
            System.out.println("You have a straight flush!");
        } else if (this.fourOfaKind() == 1) {
            System.out.println("You have four of a kind!");
        } else if (this.fullHouse() == 1) {
            System.out.println("You have a full house!");
        } else if (this.flush() == 1) {
            System.out.println("You have a flush!");
        } else if (this.straight() == 1) {
            System.out.println("You have a straight!");
        } else if (this.triple() == 1) {
            System.out.println("You have trips!");
        } else if (this.twoPairs() == 1) {
            System.out.println("You have two pairs!");
        } else if (this.pair() == 1) {
            System.out.println("You have a pair!");
        } else {
            System.out.println("Your highest card is ");
            combinedHand.get(combinedHand.size() - 1).printCard();
        }
        resetCombinedHand();
    }

    // checks for a royal flush
    public int royalFlush() {
        if (combinedHand.size() == 8) {
            return 1;
        } else {
            return 0;
        }
    }

    // checks for a straight flush
    public int straightFlush(Board board) {
        for (int counter = 1; counter < 5; counter++) {
            if (hand[0].suit != hand[counter].suit) {
                return 0;
            }
        }
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1)) {
                return 0;
            }

        }
        return 1;

    }

    // checks for four of a kind
    public int fourOfaKind(Board board) {
        if (hand[0].rank != hand[3].rank && hand[1].rank != hand[4].rank) {
            return 0;
        } else {
            return 1;
        }
    }

    // checks for full house
    public int fullHouse() {
        int comparison = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (hand[counter - 1].rank == hand[counter].rank) {
                comparison++;
            }
        }
        if (comparison == 3) {
            return 1;
        } else {
            return 0;
        }
    }

    // checks for flush
    public int flush() {
        for (int counter = 1; counter < 5; counter++) {
            if (hand[0].suit != hand[counter].suit) {
                return 0;
            }
        }
        return 1;
    }

    // check for straight
    public int straight() {
        for (int counter2 = 1; counter2 < 5; counter2++) {
            if (hand[counter2 - 1].rank != (hand[counter2].rank - 1)) {
                return 0;
            }

        }
        return 1;
    }

    // checks for triple
    public int triple() {
        if (hand[0].rank == hand[2].rank || hand[2].rank == hand[4].rank) {
            return 1;
        }
        return 0;
    }

    // checks for two pairs
    public int twoPairs() {
        int check = 0;
        for (int counter = 1; counter < 5; counter++) {
            if (hand[counter - 1].rank == hand[counter].rank) {
                check++;
            }
        }
        if (check == 2) {
            return 1;
        } else {
            return 0;
        }
    }

    // check for pair
    public int pair() {
        int check = 0;
        for (int i = 0; i < combinedHand.size(); ++i) {
            if (combinedHand.get(i).getValue() == combinedHand.get(i + 1).getValue()) {
                ++check;
            }
        }
        if (check == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
