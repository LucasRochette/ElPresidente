import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    private List<Choice> choices=new ArrayList();

    public Event(String name, List<Choice> choices) {
        this.name = name;
        this.choices = choices;
    }

    public Event(String eventName) {
        this.name = eventName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public void addChoice(String name, String effects){
        Choice c = new Choice(name,effects);
        this.choices.add(c);
    }
}