package LiteratureRecommendationSystem.service;

import LiteratureRecommendationSystem.User.User;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputForFilter {

    private final Scanner scanner;

    public InputForFilter(Scanner scanner) {
        this.scanner = scanner;
    }

    public DataForFilter create(User user) {

        System.out.println(
                "Vyberte si typ literatúry, ktorú si želáte čítať.\n" +
                        "Prosím, zadajte číslo, ktoré zodpovedá zvolenej možnosti:\n\n" +
                        "1. Knihy\n" +
                        "2. Manga\n" +
                        "3. Encyklopédie\n" +
                        "4. Náhodný výber literatúry"
        );
        int selectionLiterature = readInt();


        System.out.println(
                "Zadajte vekové hodnotenie literatúry, ktorú si želáte čítať.\n" +
                        "Prosím, zadajte iba číslo od 0 do 18 (napríklad: 12, 16, 18)."
        );
        int ageRating = readInt();

        Map<Integer, List<String>> genresByType = Map.of(
                1, List.of("Detektívka", "Klasika", "Román", "Rozprávka", "Sci-fi"),
                2, List.of("Josei", "Seinen", "Shojo", "Shonen", "Sports"),
                3, List.of(
                        "Architektúra", "Astronómia", "Biologia", "Chémia", "Dinosaurus",
                        "Ekonomika", "Film", "Filozofia", "Fyzika", "Geografia",
                        "História", "Hudba", "Jazykoveda", "Kuchárska", "Literatúra",
                        "Matematika", "Medicína", "Mytológia", "Náboženstvo", "Právo",
                        "Príroda", "Psychológia", "Šport", "Technika", "Umenie",
                        "Veda", "Vojenstvo", "Zdravie", "Zoológia"
                )
        );

        int genre = 0;
        if (selectionLiterature != 4) {
            List<String> availableGenres = genresByType.get(selectionLiterature);

            if (availableGenres == null) {
                System.out.println("Neplatná voľba typu literatúry.");
                genre = 0;
            } else {
                System.out.println("\nVyberte si žáner (zadajte číslo):");
                for (int i = 0; i < availableGenres.size(); i++) {
                    System.out.println((i + 1) + ". " + availableGenres.get(i));
                }

                int genreChoice = readInt();
                genre = (genreChoice >= 1 && genreChoice <= availableGenres.size()) ? genreChoice : 0;

                if (genre == 0) {
                    System.out.println("Neplatná voľba žánru. Žáner nebude použitý.");
                }
            }
        }

        System.out.println(
                "\nVyberte obdobie podľa roku vydania:\n" +
                        "1. Nová literatúra (posledné 2 roky)\n" +
                        "2. Stredná literatúra (3–4 roky)\n" +
                        "3. Stará literatúra (5 rokov a viac)"
        );

        RecencyCategory recency = readRecency();

        return new DataForFilter(selectionLiterature, ageRating, genre, recency, user);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Prosím, zadajte iba číslo.");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private RecencyCategory readRecency() {
        Map<Integer, RecencyCategory> recencyByChoice = Map.of(
                1, RecencyCategory.NEW,
                2, RecencyCategory.MID,
                3, RecencyCategory.OLD
        );
        java.util.function.IntFunction<RecencyCategory> mapper = choice -> recencyByChoice.get(choice);
        while (true) {
            int recencyChoice = readInt();
            RecencyCategory recency = mapper.apply(recencyChoice);
            if (recency != null) {
                return recency;
            }
            System.out.println("Neplatná voľba obdobia podľa roku vydania. Skúste znova.");
        }
    }
}
