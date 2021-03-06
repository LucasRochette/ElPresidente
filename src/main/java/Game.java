import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.*;

public class Game {

    private String name;
    private String story;
    private int season;
    private int year;
    private double agriculture;
    private double industry;
    private double globalSatisfactionPercentage;
    private double treasury;
    private double foodUnits;
    private int difficulty;

    private List<Faction> factions;
    private List<Factor> factors;
    private List<Event> events;



    public Game() {

    }

    public Game(String name, String story, int season, int year, int agriculture, int industry, double globalSatisfactionPercentage, int treasury, int difficulty, List<Faction> factions, List<Event> events) {
        this.name = name;
        this.story = story;
        this.season = season;
        this.year = year;
        this.agriculture = agriculture;
        this.industry = industry;
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

    public double getAgriculture() {
        return agriculture;
    }

    protected void setAgriculture(double agriculture) {
        this.agriculture = agriculture;
    }

    public double getIndustry() {
        return industry;
    }

    protected void setIndustry(double industry) {
        this.industry = industry;
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


    public List<Factor> getFactors() {
        return factors;
    }

    protected void setFactors(List<Factor> factors) {
        this.factors = factors;
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

    protected void incrementSeason()
    {
        if(this.season==4)
        {

        }
    }

    public void loadScenario(String choosenScenario) {
        choosenScenario = "attackOnTitans.json";
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


            this.factors = new ArrayList<>();
            this.addFactor("Agriculture",agriculturePercentage);
            this.addFactor("industry",industryPercentage);
            this.addFactor("treasury",treasury);
            this.addFactor("food",foodUnits);


            ArrayList events = (ArrayList) map.get("events");
            //System.out.println(events);


            ArrayList<Event> gameEvents = new ArrayList<>();

            for(Object e : events)
            {
                ArrayList<Choice> gameChoices = new ArrayList<>();
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

                  // actualEvent.addChoice(choiceName,effects.toString());
                    Effect actualEffect = null;
                    for(Object ef : effects)
                    {
                        LinkedTreeMap<Object,Object> allEffects = (LinkedTreeMap) ef;
                        Map onFaction = (Map) allEffects.get("actionOnFaction");
                        Map onFactor = (Map) allEffects.get("actionOnFactor");
                        Double partisans = (Double) allEffects.get("partisans");
                        actualEffect = new Effect(onFaction,onFactor,partisans);


                    }
                    actualEvent.addChoice(choiceName,actualEffect);
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
           /* this.setAgriculture(agriculturePercentage);
            this.setIndustry(industryPercentage);
            this.setTreasury(treasury);
            this.setFoodUnits(foodUnits);*/
            this.setFactions(factionList);
            this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
            this.setEvents(gameEvents);

            System.out.println("Mode de jeu : "+this.getName());
            System.out.println("Description : "+this.getStory());
        //    System.out.println("---- Agriculture percentage : "+this.getAgriculture()+" - Industry percentage : "+this.getIndustry()+" ----");
        //    System.out.println("---- Treasury : "+this.getTreasury()+" - FoodUnits : "+this.getFoodUnits()+" ----");
            System.out.println(this.getFactors());
            System.out.println(this.getFactions());
            System.out.println("---- Global statisfaction percentage : "+this.getGlobalSatisfactionPercentage()+" ----");
            System.out.println("---- Events : "+this.getEvents());
            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void bribes(Faction faction){
        if (getTreasury() >= ((double)(faction.getSupporters()*15))){
            setTreasury(getTreasury() - ((double)faction.getSupporters() * 15 ));
            faction.setApprobation((int) (faction.getApprobation()*1.1));
            if (faction.getApprobation() > 100 ){
                faction.setApprobation(100);
            }
        }
    }


    protected void addFactor(String name, double value){
        Factor f = new Factor(name,value);
        this.factors.add(f);
    }

    public void affectFactionSatisfaction(Faction faction, double approbationPercentage) {
        double actualApprobation = faction.getApprobation();
        double newApprobation = actualApprobation*(1+(approbationPercentage/100));
        newApprobation=(double)Math.round(newApprobation * 100d) / 100d; // Limiting the result to two decimals
        faction.setApprobation(newApprobation);
    }


    public void applyEffects(Effect effectsObj)
    {
        if(effectsObj.getOnFactions()!=null)
        {
            Map<String,Double> onFactions = effectsObj.getOnFactions();
            this.applyEffectOnFaction(onFactions);

        }

        if(effectsObj.getOnFactors()!=null)
        {
            Map<String,Double> onFactors = effectsObj.getOnFactors();
            this.applyEffectOnFactor(onFactors);

        }

        if(effectsObj.getPartisans()!=null)
        {
            double partisans = effectsObj.getPartisans();
            this.affectPartisanNumber(partisans);
        }


    }

    private void affectPartisanNumber(double partisans) {
        int partisanQty=(int) partisans;
        if(this.getTotalPartisans()+partisans<=0)
        {
            System.out.println("Tous vos partisans sont morts. GAME OVER");
            return;
        }

        for(int i=0;i<partisanQty;i++)
        {
            Random rand = new Random();
            Faction randomFaction;
            do{
                randomFaction = this.getFactions().get(rand.nextInt(this.getFactions().size()));
            }while(randomFaction.getSupporters()<1);
            randomFaction.setSupporters(randomFaction.getSupporters()+1);
        }
    }


    public void applyEffectOnFaction(Map<String,Double> onFactions) {
        for (Map.Entry<String, Double> entry : onFactions.entrySet()) {
            String factionName = entry.getKey();
            Double approbationPercentage = entry.getValue();
            System.out.println(factionName);
            System.out.println(approbationPercentage);
            for (Faction faction : this.getFactions()) {
                if (faction.getName().equalsIgnoreCase(factionName)) {
                    this.affectFactionSatisfaction(faction, approbationPercentage);
                }
            }
        }
    }
    public void applyEffectOnFactor(Map<String,Double> onFactors) {
        for (Map.Entry<String, Double> entry : onFactors.entrySet()) {
            String factorName = entry.getKey();
            Double value = entry.getValue();
            System.out.println(factorName);
            System.out.println(value);
            Field[] fields = this.getClass().getDeclaredFields();
            for (Factor factor : this.getFactors()) {
                if (factor.getName().equalsIgnoreCase(factorName)) {
                    this.affectFactor(factor, value);
                }
            }

        }
    }

    public void affectFactor(Factor factor, double value)
    {
        double actualValue=factor.getValue();

        if(factor.getName().equalsIgnoreCase("TREASURY"))
        {
            double newValue = actualValue+value;
            factor.setValue(newValue);
            return;
        }

        double newValue = actualValue*(1+(value/100));
        if(newValue<0)
        {
            newValue=0;
        }
        else if(newValue>100)
        {
            newValue=100;
        }
        factor.setValue(newValue);
    }

    public double getTotalPartisans()
    {
        double total=0;
        for(Faction faction : factions)
        {
            total+=faction.getSupporters();
        }
        return total;
    }

}
