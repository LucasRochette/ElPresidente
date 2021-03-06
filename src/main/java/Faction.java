public class Faction {

    //Attributes

    private String name;
    private double satisfactionPercentage;
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

    public double getApprobation() {
        return satisfactionPercentage;
    }

    protected void setApprobation(double approbation) {
        this.satisfactionPercentage = approbation;
    }

    public int getSupporters() {
        return numberOfPartisans;
    }

    protected void setSupporters(int supporters) {
        this.numberOfPartisans = supporters;
    }
}
