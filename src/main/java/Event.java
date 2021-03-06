import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private ArrayList<Choice> choices;

    public Event(String name, ArrayList<Choice> choices) {
        this.name = name;
        this.choices = choices;
    }

    public Event(String eventName) {
        this.name = eventName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [name=" + name + ",choices="+choices+"]";
    }
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    protected void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }


    protected void addChoice(String name, Effect actualEffect){
        Choice c = new Choice(name,actualEffect);
        this.choices.add(c);
    }
}