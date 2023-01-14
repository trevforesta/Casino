package JavaBlackjack;

import java.util.Iterator;
import java.util.Scanner;

public class Controller {

    /******************** FIELDS ********************/

    // the model to be used by the controller
    private Model model;
    // the view to be used by the controller
    private View view;
    // the scanner to take in user input
    private Scanner console;

    /******************** CONSTRUCTORS ********************/

    /**
     * Default constructor
     * 
     * @param model the new model
     * @param view  the new view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.console = new Scanner(System.in);
    }

    /******************** PUBLIC METHODS ********************/

    public void runGame() throws InterruptedException {
        // welcome the players and prompt for the number of players
        System.out.println("Welcome to the Blackjack table!");
        System.out.print("How many players are joining? ");
        int playerCount = this.console.nextInt();

        // if the input is 0, run a game with a bot
        if (playerCount == 0) {
            
        } else {

        }

        // use user input to create the new players
        for (int i = 1; i <= playerCount; i++) {
            // prompt the player for their name
            Thread.sleep(500);
            System.out.print("Player " + i + " please enter your name: ");
            String playerName = this.console.next();
            // prompt the player for their starting money
            System.out.print("How much money do you want to start with? ");
            double playerMoney = this.console.nextDouble();
            // create a new player and add them to the player list
            Player newPlayer = new Player(playerName, playerMoney);
            this.model.addPlayer(newPlayer);
        }
        Thread.sleep(500);
        this.view.printAllMoney(this.model.getPlayerList());

        // continue the game loop until there are no players left
        while (!this.model.getPlayerList().isEmpty()) {

            // accept each player's bet
            for (Player player : this.model.getPlayerList()) {
                // prompt the user for their bet
                Thread.sleep(500);
                System.out.print(player.getName() + " place your bet: ");
                double playerBet = this.console.nextDouble();
                // if the player does not have enough money, place the max bet
                // else place the bet
                if (playerBet > player.getMoney()) {
                    System.out.println("Insufficient funds! Placing maximum bet.");
                    player.placeBet(player.getMoney());
                } else {
                    player.placeBet(playerBet);
                }
            }
            Thread.sleep(500);
            this.view.printAllBets(this.model.getPlayerList());

            // deal a hand to each player and the dealer
            // inform the players that the cards are being dealt in a cool way
            System.out.print("Dealing cards");
            delayedEllipsis();

            this.model.initializeHands();
            this.view.printAllHands(this.model.getPlayerList(), this.model.getDealerHand());

            // check for players with blackjack
            for (Player player : this.model.getPlayerList()) {
                // if the player has blackjack, inform them
                if (this.model.isHandBlackjack(player.getHand())) {
                    Thread.sleep(1000);
                    System.out.println(player.getName() + " has blackjack!");
                }
            }

            // if the dealer is displaying a 10 card, check for blackjack
            // else give the players their turns
            if (this.model.isDealerShowing10()) {
                Thread.sleep(1000);
                System.out.print("Dealer is checking for blackjack");
                delayedEllipsis();

                // if the dealer's hand is blackjack, display the hand and inform the players
                // else inform the players and give them their turns
                if (this.model.isDealerHandBlackjack()) {
                    System.out.println("Dealer has blackjack!");
                } else {
                    System.out.println("Dealer does not have blackjack.");
                    // give each player a turn
                    playersTurn();
                }
            } else {
                playersTurn();
            }

            // print the dealer's full hand and start the dealer's turn
            Thread.sleep(1000);
            this.view.printFullDealerHand(this.model.getDealerHand());

            // while the dealer has not reached 17
            while (!this.model.isDealerHand17()) {
                Thread.sleep(1000);
                // the dealer hits
                System.out.println("Dealer hits.");
                this.model.dealDealerCard();
                Thread.sleep(500);
                this.view.printFullDealerHand(this.model.getDealerHand());
            }
            // if the dealer has bust, inform the player's
            // else the dealer stands
            if (this.model.isDealerHandBust()) {
                Thread.sleep(1000);
                System.out.println("Dealer has gone bust!");
            } else {
                Thread.sleep(1000);
                System.out.println("Dealer stands.");
            }

            // evaluate all of the player hands and award winnings
            evaluatePlayerHands();

            // iterate through the player list and remove any player that has no money left
            Iterator<Player> iterator = this.model.getPlayerList().iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                // if the player has no money left, remove them from the player list
                if (player.getMoney() <= 0) {
                    System.out.println(player.getName() + " has gone bankrupt!");
                    iterator.remove();
                }
            }

            // reset everyone's hands and the deck
            this.model.resetAllHands();
            this.model.resetDeck();
        }

        // if no players are left, end the game
        System.out.println("No players at the table! Exiting...");
    }

    /******************** PRIVATE METHODS ********************/

    /**
     * Runs each player's turn, with all the available options for play
     * 
     * @throws InterruptedException
     */
    private void playersTurn() throws InterruptedException {
        // give each player a turn
        for (Player player : this.model.getPlayerList()) {
            // if the player does not have blackjack, run their turn
            if (!this.model.isHandBlackjack(player.getHand())) {
                // loop until the player has gone bust, or stands
                while (!this.model.isHandBust(player.getHand())) {
                    Thread.sleep(1000);
                    // prompt the player for input, and read in their action
                    System.out.print(player.getName() + " take your action: ");
                    String input = this.console.next();

                    // if the player wants to hit
                    // else if the player wants to stand
                    // else if the player can, and wants to double down
                    // else if the player can, and wants to split
                    // else the input is invalid
                    if (input.equals("H")) {
                        // deal the player a card and print their hand
                        this.model.dealPlayerCard(player);
                        Thread.sleep(500);
                        this.view.printPlayerHand(player);
                        System.out.println();

                        // if the player has gone bust, inform them and end their turn
                        if (this.model.isHandBust(player.getHand())) {
                            Thread.sleep(500);
                            System.out.println("You've bust out!");
                        }
                    } else if (input.equals("S")) {
                        // end their turn
                        break;
                    } else if (input.equals("D")) {
                        // if the player cannot double down, inform them
                        // else the player double downs
                        if (player.getHand().size() > 2) {
                            System.out.println("You cannot double down!");
                        } else {
                            // double the player's bet and print their new bet
                            player.placeBet(player.getBet());
                            player.setBet(player.getBet() * 2);
                            Thread.sleep(500);
                            System.out.println("Bet is now: $" + player.getBet());

                            // deal the player a card and print their hand
                            this.model.dealPlayerCard(player);
                            Thread.sleep(500);
                            this.view.printPlayerHand(player);
                            System.out.println();

                            // if the player has gone bust, inform them
                            if (this.model.isHandBust(player.getHand())) {
                                Thread.sleep(500);
                                System.out.println("You've bust out!");
                            }
                            // end their turn
                            break;
                        }

                    } else if (input.equals("Sp")) {
                        break;
                    } else {
                        // inform the player that their input is invalid
                        System.out.println("That is not proper input!");
                    }
                }
            }
        }
    }

    private void evaluatePlayerHands() throws InterruptedException {
        Thread.sleep(1000);
        // evaluate each player's hand
        for (Player player : this.model.getPlayerList()) {
            Thread.sleep(500);
            // if the player has blackjack
            // else if the player has stood
            // else the player has gone bust
            if (this.model.isHandBlackjack(player.getHand())) {
                // if the dealer has blackjack, the player pushes
                // else the player wins
                if (this.model.isDealerHandBlackjack()) {
                    System.out.println(player.getName() + " pushed.");
                    player.addMoney((player.getBet()));
                } else {
                    System.out.println(player.getName() + " has blackjack which pays 3 to 2!");
                    player.addMoney(player.getBet() + (player.getBet() * 1.5));
                }
            } else if (!this.model.isHandBust(player.getHand())) {
                // if the dealer has gone bust, or if the player's hand beats the dealer's hand
                // else if the player's hand loses to the dealer's hand
                // else the player ties with the dealer
                if (this.model.isDealerHandBust()
                        || this.model.getHandTotal(player.getHand()) > this.model.getDealerHandTotal()) {
                    System.out.println(player.getName() + " won!");
                    player.addMoney((player.getBet() * 2));
                } else if (this.model.getHandTotal(player.getHand()) < this.model.getDealerHandTotal()) {
                    System.out.println(player.getName() + " lost!");
                } else {
                    System.out.println(player.getName() + " pushed.");
                    player.addMoney((player.getBet()));
                }
            }
            // reset the player's bet
            player.setBet(0);
        }
        // print all of the player's current money
        Thread.sleep(1000);
        this.view.printAllMoney(this.model.getPlayerList());
    }

    private void delayedEllipsis() throws InterruptedException {
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
        Thread.sleep(500);
    }

}