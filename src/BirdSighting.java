package fieldnotebook;

import java.time.LocalDate;

public class BirdSighting extends Sighting {
    private boolean heardOnly;

    public BirdSighting(String species, int count, LocalDate when, boolean heardOnly, String notes) {
        super(species, count, when, notes);
        this.heardOnly = heardOnly;
    }

    public BirdSighting(String species, int count, LocalDate when, boolean heardOnly) {
        this(species, count, when, heardOnly, "");
    }

    public BirdSighting(String species, int count, LocalDate when) {
        this(species, count, when, false, "");
    }

    public boolean isHeardOnly() {
        return heardOnly;
    }

    @Override
    public String describe() {
        String how = heardOnly ? "heard" : "seen";
        return String.format("%dx %s (%s) on %s", getCount(), getSpecies(), how, getWhen());
    }
}