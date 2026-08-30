package fieldnotebook;

import java.util.*;
import java.util.stream.Collectors;

public class NotebookQueries {

    // C1: sum(s.count for s in sightings)
    // Pipeline: mapToInt -> sum
    public static int totalOrganisms(List<Sighting> sightings) {
        return sightings.stream()
                .mapToInt(Sighting::getCount)
                .sum();
    }

    // C2: sorted({s.species for s in sightings})
    // Pipeline: map -> distinct -> sorted -> toList
    public static List<String> speciesSeen(List<Sighting> sightings) {
        return sightings.stream()
                .map(Sighting::getSpecies)
                .distinct()
                .sorted()
                .toList();
    }

    // C3: [s for s in sightings if s.count > n]
    // Pipeline: filter -> toList
    public static List<Sighting> sightingsOver(List<Sighting> sightings, int n) {
        return sightings.stream()
                .filter(s -> s.getCount() > n)
                .toList();
    }

    // C4: max(totals, key=totals.get)
    // Pipeline: collect(groupingBy(..., summingInt(...))) then max on entrySet().stream()
    public static String busiestSpecies(List<Sighting> sightings) {
        return sightings.stream()
                .collect(Collectors.groupingBy(
                        Sighting::getSpecies,
                        Collectors.summingInt(Sighting::getCount)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // C5: ", ".join(s.species for s in sightings)
    // Pipeline: map -> Collectors.joining(", ")
    public static String speciesJoined(List<Sighting> sightings) {
        return sightings.stream()
                .map(Sighting::getSpecies)
                .collect(Collectors.joining(", "));
    }
}
    // C6: return a Map<String, List<Sighting>> grouping every sighting by the month it was recorded in.
    public static Map<String, List<Sighting>> sightingsByMonth(List<Sighting> sightings) {
        return sightings.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getWhen().getMonth().toString()
                ));
    }
