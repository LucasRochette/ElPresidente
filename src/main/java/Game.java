package main.java;

import java.util.List;

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
    public void bribes(Faction faction){
        if (getTreasury() >= ((double)(faction.getSupporters()*15))){
            setTreasury(getTreasury() - ((double)faction.getSupporters() * 15 ));
            faction.setApprobation((int) (faction.getApprobation()*1.1));
            if (faction.getApprobation() > 100 ){
                faction.setApprobation(100);
            }
        }
    }
}
