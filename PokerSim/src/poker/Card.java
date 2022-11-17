package poker;

public class Card {

    private String suit;

    private String suitUnicode;
    private String valueName;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    @java.lang.Override
    public java.lang.String toString() {
        return this.valueName + Character.toString((char)2660) ;
    }

    public Card(String suit, String valueName, int value) {
        this.suit = suit;
        this.valueName = valueName;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
        setSuitUnicode(this.suit);
    }

    public String getSuitUnicode() {
        return suitUnicode;
    }

    public void setSuitUnicode(String suit) {
        switch (suit) {
            case "S" -> this.suitUnicode = "\u2660";
            case "H" -> this.suitUnicode = "\u2665";
            case "D" -> this.suitUnicode = "\u2666";
            case "C" -> this.suitUnicode = "\u2663";
        }
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public void printCard() {
        switch (this.suit) {
            case "S" -> System.out.print(this.valueName + "\u2660");
            case "H" -> System.out.print(this.valueName + "\u2665");
            case "D" -> System.out.print(this.valueName + "\u2666");
            case "C" -> System.out.print(this.valueName + "\u2663");
        }
    }

    public boolean greaterThan(Card card1, Card card2) {
        return card1.getValue() > card2.getValue();
    }
}
