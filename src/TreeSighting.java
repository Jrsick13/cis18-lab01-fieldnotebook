package fieldnotebook;

import java.time.LocalDate;

public class TreeSighting extends Sighting {
    private double heightM;

    public TreeSighting(String species, int count, LocalDate when, double heightM, String notes) {
        super(species, count, when, notes);
        this.heightM = heightM;
    }

    public TreeSighting(String species, int count, LocalDate when, double heightM) {
        this(species, count, when, heightM, "");
    }

    public TreeSighting(String species, int count, LocalDate when) {
        this(species, count, when, 0.0, "");
    }

    public double getHeightM() {
        return heightM;
    }

    @Override
    public String describe() {
        return String.format("%s, %.1fm tall, on %s", getSpecies(), heightM, getWhen());
    }
}
