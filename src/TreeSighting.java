package fieldnotebook;

import java.time.LocalDate;

public class TreeSighting extends Sighting {
    private double heightMeters;

    public TreeSighting(String species, int count, LocalDate when, String notes, double heightMeters) {
        super(species, count, when, notes);
        this.heightMeters = heightMeters;
    }

    public double getHeightMeters() {
        return heightMeters;
    }

    @Override
    public String describe() {
        return String.format("%s height=%.1fm", super.describe(), heightMeters);
    }
}