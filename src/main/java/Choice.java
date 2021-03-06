import java.util.List;

public class Choice {

    private String name;
    private String actionOnFaction;
    private String actionOnFactor;
    private String effects;

    public Choice(String name, String effects) {
        this.name = name;
        this.effects = effects;
    }
    public String getName() {
        return name;
    }


    public String getEffects() {
        return effects;
    }

    protected void setEffects(String effects) {
        this.effects = effects;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getActionOnFaction() {
        return actionOnFaction;
    }

    protected void setActionOnFaction(String actionOnFaction) {
        this.actionOnFaction = actionOnFaction;
    }

    public String getActionOnFactor() {
        return actionOnFactor;
    }

    protected void setActionOnFactor(String actionOnFactor) {
        this.actionOnFactor = actionOnFactor;
    }
}
