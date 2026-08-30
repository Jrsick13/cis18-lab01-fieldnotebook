package fieldnotebook;

import java.time.LocalDate;
import java.util.*;

public class FieldNotebook {
    // Store items using the interface type
    private List<Describable> items;

    public FieldNotebook() {
        this.items = new ArrayList<>();
    }

    // Accept any Describable object
    public void add(Describable item) {
        this.items.add(item);
    }

    public void describeAll() {
        for (Describable item : items) {
            System.out.println("  " + item.describe());
        }
    }

    // Keep remaining methods for Sighting-specific logic by filtering or checking types as needed

    public List<Sighting> getSightings() {
        return sightings;
    }

    public int totalOrganisms() {
        int total = 0;
        for (Sighting s : sightings) {
            total += s.getCount();
        }
        return total;
    }

    public List<String> speciesSeen() {
        Set<String> speciesSet = new TreeSet<>();
        for (Sighting s : sightings) {
            speciesSet.add(s.getSpecies());
        }
        return new ArrayList<>(speciesSet);
    }

    public String busiestSpecies() {
        Map<String, Integer> counts = new HashMap<>();
        for (Sighting s : sightings) {
            counts.put(s.getSpecies(), counts.getOrDefault(s.getSpecies(), 0) + s.getCount());
        }

        String busiest = null;
        int max = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                busiest = entry.getKey();
            }
        }
        return busiest;
    }

    public List<Sighting> sightingsOver(int count) {
        List<Sighting> result = new ArrayList<>();
        for (Sighting s : sightings) {
            if (s.getCount() > count) {
                result.add(s);
            }
        }
        return result;
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
        for (Sighting s : notebook.getSightings()) {
            System.out.println("  " + s.describe());
        }

        System.out.println("Total organisms: " + notebook.totalOrganisms());
        System.out.println("Species seen: " + notebook.speciesSeen());
        System.out.println("Busiest species: " + notebook.busiestSpecies());

        List<String> overTwo = new ArrayList<>();
        for (Sighting s : notebook.sightingsOver(2)) {
            overTwo.add(s.describe());
        }
        System.out.println("Sightings over 2: " + overTwo);
    }
}