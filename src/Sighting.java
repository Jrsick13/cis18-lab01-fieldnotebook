package fieldnotebook;

import java.time.LocalDate;

public class Sighting implements Describable {
    // 1. Mark fields as private (or protected for child access)
    private String species;
    private int count;
    private LocalDate when;
    private String notes;

    public Sighting(String species, int count, LocalDate when, String notes) {
        this.species = species;
        this.count = count;
        this.when = when;
        this.notes = notes;
    }

    public Sighting(String species, int count, LocalDate when) {
        this(species, count, when, "");
    }

    // 2. Add explicit getter methods
    public String getSpecies() {
        return species;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getWhen() {
        return when;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String describe() {
        return String.format("%dx %s on %s", count, species, when);
    }
}