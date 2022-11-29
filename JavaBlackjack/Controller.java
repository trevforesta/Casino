package JavaBlackjack;

import java.util.Iterator;
import java.util.Scanner;

public class Controller {

    /******************** FIELDS ********************/

    private Model model;
    private View view;
    private Scanner console;

    /******************** CONSTRUCTORS ********************/

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.console = new Scanner(System.in);
    }

    /******************** PUBLIC METHODS ********************/

    public void runGame() throws InterruptedException {
        System.out.println("Welcome to the Blackjack table!");
        System.out.print("How many players are joining? ");
        int playerCount = this.console.nextInt();
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Player " + i + " please enter your name: ");
            String playerName = this.console.next();
            System.out.print("How much money do you want to start with? ");
            double playerMoney = this.console.nextDouble();
            Player newPlayer = new Player(playerName, playerMoney);
            this.model.addPlayer(newPlayer);
        }
        this.view.printAllMoney(this.model.getPlayerList());
        while (!this.model.getPlayerList().isEmpty()) {
            for (Player player : this.model.getPlayerList()) {
                System.out.print(player.getName() + " place your bet: ");
                double bet = this.console.nextDouble();
                if (bet > player.getMoney()) {
                    System.out.println("Insufficient funds! Placing maximum bet.");
                    player.placeBet(player.getMoney());
                } else {
                    player.placeBet(bet);
                }
            }
            this.view.printAllBets(this.model.getPlayerList());
            System.out.println("Dealing cards...");
            Thread.sleep(1000);
            this.model.dealEveryoneCards();
            this.view.printAllHands(this.model.getPlayerList(), this.model.getDealerHand());
            Thread.sleep(2000);
            if (this.model.getDealerHand().get(0).getValue() == 1 || this.model.getDealerHand().get(0).getValue() == 10
                    || this.model.getDealerHand().get(0).getValue() == 11
                    || this.model.getDealerHand().get(0).getValue() == 12
                    || this.model.getDealerHand().get(0).getValue() == 13) {
                System.out.println("Dealer is checking for blackjack...");
                Thread.sleep(2000);
                if (this.model.isDealerHand21()) {
                    this.view.printFullDealerHand(this.model.getDealerHand());
                    System.out.println("Dealer has blackjack!");
                } else {
                    System.out.println("Dealer does not have blackjack.");
                }
            }
            for (Player player : this.model.getPlayerList()) {
                if (player.isHand21()) {
                    System.out.println(player.getName() + " has blackjack!");
                }
            }
            for (Player player : this.model.getPlayerList()) {
                if (!player.isHand21()) {
                    playerTurn(player);
                }
            }
            this.view.printFullDealerHand(this.model.getDealerHand());
            Thread.sleep(2000);
            while (!this.model.isDealerHand17()) {
                System.out.println("Dealer hits.");
                Thread.sleep(1000);
                this.model.dealDealerCard();
                this.view.printFullDealerHand(this.model.getDealerHand());
                Thread.sleep(2000);
            }
            if (this.model.isDealerHandBust()) {
                System.out.println("Dealer has gone bust!");
                Thread.sleep(2000);
            } else {
                System.out.println("Dealer stands.");
                Thread.sleep(2000);
            }
            if (this.model.isDealerHandBust()) {
                for (Player player : this.model.getPlayerList()) {
                    if (!player.isHandBust()) {
                        if (player.isHand21() && player.getHand().size() == 2) {
                            System.out.println(player.getName() + " has blackjack which pays 3 to 2!");
                            player.addMoney((player.getBet() * 1.5));
                        } else {
                            System.out.println(player.getName() + " won!");
                            player.addMoney((player.getBet() * 2));
                        }
                    }
                }
            } else {
                for (Player player : this.model.getPlayerList()) {
                    if (this.model.isDealerHand21() && this.model.getDealerHand().size() == 2) {
                        if (player.isHand21() && player.getHand().size() == 2) {
                            System.out.println(player.getName() + " pushed.");
                            player.addMoney((player.getBet()));
                        } else if (player.getHandTotal() < this.model.getDealerHandTotal()) {
                            System.out.println(player.getName() + " lost!");
                            player.setBet(0);
                        }
                    } else if (!player.isHandBust()) {
                        if (player.isHand21() && player.getHand().size() == 2) {
                            System.out.println(player.getName() + " has blackjack which pays 3 to 2!");
                            player.addMoney((player.getBet() * 1.5));
                        } else {
                            if (player.getHandTotal() > this.model.getDealerHandTotal()) {
                                System.out.println(player.getName() + " won!");
                                player.addMoney((player.getBet() * 2));
                            } else if (player.getHandTotal() < this.model.getDealerHandTotal()) {
                                System.out.println(player.getName() + " lost!");
                                player.setBet(0);
                            } else {
                                System.out.println(player.getName() + " pushed.");
                                player.addMoney(player.getBet());
                            }
                        }
                    }
                }
            }
            this.view.printAllMoney(this.model.getPlayerList());
            Iterator<Player> iterator = this.model.getPlayerList().iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (player.getMoney() < 1) {
                    System.out.println(player.getName() + " has gone bankrupt!");
                    iterator.remove();
                }
            }
            this.model.resetAllHands();
            this.model.resetDeck();
        }
        Thread.sleep(2000);
        System.out.println("No players at the table! Exiting...");
    }

    /******************** PRIVATE METHODS ********************/

    private void playerTurn(Player player) {
        while (true) {
            System.out.print(player.getName() + " take your action: ");
            String input = this.console.next();
            switch (input) {
                case "H":
                    this.model.dealPlayerCard(player);
                    this.view.printPlayerHand(player);
                    System.out.println();
                    if (player.isHandBust()) {
                        System.out.println("You've bust out!");
                        player.setBet(0);
                        return;
                    }
                    break;
                case "S":
                    return;
                default:
                    System.out.println("That is not proper input!");
            }
        }
    }

}