package fieldnotebook;

public class WeatherNote implements Describable {
    private String condition;

    public WeatherNote(String condition) {
        this.condition = condition;
    }

    @Override
    public String describe() {
        return "Weather observation: " + condition;
    }
}