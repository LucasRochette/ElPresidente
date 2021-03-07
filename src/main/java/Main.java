
import java.io.*;
import java.util.Map;


public class Main {


    public static void main(String[] args) throws IOException {

        Game game = new Game();
        game.loadScenario(game.listScenario()); // Lauching game with scenario choice

        Menu menu = new Menu(game);
        menu.chooseDifficulty(); // Display choose difficulty menu
        menu.gameMenu(); // Display game menu
    }
}
