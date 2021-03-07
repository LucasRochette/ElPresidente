import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class Game {

    private String name;
    private String story;
    private int season;
    private int year;
    private double globalSatisfactionPercentage;
    private double difficulty;
    private double score;

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
        this.globalSatisfactionPercentage = globalSatisfactionPercentage;
        this.difficulty = difficulty;
        this.factions = factions;
        this.events = events;
        this.score = 0;
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
        double agriculturePercentage = 0;
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("AGRICULTURE"))
            {
                agriculturePercentage=f.getValue();
            }
        }
        return agriculturePercentage;
    }

    protected void setAgriculture(double agriculture) {
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("AGRICULTURE"))
            {
                f.setValue(agriculture);
            }
        }
    }

    public double getIndustry() {
        double industryPercentage = 0;
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("INDUSTRY"))
            {
                industryPercentage=f.getValue();
            }
        }
        return industryPercentage;

    }

    protected void setIndustry(double industry) {
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("INDUSTRY"))
            {
                f.setValue(industry);
            }
        }
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
        double treasury = 0;
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("TREASURY"))
            {
                treasury=f.getValue();
            }
        }
        return treasury;
    }

    public void setTreasury(double treasury) {
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("TREASURY"))
            {
                f.setValue(treasury);
            }
        }
    }


    public double getDifficulty() {
        return difficulty;
    }

    protected void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }


    public double getFoodUnits() {
        double foodUnits = 0;
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("FOOD"))
            {
                foodUnits=f.getValue();
            }
        }
        return foodUnits;

    }

    protected void setFoodUnits(double foodUnits) {
        for(Factor f : this.factors)
        {
            if(f.getName().equalsIgnoreCase("FOOD"))
            {
                f.setValue(foodUnits);
            }
        }
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
         globalSatisfactionPercentage=(double)Math.round((globalSatisfactionPercentage/totalPartisan) * 100d) / 100d; //limiting to 2 decimals
        return globalSatisfactionPercentage;

    }

    protected void incrementSeason()
    {

        if(this.getSeason()==3) //If its last season of year, increments year and reset season to 0
        {
            this.setSeason(0);
            this.setYear(this.getYear()+1);
        }
        else
        {
            this.setSeason(this.getSeason()+1);
        }
        this.incrementScore();
    }
    public String listScenario()
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("json");
        String path = url.getPath();
        File[] allScenarios = new File(path).listFiles();


        String choice;
        boolean isNumeric=false;
        int i;
        do{
            i=0;
            System.out.println("___ Choisissez votre scenario ___");
            for(File f : allScenarios)
            {
                i++;
                System.out.println(i+" - "+f.getName().split("\\.")[0]);
            }
            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();
            isNumeric = choice.chars().allMatch( Character::isDigit );

        }while(!isNumeric || Integer.parseInt(choice)<1 || Integer.parseInt(choice)>i);

            File choosenFile=allScenarios[Integer.parseInt(choice)-1];
            return choosenFile.getName();
    }


    public void loadScenario(String choosenScenario) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // get the file from ressource folder
            InputStream is = getClass().getClassLoader().getResourceAsStream("json/"+choosenScenario);
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

            //adding factors to the game
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

                gameEvents.add(actualEvent);
            }


            // getting all faction object in a list
            Map factions = (Map) baseParameters.get("factions");
            ArrayList<String> list = new ArrayList<String>(factions.values());
            List<Faction> factionList = Arrays.asList(gson.fromJson(String.valueOf(list), Faction[].class));



            //Setting game parameters into game Object
            this.setName(name);
            this.setStory(story);
            this.setSeason(0);
            this.setYear(0);
            this.setFactions(factionList);
            this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
            this.setEvents(gameEvents);

            System.out.println("Mode de jeu : "+this.getName());
            System.out.println("Description : "+this.getStory());

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void bribes(Faction faction){
        if (getTreasury() >= ((double)(faction.getSupporters()*15)) && (faction.getApprobation() < 100)){
            setTreasury(getTreasury() - ((double)faction.getSupporters() * 15 ));
            faction.setApprobation( (faction.getApprobation()*1.1));
            if (faction.getApprobation() > 100 ){
                faction.setApprobation(100);
            }
        }
        else if (getTreasury() < ((double)(faction.getSupporters()*15)) ){
            System.out.println("Vous n'avez pas assez de fond");
        }
        else if (faction.getApprobation() >= 100){
            System.out.println("Le taux de satisfaction est au maximum");
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
        }

        if(partisanQty>0)
        {
            addPartisanRandomly(partisanQty);
        }
        else{
            removePartisanRandomly(partisanQty);
        }

    }
    private void addPartisanRandomly(int partisans)
        {
            for(int i=0;i<partisans;i++)
            {
                Random rand = new Random();
                Faction randomFaction;
                randomFaction = this.getFactions().get(rand.nextInt(this.getFactions().size()));
                randomFaction.setSupporters(randomFaction.getSupporters()+1);
            }
            this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
        }

    private void removePartisanRandomly(int partisans)
    {
        for(int i=0;i>partisans;i--)
        {
            Random rand = new Random();
            Faction randomFaction;
            randomFaction = this.getFactions().get(rand.nextInt(this.getFactions().size()));
            randomFaction.setSupporters(randomFaction.getSupporters()-1);
        }
        this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
    }

    public void applyEffectOnFaction(Map<String,Double> onFactions) {
        for (Map.Entry<String, Double> entry : onFactions.entrySet()) {
            String factionName = entry.getKey();
            Double approbationPercentage = entry.getValue();
            for (Faction faction : this.getFactions()) {
                if (faction.getName().equalsIgnoreCase(factionName)) {
                    this.affectFactionSatisfaction(faction, approbationPercentage);
                }
            }
        }
        this.setGlobalSatisfactionPercentage(this.countGlobalSatisfaction());
    }
    public void applyEffectOnFactor(Map<String,Double> onFactors) {
        for (Map.Entry<String, Double> entry : onFactors.entrySet()) {
            String factorName = entry.getKey();
            Double value = entry.getValue();
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
    public void reviewAgricultureEndOfYear()
    {
        // Calculating needed food and making population growth or decrease
        double totalFood = this.getFoodUnits()+(this.getAgriculture()*40);
        int partisans = (int) this.getTotalPartisans();
        double neededFood = partisans*4;
        int foodBalance = (int) Math.round(totalFood-neededFood);


        if(foodBalance>0)
        {
            Random rn = new Random();
            double coefficient = rn.nextInt(10) + 1;
            int newPartisanValue=(int) Math.round(this.getTotalPartisans()*(1+(coefficient/100)));
            int partisansToAdd= (int) (newPartisanValue-partisans);
            this.addPartisanRandomly(partisansToAdd);
            System.out.println("Votre agriculture ayant été exédentaire sur l'année, "+partisansToAdd+" nouveaux partisans apparaissent sur votre île.");
        }
        else if(foodBalance<0)
        {
            int partisansToRemove=foodBalance/4;
            removePartisanRandomly(partisansToRemove);
            System.out.println("Votre agriculture ayant été déficitaire sur l'année, "+partisansToRemove+" sont décédés sur votre île.");
        }
    }
    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void incrementScore(){
        this.setScore(this.getScore() + (this.getDifficulty()*100) + (this.getTotalPartisans() * this.getGlobalSatisfactionPercentage() * this.getDifficulty()));

    }
    public void reviewIndustryEndOfYear()
    {
        // Calculating and adding profits of the year
        double generated = this.getIndustry()*10;
        this.setTreasury(this.getTreasury()+generated);
        System.out.println("Votre industrie a généré "+generated+"$ de profits cette année.");
    }


    public void displayAll()
    {
        System.out.println("---- Pourcentage dédié à l'agriculture : "+this.getAgriculture()+" - Pourcentage dédié à l'industrie : "+this.getIndustry()+" ----");
        System.out.println("---- Trésorerie : "+this.getTreasury()+" - Unités de nourriture : "+this.getFoodUnits()+" ----");
        System.out.println(this.getFactions());
        System.out.println("---- Nombre total de partisans : "+this.getTotalPartisans()+" ----");
        System.out.println("---- Satisfaction globale : "+this.getGlobalSatisfactionPercentage()+" ----");
    }
    public boolean buyFood(int quantity)
    {
        double treasury=this.getTreasury();
        double price=quantity*8;
        if(quantity*8>treasury)
        {
            return false;
        }

        this.setTreasury(treasury-price);
        this.setFoodUnits(this.getFoodUnits()+quantity);
        return true;

    }
}
