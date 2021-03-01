import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


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

    public static void loadScenario(String choosenScenario) {
        choosenScenario = "attackOnTitans.json";
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\lucas.rochette\\Documents\\GitHub\\ElPresidente\\src\\main\\resources\\" + choosenScenario));

            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // getting name and story of the loaded game mode
            String name = (String) map.get("name");
            String story = (String) map.get("story");


            Map gameStartParameters = (Map) map.get("gameStartParameters");
            Map baseParameters = (Map) gameStartParameters.get("NORMAL");
            double agriculturePercentage = (double) baseParameters.get("agriculturePercentage");
            double industryPercentage = (double) baseParameters.get("industryPercentage");
            double treasury = (double) baseParameters.get("treasury");
            double foodUnits = (double) baseParameters.get("foodUnits");

            List events = (List) map.get("events");

            System.out.println(events);

            // getting all faction object in a list
            Map factions = (Map) baseParameters.get("factions");
            ArrayList<String> list = new ArrayList<String>(factions.values());
            List<Faction> factionList = Arrays.asList(gson.fromJson(String.valueOf(list), Faction[].class));



            //Setting game parameters into game Object
            Game game = new Game();
            game.setName(name);
            game.setStory(story);
            game.setAgriculturePercentage(agriculturePercentage);
            game.setIndustryPercentage(industryPercentage);
            game.setTreasury(treasury);
            game.setFoodUnits(foodUnits);
            game.setFactions(factionList);
            game.setGlobalSatisfactionPercentage(game.countGlobalSatisfaction());

            System.out.println("Mode de jeu : "+game.getName());
            System.out.println("Description : "+game.getStory());
            System.out.println("---- Agriculture percentage : "+game.getAgriculturePercentage()+" - Industry percentage : "+game.getIndustryPercentage()+" ----");
            System.out.println("---- Treasury : "+game.getTreasury()+" - FoodUnits : "+game.getFoodUnits()+" ----");
            System.out.println(game.getFactions());
            System.out.println("---- Global statisfaction percentage : "+game.getGlobalSatisfactionPercentage()+" ----");


            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        /*Scanner in = new Scanner ( System.in );
        display_menu();
        while(in.nextInt() < 0 || in.nextInt() >  )
        {

        }

*/
        loadScenario("");

    }
}
