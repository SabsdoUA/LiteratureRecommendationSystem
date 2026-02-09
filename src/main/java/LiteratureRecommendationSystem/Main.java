package LiteratureRecommendationSystem;

import LiteratureRecommendationSystem.User.User;
import LiteratureRecommendationSystem.User.UserFactory;
import LiteratureRecommendationSystem.domain.Book;
import LiteratureRecommendationSystem.domain.Encyclopedia;
import LiteratureRecommendationSystem.domain.Literature;
import LiteratureRecommendationSystem.domain.Manga;
import LiteratureRecommendationSystem.repository.LiteratureRepository;
import LiteratureRecommendationSystem.service.DataForFilter;
import LiteratureRecommendationSystem.service.InputForFilter;
import LiteratureRecommendationSystem.service.LiteratureService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int DEFAULT_LIMIT = 1;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            UserFactory userFactory = new UserFactory(scanner);
            InputForFilter inputForFilter = new InputForFilter(scanner);
            LiteratureRepository repository = new LiteratureRepository();
            LiteratureService service = new LiteratureService(repository);

            User user = userFactory.create();
            DataForFilter filter = inputForFilter.create(user);
            List<Literature> recommendations = service.recommend(filter, DEFAULT_LIMIT);
            printRecommendations(user, recommendations);
        }
    }

    private static void printRecommendations(User user, List<Literature> recommendations) {
        System.out.println("\nPre používateľa " + user.getName() + " je najvhodnejšie odporúčanie:");
        if (recommendations.isEmpty()) {
            System.out.println("Nenašli sa žiadne odporúčania pre zadané kritériá.");
            return;
        }
        Literature literature = recommendations.get(0);
        System.out.println(buildDescription(literature));
    }

    private static String buildDescription(Literature literature) {
        StringBuilder details = new StringBuilder();
        details.append("Typ: ").append(literature.getType()).append("\n");
        details.append("Názov: ").append(literature.getName()).append("\n");
        details.append("Žáner: ").append(literature.getGenre()).append("\n");
        details.append("Vekové obmedzenie: ").append(literature.getAgeLimit()).append("\n");
        details.append("Rok vydania: ").append(literature.getReleaseDate()).append("\n");
        details.append("Popis: ").append(literature.getDescription()).append("\n");
        details.append("Odkaz: ").append(literature.getLinkBook()).append("\n");

        if (literature instanceof Book book) {
            details.append("Autor: ").append(book.getAuthor()).append("\n");
            details.append("Počet strán: ").append(book.getPages()).append("\n");
        } else if (literature instanceof Manga manga) {
            details.append("Ilustrátor: ").append(manga.getIllustrator()).append("\n");
            details.append("Počet zväzkov: ").append(manga.getVolumes()).append("\n");
            details.append("Farebnosť: ").append(manga.getColorDescription()).append("\n");
        } else if (literature instanceof Encyclopedia encyclopedia) {
            details.append("Oblasť: ").append(encyclopedia.getField()).append("\n");
            details.append("Vydanie: ").append(encyclopedia.getEdition()).append("\n");
        }
        return details.toString();
    }
}