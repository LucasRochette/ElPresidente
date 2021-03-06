
import java.util.Map;

public class Effect {

    private Map<String,Double> onFactions;
    private Map<String,Double> onFactors;
    private Double partisans;

    public Effect(Map<String, Double> onFactions, Map<String, Double> onFactors, Double partisans) {
        this.onFactions = onFactions;
        this.onFactors = onFactors;
        this.partisans = partisans;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [onFaction=" + onFactions + ",onFactors="+onFactors+ ",partisans="+partisans+"]";
    }

    public Map<String, Double> getOnFactions() {
        return onFactions;
    }

    protected void setOnFactions(Map<String, Double> onFactions) {
        this.onFactions = onFactions;
    }

    public Map<String, Double> getOnFactors() {
        return onFactors;
    }

    protected void setOnFactors(Map<String, Double> onFactors) {
        this.onFactors = onFactors;
    }


    public Double getPartisans() {
        return partisans;
    }

    protected void setPartisans(Double partisans) {
        this.partisans = partisans;
    }
}
