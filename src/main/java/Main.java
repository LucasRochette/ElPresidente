
import java.io.*;
import java.util.Map;


public class Main {

    public static void display_menu() {
        String[] pathnames;
        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File("C:\\Users\\Lucas\\IdeaProjects\\ElPresidente\\src\\main\\resources");
        // Populates the array with names of files and directories
        pathnames = f.list();
        int i = 1;
        for (String file : pathnames)
        {
            System.out.println(i+" - "+file);
            i+=1;
        }
        System.out.print ( "Selection: " );
    }



    public static void main(String[] args) throws IOException {
        /*Scanner in = new Scanner ( System.in );
        display_menu();
        while(in.nextInt() < 0 || in.nextInt() >  )
        {

        }

*/
        Game game = new Game();
        game.loadScenario("attackontitans");


        Menu menu = new Menu(game);




        boolean gameIsLive=true;

            menu.gameMenu();
    }
}
