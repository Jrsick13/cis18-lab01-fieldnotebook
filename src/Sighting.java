package fieldnotebook;

import java.time.LocalDate;

public class Sighting implements Describable {
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
        String detail = notes.isEmpty() ? "" : " (" + notes + ")";
        return String.format("%s count=%d when=%s%s", species, count, when, detail);
    }
}