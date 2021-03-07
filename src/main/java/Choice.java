import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Choice {

    private String name;
    private Effect effectsObj;


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


    public void setName(String name) {
        this.name = name;
    }


    protected void setEffectsObj(Effect effectsObj) {
        this.effectsObj = effectsObj;
    }

    public Effect getEffectsObj() {
        return effectsObj;
    }

}
