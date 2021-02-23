import java.util.List;

public class Choice {

    private String name;
    private List<Effect> effects;

    public Choice(String name, List<Effect> effects) {
        this.name = name;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
