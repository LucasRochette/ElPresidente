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
            Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\Lucas\\IdeaProjects\\ElPresidente\\src\\main\\resources\\" + choosenScenario));

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


            // getting all faction object in a list
            Map factions = (Map) baseParameters.get("factions");
            ArrayList<String> list = new ArrayList<String>(factions.values());
            System.out.println(list);
            List<Faction> factionList = Arrays.asList(gson.fromJson(String.valueOf(list), Faction[].class));



            //Setting game parameters
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


        //Object obj = parser.parse(new FileReader("C:\\Users\\Lucas\\IdeaProjects\\ElPresidente\\src\\main\\resources\\"+choosenScenario));

       /* JsonObject scenario = (JsonObject) obj;

        // getting name and story
        String name = scenario.get("name").getAsString();
        String story = scenario.get("story").getAsString();

        Game game = new Game();


        JsonObject allParameters = (JsonObject) scenario.get("gameStartParameters");
        JsonObject startParameters = (JsonObject) allParameters.get("NORMAL");
        JsonObject factions = (JsonObject) startParameters.get("factions");

        int agriculturePercentage = startParameters.get("agriculturePercentage").getAsInt();
        int industryPercentage = startParameters.get("industryPercentage").getAsInt();
        int treasury = startParameters.get("treasury").getAsInt();
        int foodUnits = startParameters.get("foodUnits").getAsInt();



        game.setStory(story);
        game.setName(name);

        game.setAgriculturePercentage(agriculturePercentage);
        game.setIndustryPercentage(industryPercentage);
        game.setTreasury(treasury);
        game.setFoodUnits(foodUnits);




        System.out.println("Description : "+game.getStory());
        System.out.println("Scénario de jeu : "+game.getName());
        System.out.println(game.getFoodUnits());
        System.out.println(factions);

        List<Faction> factionList = new ArrayList<>();

        for(Iterator iterator = factions.keySet().iterator(); iterator.hasNext();) {

            String key = (String) iterator.next();
            System.out.println(factions.get(key));

        }*/
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
