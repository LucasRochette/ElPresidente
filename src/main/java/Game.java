import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game {

    private String name;
    private String story;
    private int season;
    private int year;
    private double agriculturePercentage;
    private double industryPercentage;
    private double globalSatisfactionPercentage;
    private double treasury;
    private double foodUnits;
    private int difficulty;

    private List<Faction> factions;
    private List<Event> events;



    public Game() {

    }

    public Game(String name, String story, int season, int year, int agriculturePercentage, int industryPercentage, double globalSatisfactionPercentage, int treasury, int difficulty, List<Faction> factions, List<Event> events) {
        this.name = name;
        this.story = story;
        this.season = season;
        this.year = year;
        this.agriculturePercentage = agriculturePercentage;
        this.industryPercentage = industryPercentage;
        this.globalSatisfactionPercentage = globalSatisfactionPercentage;
        this.treasury = treasury;
        this.difficulty = difficulty;
        this.factions = factions;
        this.events = events;
    }


    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    protected void setStory(String story) {
        this.story = story;
    }

    public int getSeason() {
        return season;
    }

    protected void setSeason(int season) {
        this.season = season;
    }

    public int getYear() {
        return year;
    }

    protected void setYear(int year) {
        this.year = year;
    }

    public double getAgriculturePercentage() {
        return agriculturePercentage;
    }

    protected void setAgriculturePercentage(double agriculturePercentage) {
        this.agriculturePercentage = agriculturePercentage;
    }

    public double getIndustryPercentage() {
        return industryPercentage;
    }

    protected void setIndustryPercentage(double industryPercentage) {
        this.industryPercentage = industryPercentage;
    }

    public double getGlobalSatisfactionPercentage() {
        return globalSatisfactionPercentage;
    }

    protected void setGlobalSatisfactionPercentage(double globalSatisfactionPercentage) {
        this.globalSatisfactionPercentage = globalSatisfactionPercentage;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    protected void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public List<Event> getEvents() {
        return events;
    }

    protected void setEvents(List<Event> events) {
        this.events = events;
    }

    public double getTreasury() {
        return treasury;
    }

    protected void setTreasury(double treasury) {
        this.treasury = treasury;
    }

    public int getDifficulty() {
        return difficulty;
    }

    protected void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    public double getFoodUnits() {
        return foodUnits;
    }

    protected void setFoodUnits(double foodUnits) {
        this.foodUnits = foodUnits;
    }

    protected double countGlobalSatisfaction()
    {
        double globalSatisfactionPercentage=0;
        double totalPartisan = 0;
         for(Faction fac : this.factions) {
            double partisan=fac.getSupporters();
            double satisfaction=fac.getApprobation();
            totalPartisan+=partisan;
            globalSatisfactionPercentage+=partisan*satisfaction;
        }
        globalSatisfactionPercentage=globalSatisfactionPercentage/totalPartisan;
        return globalSatisfactionPercentage;

    }

    public void loadScenario(String choosenScenario) {
        choosenScenario = "coldWarUSSR.json";
        try {
            // create Gson instance
            Gson gson = new Gson();

            // get the file from ressource folder
            InputStream is = getClass().getClassLoader().getResourceAsStream(choosenScenario);
            // create a reader
            Reader reader = new InputStreamReader(is);
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

            ArrayList events = (ArrayList) map.get("events");
            //System.out.println(events);


            ArrayList<Event> gameEvents = new ArrayList<>();
            ArrayList<Choice> gameChoices = new ArrayList<>();
            for(Object e : events)
            {

                LinkedTreeMap<Object,Object> eventObj = (LinkedTreeMap) e;
                String eventName = eventObj.get("name").toString(); // Name of the event

                Event actualEvent = new Event(eventName,gameChoices);

                //List<Choice> choiceList = null;
                List choices = (List) eventObj.get("choices"); // List of choices + each effect

                // System.out.println(choices);
                for(Object c : choices)
                {
                    //List<Choice>

                    LinkedTreeMap<Object,Object> choiceObj = (LinkedTreeMap) c;
                    String choiceName = choiceObj.get("choice").toString();



                    List effects = (List) choiceObj.get("effects"); // List of effects

                    //Choice actualChoice = new Choice(choiceName,effects.toString());
                    //gameChoices.add(actualChoice);
                   actualEvent.addChoice(choiceName,effects.toString());
                    //choiceList.add(actualChoice);

                }

                //actualEvent.setChoices(choiceList);
                gameEvents.add(actualEvent);
            }


            // getting all faction object in a list
            Map factions = (Map) baseParameters.get("factions");
            ArrayList<String> list = new ArrayList<String>(factions.values());
            List<Faction> factionList = Arrays.asList(gson.fromJson(String.valueOf(list), Faction[].class));



            //Setting game parameters into game Object
            //Game game = new Game();
            this.setName(name);
            this.setStory(story);
            this.setAgriculturePercentage(agriculturePercentage);
            this.setIndustryPercentage(industryPercentage);
            this.setTreasury(treasury);
            this.setFoodUnits(foodUnits);
            this.setFactions(factionList);
            this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
            this.setEvents(gameEvents);

            System.out.println("Mode de jeu : "+this.getName());
            System.out.println("Description : "+this.getStory());
            System.out.println("---- Agriculture percentage : "+this.getAgriculturePercentage()+" - Industry percentage : "+this.getIndustryPercentage()+" ----");
            System.out.println("---- Treasury : "+this.getTreasury()+" - FoodUnits : "+this.getFoodUnits()+" ----");
            System.out.println(this.getFactions());
            System.out.println("---- Global statisfaction percentage : "+this.getGlobalSatisfactionPercentage()+" ----");
            System.out.println("---- Events : "+this.getEvents());
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
