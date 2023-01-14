package JavaBlackjack;

public class Bot extends Player {
    
    private int runningCount;
    private int totalCount;

    public Bot(int botNumber) {
        super("Bot" + botNumber, 100);
        this.runningCount = 0;
        this.totalCount = 0;
    }

    public int getValueOfTable() {

    }

}
