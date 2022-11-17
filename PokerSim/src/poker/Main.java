package poker;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {

            Deck deck = new Deck();
            deck.shuffle();

            int input;
            String playAgain;

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n\nAlways press enter to continue...");
            System.out.print("\n\nEnter number of opponents: ");
            input = scanner.nextInt();

            Table table = new Table(input, deck);
            Hand myHand = new Hand();
            table.addPlayer(myHand);

            table.dealToPlayers();
            table.showHands();
            System.in.read();
            System.out.flush();

            deck.flop(table.board);
            table.board.showBoard();
            //myHand.evaluate(table.board);
            System.in.read();
            System.out.flush();

            deck.turn(table.board);
            table.board.showBoard();
            System.in.read();
            System.out.flush();

            deck.river(table.board);
            table.board.showBoard();
            System.in.read();

            System.out.println("Enter '1' to play again, or enter any other key to quit: ");
            playAgain = scanner.next();
            if (!playAgain.toString().equals("1"))
                break;
        }
    }
}
