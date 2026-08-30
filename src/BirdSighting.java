package fieldnotebook;

import java.time.LocalDate;

public class BirdSighting extends Sighting {
    private boolean vocalizing;

    public BirdSighting(String species, int count, LocalDate when, String notes, boolean vocalizing) {
        super(species, count, when, notes);
        this.vocalizing = vocalizing;
    }

    public boolean isVocalizing() {
        return vocalizing;
    }

    @Override
    public String describe() {
        String base = super.describe();
        return vocalizing ? base + " [vocalizing]" : base;
    }
}