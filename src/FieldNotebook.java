package fieldnotebook;

import java.time.LocalDate;
import java.util.*;

public class FieldNotebook {
    private List<Describable> items;

    public FieldNotebook() {
        this.items = new ArrayList<>();
    }

    public void add(Describable item) {
        this.items.add(item);
    }

    public List<Describable> getItems() {
        return items;
    }

    public void describeAll() {
        for (Describable item : items) {
            System.out.println("  " + item.describe());
        }
    }

    public static void main(String[] args) {
        FieldNotebook notebook = new FieldNotebook();

        notebook.add(new BirdSighting("Marbled Murrelet", 2, LocalDate.of(2026, 3, 14), "", true));
        notebook.add(new TreeSighting("Coast Redwood", 1, LocalDate.of(2026, 3, 14), "", 87.4));
        notebook.add(new BirdSighting("Steller's Jay", 6, LocalDate.of(2026, 3, 15), "", false));
        notebook.add(new Sighting("Banana Slug", 3, LocalDate.of(2026, 3, 15)));
        notebook.add(new TreeSighting("Douglas Fir", 1, LocalDate.of(2026, 3, 16), "", 52.0));
        notebook.add(new WeatherNote("Heavy fog at dawn"));

        System.out.println("--- A. Naturalist's notebook ---");
        notebook.describeAll();
    }
}