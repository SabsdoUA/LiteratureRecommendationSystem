package main.java.LiteratureRecommendationSystem.bootstrap;

import main.java.LiteratureRecommendationSystem.domain.model.Manga;
import main.java.LiteratureRecommendationSystem.repository.MangaRepository;

public class MangaSeeder {

    public static void seed(MangaRepository repo) {
        Manga berserk = new Manga("Seinen", 18, "Berserk", "Temná fantasy manga o Gutsovi.", 1989, "https://example.com/berserk", "Kentaro Miura", 41, false);
        repo.add(berserk);
    }
}
