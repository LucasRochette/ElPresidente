import java.util.List;

public class Event {
    private String name;
    private List<Choice> choices;


    public Event(String name, List<Choice> choices) {
        this.name = name;
        this.choices = choices;
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
}
