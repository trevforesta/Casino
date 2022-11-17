package poker;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Deck deck = new Deck();
        deck.shuffle();

        int input;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of opponents: ");
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
        myHand.evaluate(table.board);
        System.in.read();
        System.out.flush();
        deck.turn(table.board);
        table.board.showBoard();
        System.in.read();
        System.out.flush();
        deck.river(table.board);
        table.board.showBoard();
        System.in.read();
    }
}