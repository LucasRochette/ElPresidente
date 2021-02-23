public class Faction {

    //Attributes

    private String name;
    private int satisfactionPercentage;
    private int numberOfPartisans;




    //Methods
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name=" + name + ",numberOfPartisans="+numberOfPartisans+",satisfactionPercentage="+satisfactionPercentage+"]";
    }

    public Faction(String name, int SatisfactionPercentage, int numberOfPartisans) {
        this.name = name;
        this.satisfactionPercentage = SatisfactionPercentage;
        this.numberOfPartisans = numberOfPartisans;
    }


    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getApprobation() {
        return satisfactionPercentage;
    }

    private void setApprobation(int approbation) {
        this.satisfactionPercentage = approbation;
    }

    public int getSupporters() {
        return numberOfPartisans;
    }

    private void setSupporters(int supporters) {
        this.numberOfPartisans = supporters;
    }
}
