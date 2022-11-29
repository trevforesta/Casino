package JavaBlackjack;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.runGame();
    }
}
