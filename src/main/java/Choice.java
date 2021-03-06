import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Choice {

    private String name;
    private String actionOnFaction;
    private String actionOnFactor;
    private String effects;
    private Effect effectsObj;


    public Choice(String name, String effects) {
        this.name = name;
        this.effects = effects;
    }

    public Choice(String name, Effect actualEffect) {
        this.name = name;
        this.effectsObj = actualEffect;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name=" + name + ",effects="+effectsObj+"]";
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

    protected void setEffectsObj(Effect effectsObj) {
        this.effectsObj = effectsObj;
    }

    public Effect getEffectsObj() {
        return effectsObj;
    }

}
