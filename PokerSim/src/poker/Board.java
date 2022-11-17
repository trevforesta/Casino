package poker;

import java.util.ArrayList;

public class Board {

    ArrayList<Card> board = new ArrayList<Card>();

    public Board() {}

    public void addCard(Card card) {
        board.add(card);
    }

    public void flop() {
        for (int i = 0; i < 3; i++) {

        }
    }

    public void clearBoard(){
        board.clear();
    }

    public void showFlop() {
        for (Card card:board) {
            card.printCard();
            System.out.print(" ");
        }
    }

    public void showTurn() {
        board.get(board.size() - 1).printCard();
    }

    public void showRiver() {
        board.get(board.size() - 1).printCard();
    }

    public void showBoard() {
        for (Card card:board) {
            card.printCard();
            System.out.print(" ");
        }
    }

    public ArrayList<Card> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Card> board) {
        this.board = board;
    }

    public int size() {
        return board.size();
    }
}
