public class Factor {

    private String name;
    private double value;


    public Factor(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name=" + name + ",value="+value+"]";
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    protected void setValue(double value) {
        this.value = value;
    }


}
