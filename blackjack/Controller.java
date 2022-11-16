package blackjack;

import java.io.Console;
import java.util.Scanner;

public class Controller {

    // the model to be used by the controller
    private Model model;
    // the view to be used by the controller
    private View view;

    /**
     * Constructs a new Controller
     * 
     * @param model the model to be used by the controller
     * @param view the view to be used by the controller
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Runs the game loop
     * @throws InterruptedException
     */
    public void runGame() throws InterruptedException {
        Scanner console = new Scanner(System.in);

        while(true) {
            System.out.println("Welcome to the Blackjack table!");
            System.out.print("How many players are joining? ");
            int playerCount = console.nextInt();
            for (int i = 1; i <= playerCount; i++) {
                System.out.print("Player " + i + " please enter your name: ");
                String playerName = console.next();
                System.out.print("How much money do you want to start with? ");
                int playerMoney = console.nextInt();
                Player newPlayer = new Player(playerName, playerMoney);
                this.model.addPlayer(newPlayer);
                this.view.printMoney(newPlayer);
                System.out.println();
            }
            
            System.out.println("Dealing cards...");
            Thread.sleep(3000);
            for (int i = 1; i <= playerCount; i++) {
                
            }
            
        }
    }
}
