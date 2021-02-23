public class Sector {

    private String name;
    private int dedicated;


    public Sector(String name, int dedicated) {
        this.name = name;
        this.dedicated = dedicated;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getDedicated() {
        return dedicated;
    }

    private void setDedicated(int dedicated) {
        this.dedicated = dedicated;
    }
}
