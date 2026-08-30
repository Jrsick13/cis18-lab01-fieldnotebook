package fieldnotebook;

import java.util.*;
import java.util.stream.Collectors;

public class NotebookQueries {

    public static int totalOrganisms(List<Sighting> sightings) {
        return sightings.stream()
                .mapToInt(Sighting::getCount)
                .sum();
    }

    public static List<String> speciesSeen(List<Sighting> sightings) {
        return sightings.stream()
                .map(Sighting::getSpecies)
                .distinct()
                .sorted()
                .toList();
    }

    public static List<Sighting> sightingsOver(List<Sighting> sightings, int n) {
        return sightings.stream()
                .filter(s -> s.getCount() > n)
                .toList();
    }

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

    public static String speciesJoined(List<Sighting> sightings) {
        return sightings.stream()
                .map(Sighting::getSpecies)
                .collect(Collectors.joining(", "));
    }

    public static Map<String, List<Sighting>> sightingsByMonth(List<Sighting> sightings) {
        return sightings.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getWhen().getMonth().toString()
                ));
    }
}