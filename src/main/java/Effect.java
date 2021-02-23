import java.util.Map;

public class Effect {

    private Map<String,Double> onFactions;
    private Map<String,Double> onFactors;
    private Map<String,Double> onResources;


    public Map<String, Double> getOnFactions() {
        return onFactions;
    }

    public void setOnFactions(Map<String, Double> onFactions) {
        this.onFactions = onFactions;
    }

    public Map<String, Double> getOnFactors() {
        return onFactors;
    }

    public void setOnFactors(Map<String, Double> onFactors) {
        this.onFactors = onFactors;
    }

    public Map<String, Double> getOnResources() {
        return onResources;
    }

    public void setOnResources(Map<String, Double> onResources) {
        this.onResources = onResources;
    }
}
